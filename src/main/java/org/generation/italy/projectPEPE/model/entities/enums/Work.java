package org.generation.italy.projectPEPE.model.entities.enums;

public enum Work {
    SEDENTARY(1),ACTIVE(2),VERY_ACTIVE(3);

    private int workValue;

    Work(int workValue) {
        this.workValue = workValue;
    }

    public int getWorkValue() {
        return workValue;
    }
}
