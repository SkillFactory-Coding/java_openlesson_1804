package ru.skillfactory.demobot.tg;

import com.google.common.collect.Lists;

/**
 * Абстракция над командами
 */
public enum Command {

    // Список поддерживаемых команд
    RATE("/rate"),
    HELP("/help");

    public String value;

    Command(String value) {
        this.value = value;
    }

    /**
     * Получаем значение ENUM из сообщения пользователя
     * "/rate usd" -> RATE
     * @param inputValue входное сообщение
     * @return Command
     */
    public static Command fromValue(String inputValue) {
        return Lists.newArrayList(Command.values()).stream()
                .filter(c -> c.value.equals(extractCommand(inputValue)))
                .findAny().orElse(null);
    }

    private static String extractCommand(String text) {
        text = text + " ";
        return text.substring(0, text.indexOf(" "));
    }
}
