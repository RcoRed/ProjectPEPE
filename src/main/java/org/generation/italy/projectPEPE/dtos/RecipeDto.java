package org.generation.italy.projectPEPE.dtos;

import org.generation.italy.projectPEPE.model.entities.Recipe;

import java.util.stream.StreamSupport;

public class RecipeDto {
    private long id;
    private String name;
    private String description;

    public RecipeDto() {
    }

    public RecipeDto(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static RecipeDto fromEntity(Recipe recipe){
        return new RecipeDto(recipe.getId(),recipe.getName(),recipe.getDescription());
    }

    public static Iterable<RecipeDto> fromEntityIterator(Iterable<Recipe> recipes){
        return StreamSupport.stream(recipes.spliterator(), false)
                .map(RecipeDto::fromEntity).toList();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
