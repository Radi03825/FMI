package bg.sofia.uni.fmi.mjt.sentimentanalyzer;

import bg.sofia.uni.fmi.mjt.sentimentanalyzer.exceptions.SentimentAnalysisException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParallelSentimentAnalyzer implements SentimentAnalyzerAPI {

    private int workersCount;
    private Set<String> stopWords;
    private Map<String, SentimentScore> sentimentLexicon;
    private List<Thread> workers;

    /**
     * @param workersCount number of consumer workers
     * @param stopWords set containing stop words
     * @param sentimentLexicon map containing the sentiment lexicon,
     *                         where the key is the word and the value is the sentiment score
     */
    public ParallelSentimentAnalyzer(int workersCount, Set<String> stopWords,
                                     Map<String, SentimentScore> sentimentLexicon) {
        this.workersCount = workersCount;
        this.stopWords = stopWords;
        this.sentimentLexicon = sentimentLexicon;
        this.workers = new ArrayList<>();
    }

    @Override
    public Map<String, SentimentScore> analyze(AnalyzerInput... input) {
        if (input == null || input.length == 0) {
            return Map.of();
        }

        Map<String, SentimentScore> result = new HashMap<>();
        for (AnalyzerInput currentInput : input) {
            Thread worker = createWorker(currentInput, result);
            addWorker(worker);
        }

        synchronized (this) {
            while (!workers.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread was interrupted", e);
                }
            }
        }

        synchronized (this) {
//            while (!workers.isEmpty()) {
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException("Thread was interrupted", e);
//                }
//            }
            for (Thread worker : workers) {
                try {
                    worker.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread was interrupted", e);
                }
            }

        }

        return result;
    }

    private synchronized void addWorker(Thread worker) {
        while (workers.size() >= workersCount) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted", e);
            }
        }

        workers.add(worker);
        worker.start();
    }

    private Thread createWorker(AnalyzerInput currentInput, Map<String, SentimentScore> result) {
        return new Thread(() -> {
            String inputID = currentInput.inputID();
            SentimentScore score = calculateSentimentScore(currentInput);
            synchronized (result) {
                result.put(inputID, score);
            }

            synchronized (this) {
                workers.remove(Thread.currentThread());
                this.notifyAll();
            }
        });
    }

    private SentimentScore calculateSentimentScore(AnalyzerInput currentInput) {
        SentimentScore result = SentimentScore.NEUTRAL;
        try (BufferedReader reader = new BufferedReader(currentInput.inputReader())) {
            String line;
            double totalScore = 0;
            int wordsCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    SentimentScore sentiment = sentimentLexicon.get(word.toLowerCase());
                    if (!stopWords.contains(word) && sentiment != null) {
                        totalScore += sentiment.getScore();
                        wordsCount++;
                    }
                }
            }

            if (wordsCount > 0) {
                double averageScore = totalScore / wordsCount;
                result = SentimentScore.fromScore((int) Math.round(averageScore));
            }
        } catch (IOException e) {
            throw new SentimentAnalysisException("Error reading input", e);
        }

        return result;
    }
    
}
