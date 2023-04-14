package org.generation.italy.projectPEPE.model.entities.enums;

public enum PhysicalActivity {
    LOW(1),NORMAL(2),HIGH(3);

    private int pAValue;

    PhysicalActivity(int pAValue) {
        this.pAValue = pAValue;
    }

    public int getPAValue() {
        return pAValue;
    }
}
