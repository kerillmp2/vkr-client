package utils;

import utils.HasName;

public enum ScoreType implements HasName {
    CLEAN("Чистота"),
    LOCATION("Расположение"),
    SERVICE("Сервис"),
    ROOM("Общее впечатление от комнаты"),
    MONEY("Стоимость"),
    FOOD("Еда"),
    TRANSPORT("Транспорт"),
    FACILITIES("Оборудование"),
    CULTURE("Культура"),
    JOY("Развлечения"),
    OVERALL("Средняя оценка");

    private String name;

    ScoreType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
