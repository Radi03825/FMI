package bg.sofia.uni.fmi.mjt.justifier;

public class TextJustifier {

    private static StringBuilder appendSpaces(String[] words, int maxWidth, int currentWidth, int wordsCount, int endIndex) {
        StringBuilder result = new StringBuilder();

        if (wordsCount > 1) {
            int spaces = (maxWidth - currentWidth) / (wordsCount - 1);
            int bonusSpaces = (maxWidth - currentWidth) % (wordsCount - 1);

            if (words.length - 1 == endIndex) {
                for (int i = endIndex - wordsCount + 1; i <= endIndex; i++) {
                    result.append(words[i]);

                    if (i != endIndex) {
                        result.append(" ");
                    }
                }

                int finalSpaceCount = maxWidth - currentWidth - wordsCount + 1;

                for (int i = 0; i < finalSpaceCount; i++) {
                    result.append(" ");
                }
            } else {
                for (int i = endIndex - wordsCount + 1; i <= endIndex; i++) {
                    result.append(words[i]);

                    if (i == endIndex) {
                        break;
                    }

                    for (int j = 0; j < spaces; j++) {
                        result.append(" ");
                    }

                    if (bonusSpaces > 0) {
                        result.append(" ");
                        bonusSpaces--;
                    }
                }
            }
        } else if (wordsCount == 1) {
            result.append(words[endIndex]);

            int spacesCount = maxWidth - currentWidth;

            for (int i = 0; i < spacesCount; i++) {
                result.append(" ");
            }
        }

        return result.append("\n");
    }

    public static String[] justifyText(String[] words, int maxWidth) {
        StringBuilder resultString = new StringBuilder();

        int currentWidth = 0;
        int currentWordsCount = 0;

        int currentWordIndex = 0;

        while (currentWordIndex < words.length) {
            int currentWordLength = words[currentWordIndex].length();

            if (currentWordLength + currentWidth + (currentWordsCount - 1) < maxWidth) {
                currentWidth += currentWordLength;
                currentWordsCount++;

                currentWordIndex++;
            } else {
                resultString.append(appendSpaces(words, maxWidth, currentWidth, currentWordsCount, currentWordIndex - 1));
                currentWidth = 0;
                currentWordsCount = 0;
            }
        }

        resultString.append(appendSpaces(words, maxWidth, currentWidth, currentWordsCount, currentWordIndex - 1));

        return resultString.toString().split("\n");
    }

    private static void printResult(String[] strings) {
        for (String str : strings) {
            System.out.printf("\"%s\"\n", str);
        }
    }

    public static void main(String[] args) {
        //printResult(justifyText(new String[]{"The", "quick", "brown", "fox"}, 9));
        //printResult(justifyText(new String[]{"The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog."}, 11));
        printResult(justifyText(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer."}, 20));
    }

}
