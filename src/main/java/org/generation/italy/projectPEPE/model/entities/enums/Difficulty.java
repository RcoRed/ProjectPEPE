package org.generation.italy.projectPEPE.model.entities.enums;

public enum Difficulty {
    LOW("bassa"),MEDIUM("media"),HIGH("alta");

    private String difficultyName;

    Difficulty(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }
}
