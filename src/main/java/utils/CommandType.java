package utils;

public enum CommandType implements HasName {
    UNKNOWN("UNKNOWN"),
    BACK("Назад"),
    GET_RECOMMENDATION("Получить рекомендации"),
    GET_TICKET("Получить информацию о билете"),
    REGISTRATION("Зарегистрироваться");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}