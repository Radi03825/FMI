# Домашно 2

## Goodreads: Book Recommender 📚

`Краен срок: 23.12.2024, 23:59`

### Условие

Ще създадем приложение, предоставящо разнообразна информация и препоръки на книги, основано на базови статистически подходи от Машинното обучение.

Ще използваме [dataset от kaggle](https://www.kaggle.com/datasets/ishikajohari/best-books-10k-multi-genre-data) с 10-те хиляди най-препоръчвани книги за всички времена. Приложили сме минимални корекции срямо него и ще работим с версията, приложена във файла [goodreads_data.csv](./resources/goodreads_data.csv).

Всеки ред от файла съдържа информация за една книга, като полетата са разделени със запетая (CSV формат):

`N,Book,Author,Description,Genres,Avg_Rating,Num_Ratings,URL`

Отделете време да разгледате файла, преди да се впуснете в зареждането и обработката му.
Понеже `Description` полето може да съдържа запетаи, които са разделител в CSV файла, стойностите са оградени с кавички ("). За да ви улесним в парсването на файла, ще ползваме библиотеката [OpenCSV](https://opencsv.sourceforge.net) и ще ви дадем наготово кодa за парсване. Най-лесният начин да сетъпнете локално OpenCSV е през IntelliJ IDE-то (аналогично на сетъпа на Mockito): десен бутон върху името на проекта → Open Module Settings → Project Settings → Libraries → натискате плюса - New Project Library - From Maven... → в търсачката пишете `com.opencsv`, избирате най-новата версия (в момента, com.opencsv:opencsv:5.9), селектирате Transitive dependencies → OK → OK → OK.

### Малко теория

За реализация на алгоритмите в тази задача, ще трябва да се запознаем с няколко концепции:

- [**TF-IDF**](https://en.wikipedia.org/wiki/Tf–idf) (Term Frequency-Inverse Document Frequency): това е статистическа метрика, използвана за оценка на важността на дадена дума в рамките на колекция от документи. *Term Frequency* (TF) изразява честотата на появяване на дадена дума в конкретен документ. *Inverse Document Frequency* (IDF) измерва колко рядка е думата в цялата колекция от документи, като намалява тежестта на често срещаните думи. Умножавайки двете, TF-IDF определя колко важна е дадена дума в документ спрямо останалите документи в колекцията. Използва се често в системите за обработка на естествен език (Natural Language Processing, NLP), за да определи релевантността на думa за даден текст.

- [**Overlap coefficient**](https://en.wikipedia.org/wiki/Overlap_coefficient): прост алгоритъм за изчисляване на подобността на две множества от думи. Ако означим двете множества с A и B, коефициентът се пресмята по следната формула:

    ![Overlap coefficient](https://wikimedia.org/api/rest_v1/media/math/render/svg/27b7794575d6bdf344e47a165544cb37a9fe5e47)

    Или по-просто казано: броят на общите елементи на A и B, разделен на размера на по-малкото от двете множества.

- [**Stopwords**](https://en.wikipedia.org/wiki/Stop_word): Има едно множество от често срещани в свободен текст думи, които носят твърде малко семантика: определителни членове, местоимения, предлози, съюзи и т.н. Такива думи се наричат *stopwords* и много алгоритми, свързани с обработка на естествен език, ги игнорират - т.е. премахват ги от съответните входни текстове, защото внасят "шум", т.е. намаляват качеството на резултата. 
Няма еднозначна дефиниция (или речник), коя дума е stopword в даден език. В нашия алгоритъм, ще ползваме списъка от 174 stopwords в английския език, записани по една на ред в текстовия файл [stopwords.txt](./resources/stopwords.txt), който сме заимствали от сайта [ranks.nl](https://www.ranks.nl/stopwords). 
Обърнете внимание, че в думите, които съдържат апостроф сме го премахнали и те са една дума - `o'clock = oclock`.

### Задължителни интерфейси и класове

**Важно!** Всички интерфейси, както и класовете с частична (а някои и с пълна) имплементация ще намерите [тук](https://github.com/fmi/java-course/tree/mjt-2024-2025/homeworks/02-goodreads/resources)

1. **Book Recommender API**

В пакета `bg.sofia.uni.fmi.mjt.goodreads.recommender` създайте интерфейс `BookRecommenderAPI`, който дефинира алгоритъм за препоръчване на книги:

```java
package bg.sofia.uni.fmi.mjt.goodreads.recommender;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;

import java.util.SortedMap;

public interface BookRecommenderAPI {

    /**
     * Searches for books that are similar to the provided one.
     *
     * @param originBook the book we should calculate similarity with.
     * @param maxN       the maximum number of entries returned
     * @return a SortedMap<Book, Double> representing the top maxN closest books
     * with their similarity to originBook ordered by their similarity score
     * @throws IllegalArgumentException if the originBook is null.
     * @throws IllegalArgumentException if maxN is smaller or equal to 0.
     */
    SortedMap<Book, Double> recommendBooks(Book originBook, int maxN);

}
```

Класът `BookRecommender` имплементира интерфейса `BookRecommenderAPI` и има публичен конструктор със сигнатура `public BookRecommender(Set<Book> books, SimilarityCalculator calculator)`.

Книгите се моделират от record-а `Book` в пакета `bg.sofia.uni.fmi.mjt.goodreads.book`, чиято частична имплементация ви е предоставена.

Библиотеката OpenCSV автоматично сплитва всички полета на всеки един запис от файла. Затова нашият record `Book` трябва задължително да има factory метод със следната сигнатура:
```java
public static Book of(String[] tokens)
```

**Обърнете внимание**, че низът, репрезентиращ жанровете, е във формат `"[genreA, genreB, genreC]"`.

Зареждането на книгите се осъществява от класа `BookLoader`. Той има един статичен метод, който приема `Reader` и връща `Set<Book>`. Използвайте този клас за зареждане на dataset-а и **не го променяйте**.

```java
package bg.sofia.uni.fmi.mjt.goodreads;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

public class BookLoader {
    public static Set<Book> load(Reader reader) {
        try (CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readAll().stream()
                    .skip(1)
                    .map(Book::of)
                    .collect(Collectors.toSet());

        } catch (IOException | CsvException ex) {
            throw new IllegalArgumentException("Could not load dataset", ex);
        }
    }
}
```

2. **SimilarityCalculator**

   За изчисляване на подобността между две книги ще използваме интерфейса `SimilarityCalculator` в пакета `bg.sofia.uni.fmi.mjt.goodreads.recommender.similarityCalculator`:

```java
package bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;

public interface SimilarityCalculator {

    /**
     * Calculates the similarity between two books.
     *
     * @param first, second - Books used for similarity calculation
     * @throws IllegalArgumentException if first or second is null                  
     * @return a double - score of similarity
     */
    double calculateSimilarity(Book first, Book second);

}
```

За да върнем възможно най-точни и реалистични стойности, ще изчисляваме подобността на базата на жанрове и описание на книгите.
За тази цел, ще използваме няколко пакета, в които ще трябва да създадем калкулатори:
- в пакета `bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.genres` ще намерите частична имплементация на класа `GenresOverlapSimilarityCalculator`. Използвайте *Overlap coefficient* (виж точката с теорията), за да изчислите коефициента на съвместимост на жанровете на две книги.
- в пакета `bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.descriptions` - ще намерите частична имплементация на класа `TFIDFSimilarityCalculator`, който генерира оценки на думите от описанията на две книги (на база на `TF-IDF` алгоритъма). За повече информация, разгледайте [допълнителното описание на алгоритъма](https://github.com/fmi/java-course/blob/mjt-2024-2025/homeworks/02-goodreads/tfidf.md).
- в пакета `bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.composite` завършете имплементацията на класа `CompositeSimilarityCalculator`. Идеята на този клас е да комбинира различни калкулатори със съответните им тежести. Сумарният резултат е сбор от резултатите на дефинираните композитни калкулатори, умножени по тяхната тежест.

Частичната имплементация на гореспоменатите класове за калкулиране ще намерите [тук](https://github.com/fmi/java-course/tree/mjt-2024-2025/homeworks/02-goodreads/resources).

3. **Tokenizer**

В пакета `bg.sofia.uni.fmi.mjt.goodreads.tokenizer` ще намерите частична имплементация на класа `Tokenizer`. Неговата идея е да извлича думи от подаден низ като прилага "изчистване" на низа и премахване на stopwords. `Tokenizer` класът има метод `List<String> tokenize(String input)`, чиято идея е да извлече всички думи от подадения `String`.

За целта, трябва първо да премахнем всички препинателни знаци и последователности от повече от един `whitespace` и финално да превърнем резултата в `lowercase`. След това трябва да извлечем думитe и да премахнем тези от тях, които са stopwords.

Полезни regex-и:

 - препинателни знаци: `\\p{Punct}`
 - whitespace: `\\s+`

4. **Book Finder API**

Интерфейс за търсене и филтриране на книги:

```java
package bg.sofia.uni.fmi.mjt.goodreads.finder;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;

import java.util.List;
import java.util.Set;

public interface BookFinderAPI {

    /**
     * Retrieves all books
     *
     * @return a Set of all books.
     */
    Set<Book> allBooks();

    /**
     * Retrieves all genres
     *
     * @return a Set of all genres.
     */
    Set<String> allGenres();

    /**
     * Searches for books by the specified author name.
     *
     * @param authorName the name of the author to search for.
     * @throws IllegalArgumentException if the author name is null or empty                  
     * @return a List of books written by the specified author.
     *         Returns an empty list if no books are found.
     */
    List<Book> searchByAuthor(String authorName);

    /**
     * Searches for books that belong to the specified genres.
     * The search can be based on different match options (all or any genres).
     *
     * @param genres a Set of genres to search for.
     * @throws IllegalArgumentException if {@param genres} is null              
     * @return a List of books that match the given genres according to the MatchOption
     *         Returns an empty list if no books are found.
     */
    List<Book> searchByGenres(Set<String> genres, MatchOption option);

    /**
     * Searches for books that match the specified keywords.
     * The search can be based on different match options (all or any keywords).
     *
     * @param keywords a {@code Set} of keywords to search for.
     * @param option the {@code MatchOption} that defines the search criteria 
     *               (either {@link MatchOption#MATCH_ALL} or {@link MatchOption#MATCH_ANY}).
     * @return a List of books that match the given keywords according to the MatchOption
     *         Returns an empty list if no books are found.
     */
    List<Book> searchByKeywords(Set<String> keywords, MatchOption option);

}
```

**Опции за търсене**

Методите на интерфейса, които предоставят търсене по жанрове/ключови думи, приемат като аргумент и `MatchOption`, който определя дали се търсят книги, съдържащи всички или поне един от подадените жанрове/думи.

```java
package bg.sofia.uni.fmi.mjt.goodreads.finder;

public enum MatchOption {
    MATCH_ALL,
    MATCH_ANY
}
```
Когато търсим по ключови думи, включваме заглавието и описанието на книга и очакваме следното поведение:
- MATCH_ALL - ВСИЧКИ подадени ключови думи се срещат в заглавието ИЛИ описанието на книгата
- MATCH_ANY - ПОНЕ ЕДНА от подадени ключови думи се срещат в заглавието ИЛИ описанието на книгата

### Тестване

Създайте автоматични тестове, гарантиращи функционалната коректност на системата. Тестовете трябва да обхващат всички методи в интерфейсите, включително и евентуални corner cases.

### Структура на проекта

Спазвайте имената на пакетите на всички по-долу описани класове, тъй като в противен случай решението ви няма да може да бъде тествано от грейдъра.

```bash
src
└── bg.sofia.uni.fmi.mjt.goodreads
    ├── book
    │    └── Book.java
    ├── finder
    │    ├── BookFinder.java
    │    ├── BookFinderAPI.java
    │    ├── MatchOption.java
    │    └── (...)
    ├── recommender
    │    ├── similaritycalculator
    │    │    ├── SimilarityCalculator.java
    │    │    ├── composite
    │    │    │    ├── CompositeSimilarityCalculator.java
    │    │    │    └── (...)
    │    │    ├── descriptions
    │    │    │    ├── TFIDFSimilarityCalculator.java
    │    │    │    └── (...)
    │    │    └── genres
    │    │         ├── GenresOverlapSimilarityCalculator.java
    │    │         └── (...)
    │    ├── BookRecommender.java
    │    └── BookRecommenderAPI.java
    ├── tokenizer
    │    └── TextTokenizer.java
    ├── BookLoader.java
    └── (...)
test
└── bg.sofia.uni.fmi.mjt.goodreads
    └── (...)
```

Обърнете внимание, че при качване на решението ви, в грейдъра ще се изпълни само _smoke_ тест, чиято цел е да изчистите
евентуални проблеми с компилацията, зареждането на dataset-a и съвсем базовата функционалност на системата. Референтите тестове и Checkstyle статичният код анализ ще се изпълнят еднократно след изтичане на крайния срок за предаване. За функционалната коректност и качеството на кода ще трябва да се погрижите без тяхната помощ.

### Предаване

Качете `src` и `test` директориите на проекта (или `.zip` архив, който ги съдържа) в съответния assignment в грейдъра. Ако ползвате статични файлове за тестване, те трябва да се намират директно на нивото на `src` и `test`, без да са в отделна директория. Препоръчваме обаче да създадете автоматични тестове, които не ползват файлове. Не качвайте многомегабайтови файлове, не качвайте и jar-ките на OpenCSV библиотеката. Първо, има лимит на архива, който може да качите, второ, библиотеките ги има на грейдъра и нямате грижа за тях.

### Оценяване

Решението може да ви донесе до 100 точки, като ще бъде оценявано за:

* функционална пълнота и коректност, и за автоматични тестове с добър code coverage (60% от оценката)
* добър обектно-ориентиран дизайн, спазване на правилата за чист код и подбиране на оптимални за задачата структури от данни (40% от оценката)

### Желаем ви успех! ☘️