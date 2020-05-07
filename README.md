# Skillfactory Java OpenLesson 1804

![logo](logo.png)

Тут есть 2 проекта наших открытых уроков, в первом мы создадим телеграм бота, а во втором - fullstack приложение исключительно на java 

Интересного и результативного обучения! :rocket: 

## До урока нужно
- Скачать и установить jdk ([Oracle](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html) или [OpenJdk](https://jdk.java.net/13/))
- Скачать и установить [IDEA](https://www.jetbrains.com/ru-ru/idea/download)

Установить переменные окружения `JAVA_HOME` и добавить ее в `PATH`
```
# Для Windows
set JAVA_HOME = C:\Program Files\Java\jdk13
# Для Linux
export JAVA_HOME=/usr/java/jdk13 /bin/java
# Для MacOS
export JAVA_HOME=$(/usr/libexec/java_home)
```

Результат, при выполнении команды в console вы должны будете увидеть подобный результат
```
sboychenko@macbook ~ % java --version 
java 11.0.6 2020-01-14 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.6+8-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.6+8-LTS, mixed mode)
```

Далее скачайте этот репозиторий (можно сделать нажав на кнопку `Clone or download`)

Так же не забудьте зарядить свои ноутбуки, и обязательно захватите хорошее настроение

## Hello World
Начнем с азов. Создадим файл `HelloWorld.java`
```
class HelloWorld {
    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println("Hello " + args[0] + "!");
        } else {
            System.out.println("Hello World!")
        }
    }
}
```
Теперь скомпилируем и запустим его
```
javac HelloWorld.java
java HelloWorld
```
Работает? 
```
%  java HelloWorld 
Hello World! 
 %  java HelloWorld Sergey
Hello Sergey! 
```
Погнали дальше! :rocket:

### Telegram bot на Java

[Перейти ->](telegram-bot/README.md)

### Vaadin TODO

[Перейти ->](vaadin-todo/README.md)
