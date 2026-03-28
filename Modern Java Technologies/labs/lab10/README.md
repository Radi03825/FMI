# Poll System 📊

Тази седмица ще създадем малка система за провеждане на анкети.

За целта ще използваме популярната [клиент-сървър](https://en.wikipedia.org/wiki/Client%E2%80%93server_model) архитектура, в която сървърът ще управлява анкетите и гласовете за всяка анкета,
а редица клиенти ще могат да взаимодействат със сървъра, за да създават анкети, да гласуват и да преглеждат резултатите.

## Poll System Server

- Сървърът трябва да обслужва **множество** клиенти едновременно.
- Всеки клиент изпраща команди към сървъра, а сървърът изпълнява командите и връща резултат към клиента.

Имплементирайте следния конструктор:  
`public PollServer(int port, PollRepository pollRepository)`  

Както и следните методи:  
`public void start()` - стартира сървъра на зададения в конструктора порт  
`public void stop()` - спира сървъра и зачиства ресурсите  

## Poll System Client

- Клиентът трябва да може да изпраща заявки към сървъра.
- Всяка заявка представлява команда, която се изпълнява на сървъра, в следствие на което се връща отговор.
- Потребителите могат да прекратят връзката си към сървъра по всяко време.

## Команди

### create-poll

Можем да създадем анкета, като зададем въпрос и списък от отговори чрез следната команда:

```
create-poll <question> <option1> <option2> [... <optionN>]
```

Изисквания и ограничения:
- Всеки въпрос трябва да има минимум два отговора.
- За конкретна анкета можем да имаме само един въпрос, но неограничен брой отговори.
- Първоначално всеки отговор трябва да има 0 гласа.

> ⚠️ **Забележка** ⚠️  
> За да не се налага да пишем къдрави регулярни изрази предпразнично, ще приемем, че въпросът и отговорите НЯМА да съдържат интервали.
> Интервал между думите във въпрос/отговор ще бъде заменен с тире.

Пример:

```
create-poll What-is-your-favourite-xmas-movie? Home-Alone Die-Hard Elf The-Grinch
```

Примерен отговор на сървъра при успешно създаване на анкета:

```json
{"status":"OK","message":"Poll 1 created successfully."}
```

### list-polls

Можем да прегледаме всички създадени анкети чрез следната команда:

```
list-polls
```

Примерен отговор на сървъра:

```json
{"status":"OK","polls":{"1":{"question":"What-is-your-favourite-xmas-movie?","options":{"The-Grinch":0,"Home-Alone":0,"Die-Hard":0,"Elf":0}}}}
```

Ако няма създадени анкети, отговорът на сървъра ще бъде:

```json
{"status":"ERROR","message":"No active polls available."}
```

### submit-vote

Можем да гласуваме неограничено за отговор на дадена анкета чрез следната команда:

```
submit-vote <poll-id> <option>
```

Изисквания и ограничения:
- Аргументите `poll-id` и `option` трябва да съответстват на валидни стойности.
    - `poll-id` е уникален идентификатор на анкетата - цяло число.
    - `option` е конкретен отговор на анкетата - низ, в който интервалите са заменени с тире.
- Ако `poll-id` не съществува, сървърът трябва да върне съобщение за грешка.
- Ако `option` не съществува за дадената анкета, сървърът трябва да върне съобщение за грешка.
- Няма ограничение за броя гласове, които можем да дадем за даден отговор.

Пример:

```
submit-vote 1 Home-Alone
```

Примерен отговор на сървъра при успешно гласуване:

```json
{"status":"OK","message":"Vote submitted successfully for option: Home-Alone"}
```

Сега, ако попитаме за резултатите на анкетата, ще видим, че Home-Alone има един глас:

```json
{"status":"OK","polls":{"1":{"question":"What-is-your-favourite-xmas-movie?","options":{"The-Grinch":0,"Home-Alone":1,"Die-Hard":0,"Elf":0}}}}
```

Примери с грешни входни данни:

```
submit-vote 999 Home-Alone
```

```json
{"status":"ERROR","message":"Poll with ID 999 does not exist."}
```

```
submit-vote 1 alabala-portokala
```

```json
{"status":"ERROR","message":"Invalid option. Option alabala-portokala does not exist."}
```

### disconnect

Можем да прекратим връзката със сървъра чрез следната команда:

```
disconnect
```

- При получаване на тази команда, сървърът трябва да затвори връзката с клиента.
- След прекъсването на връзката, клиентът трябва да може да се свърже отново, ако пожелае.
- Сървърът не трябва да прекъсва другите активни връзки (в случай на множество клиенти).

## Интерфейси

Имплементирайте следния интерфейс като създадете клас `InMemoryPollRepository` с конструктор по подразбиране:

```java
package bg.sofia.uni.fmi.mjt.poll.server.repository;

import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;

import java.util.Map;

public interface PollRepository {

    /**
     * Adds a new poll to the repository.
     *
     * @param poll the {@link Poll} object to be added to the repository.
     * @return the ID of the new poll
     */
    int addPoll(Poll poll);

    /**
     * Retrieves a poll from the repository by its unique ID.
     *
     * @param pollId the unique identifier of the poll to retrieve.
     * @return the {@link Poll} object associated with the given ID,
     * or {@code null} if no such poll exists.
     */
    Poll getPoll(int pollId);

    /**
     * Retrieves all polls stored in the repository.
     *
     * @return a {@link Map} containing all polls, where the key is the poll ID
     * and the value is the {@link Poll} object.
     */
    Map<Integer, Poll> getAllPolls();

    /**
     * Clears all polls from the repository.
     * <p>
     * This method removes all stored {@link Poll} objects, leaving the repository empty.
     */
    void clearAllPolls();

}
```

Анкета ще моделираме със следния record:

```java
package bg.sofia.uni.fmi.mjt.poll.server.model;

import java.util.Map;

public record Poll(String question, Map<String, Integer> options) {}
```

## Валидации

Уверете се, че всички команди са валидирани и връщат съобщение за грешка, ако форматът на командата не е валиден.

Пример:

```
create-poll What-is-your-favourite-xmas-movie?
```

Отговор на сървъра:

```json
{"status":"ERROR","message":"Usage: create-poll <question> <option-1> <option-2> [... <option-N>]"}
```

### Тестване

⭐ Тествайте ръчно имплементацията, първо с един, а после с няколко паралелно свързани клиента, и се убедете, че работи коректно.

⭐ Писането на автоматични тестове за тази задача е по ваш избор, но съветваме всеки да пробва, тъй като ще ви е полезно и за курсовите проекти.

:point_right: **Подсказка:** Припомнете си различните имплементации на Echo Client-Server. Можем ли да ги превърнем в Poll System 📊 Client-Server приложение?

:point_right: **Подсказка:** Решението на тази задача ще ви улесни изключително много при разработката на курсовите ви проекти, защото всички те представляват приложения тип клиент-сървър, като сървърът обслужва много потребители едновременно.

### Примерна структура на проекта

Добра практика при създаването на приложения тип клиент-сървър е да отделяте клиента и сървъра в отделни проекти. Това предотвратява грешки от типа, класове/интерфейси от клиента да се ползват от сървъра, или обратно. Също така, в реална ситуация, бихме искали да пакетираме и разпространяваме поотделно клиентската и сървърната част на нашето приложение.
Като минимум, отделете имплементацията на клиента и сървъра в отделни пакети.

В грейдъра качете папки `src` и `test`, ако имате тестове (или техен общ `zip` архив).

```
src
└─ bg.sofia.uni.fmi.mjt.poll
    ├── client    
    │    └── (...)
    ├── server
    │    ├── model
    │    │    └── Poll.java
    │    ├── repository
    │    │    ├── InMemoryPollRepository.java
    │    │    ├── PollRepository.java
    │    │    └── (...)
    │    ├─- PollServer.java
    │    └─- (...)
    └── (...)
```