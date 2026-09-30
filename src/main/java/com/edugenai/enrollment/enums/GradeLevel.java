package com.edugenai.enrollment.enums;

public enum GradeLevel {
    GRADE_3("PRIMARY"),
    GRADE_4("PRIMARY"),
    GRADE_5("PRIMARY");

    private final String category;

    GradeLevel(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
