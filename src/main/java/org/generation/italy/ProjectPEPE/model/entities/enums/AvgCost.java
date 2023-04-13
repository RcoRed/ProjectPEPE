package org.generation.italy.ProjectPEPE.model.entities.enums;

public enum AvgCost {
    LOW(1), MEDIUM(2), HIGH(3), EXOTIC(4);

    private int costLevel;

    AvgCost(int costLevel) {
        this.costLevel = costLevel;
    }

    public int getCostLevel() {
        return costLevel;
    }

    public static AvgCost getEnumByValue(double d){
        if(d <= 1.5){
            return AvgCost.LOW;
        } if(d > 1.5 && d < 2.5){
            return AvgCost.MEDIUM;
        } if(d > 2.5 && d < 3.5){
            return AvgCost.HIGH;
        }
        return AvgCost.EXOTIC;
    }
}
