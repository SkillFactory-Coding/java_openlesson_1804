package ru.skillfactory.demobot.tg;

import com.google.common.collect.Lists;

public enum Command {

    RATE("/rate"),
    HELP("/help");

    public String value;

    Command(String value) {
        this.value = value;
    }

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
