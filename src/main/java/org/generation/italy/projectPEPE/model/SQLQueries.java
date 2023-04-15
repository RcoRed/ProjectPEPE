package org.generation.italy.projectPEPE.model;

public class SQLQueries {
    public final static String SQL_FIND_RECIPE_BY_AVOID_FOOD = """
            SELECT r.name, r.description
            FROM recipe as r join ingredient as i
            USING (id_recipe)
            WHERE i.id_food != (SELECT af.id_food 
                                 FROM avoiding_food as af
                                 JOIN person as p
                                 USING (id_person
                                 WHERE id_person = :id))
            """;
}
