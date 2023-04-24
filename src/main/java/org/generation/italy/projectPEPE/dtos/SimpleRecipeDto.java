package org.generation.italy.projectPEPE.dtos;

import org.generation.italy.projectPEPE.model.entities.Recipe;

import java.util.stream.StreamSupport;

public class SimpleRecipeDto {
    private long id;
    private String name;
    private String imageFilePath;

    public SimpleRecipeDto() {
    }

    public SimpleRecipeDto(long id, String name, String imageFilePath) {
        this.id = id;
        this.name = name;
        this.imageFilePath = imageFilePath;
    }

    public static SimpleRecipeDto fromEntity(Recipe recipe){
        return new SimpleRecipeDto(recipe.getId(),recipe.getName(),recipe.getImageFilePath());
    }

    public static Iterable<SimpleRecipeDto> fromEntityIterator(Iterable<Recipe> recipes){
        return StreamSupport.stream(recipes.spliterator(), false)
                .map(SimpleRecipeDto::fromEntity).toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }
}
