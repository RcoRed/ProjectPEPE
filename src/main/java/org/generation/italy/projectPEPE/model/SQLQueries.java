package org.generation.italy.projectPEPE.model;

public class SQLQueries {
    public final static String SQL_FIND_RECIPE_BY_AVOID_FOOD = """
          SELECT *
            FROM recipe as r
            WHERE r.id_recipe NOT IN (SELECT i.id_recipe
                                 FROM avoiding_food as af
                                 JOIN person as p
                                 ON af.id_person = :pippo
                                 JOIN ingredient as i
                                 USING (id_food)
                                 GROUP BY i.id_recipe)""";
}
