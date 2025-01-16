package com.example.hrm_service.entity.enumConstants;

public enum RelationShip {
    MOTHER("Мать"),
    FATHER("Отец"),
    BROTHER("Брат"),
    SISTER("Сестра"),
    WIFE("Жена"),
    HUSBAND("Муж"),
    SON("Сын"),
    DAUGHTER("Дочь"),
    MOTHER_IN_LAW("Тёща"),
    FATHER_IN_LAW("Свёкор");

    private final String label;

    RelationShip(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
