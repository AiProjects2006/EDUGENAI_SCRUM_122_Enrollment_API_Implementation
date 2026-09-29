package com.edugenai.enrollment.enums;

public enum GradeLevel {
    GRADE_3("PRIMARY"),
    GRADE_4("PRIMARY"),
    GRADE_5("PRIMARY"),
    GRADE_6("SECONDARY"),
    GRADE_7("SECONDARY"),
    GRADE_8("SECONDARY"),
    GRADE_9("SECONDARY"),
    GRADE_10("SECONDARY"),
    GRADE_11("SECONDARY");

    private final String category;

    GradeLevel(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
