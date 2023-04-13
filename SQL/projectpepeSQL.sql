DROP TABLE IF EXISTS food_category, food_storage, avoiding_food, recipe_food, recipe, food, person;
DROP TYPE IF EXISTS WORK, DIET, PHISICAL_ACTIVITY, CATEGORY, AVG_COST, DIFFICULTY, DISH, SEX;

CREATE TYPE WORK    AS ENUM('SEDENTARY','ACTIVE','VERY_ACTIVE');
CREATE TYPE DIET     AS ENUM('VEGAN', 'VEGETARIAN', 'OMNIVOROUS');
CREATE TYPE PHISICAL_ACTIVITY     AS ENUM('NO', 'LOW', 'NORMAL', 'HIGH');
CREATE TYPE CATEGORY     AS ENUM('REDMEAT', 'WHITEMEAT', 'FATFISH', 'THINFISH', 'EGG', 
                                'DAIRY', 'CHEESE', 'CEREAL', 'TUBER', 'VEGETABLE', 
                                'FRUIT', 'DRYFRUIT', 'SEASONINGFAT', 'SPICE');
CREATE TYPE AVG_COST     AS ENUM('LOW', 'MEDIUM', 'HIGH', 'EXOTIC');
CREATE TYPE DIFFICULTY     AS ENUM('LOW', 'MEDIUM', 'HIGH');
CREATE TYPE DISH     AS ENUM('APPETIZER', 'FIRST', 'SECOND', 'DESSERT');
CREATE TYPE SEX     AS ENUM('MALE', 'FEMALE', 'UNDEFINEED');


--tabella PERSON
CREATE TABLE person
(
    id_person                BIGINT NOT NULL,
    firstname            VARCHAR(32) NOT NULL,
    lastname            VARCHAR(32) NOT NULL,
    age                    INT NOT NULL,
    weight                INT NOT NULL,
    height                INT NOT NULL,
    sex                    sex NOT NULL,
    work                work NOT NULL,
    diet                diet NOT NULL,
    phisical_activity   phisical_activity NOT NULL,
    ideal_weight        INT NOT NULL,
    calorie_req            INT NOT NULL,
    mail                VARCHAR(32) NOT NULL,
    password            VARCHAR(32) NOT NULL,

      CONSTRAINT PK_person PRIMARY KEY(id_person)

);

    CREATE SEQUENCE person_sequence
    start 1
    increment 1
    OWNED BY person.id_person;


--tabella FOOD
CREATE TABLE food (
    id_food BIGINT NOT NULL,
    name VARCHAR(40) NOT NULL,
    kal DECIMAL,
    carbs DECIMAL,
    sugar DECIMAL,
    protein DECIMAL,
    fat DECIMAL,
    fiber DECIMAL,
    saturated_fat DECIMAL,
    avg_cost AVG_COST,
    CONSTRAINT PK_food PRIMARY KEY (id_food)
);

CREATE SEQUENCE food_sequence
    start 1
    increment 1
    OWNED BY food.id_food;


--tabella recipe
CREATE TABLE recipe
(
    id_recipe                 BIGINT NOT NULL,
    name                    VARCHAR(50) NOT NULL,
    description                TEXT NOT NULL,
    to_cook                    BOOLEAN NOT NULL,
    image_file_path            VARCHAR(69),
    tot_nutritional_value    INT NOT NULL,
    tot_preparation_time    INT NOT NULL,
    number_ingredient        INT NOT NULL,
    total_cost                AVG_COST NOT NULL,
    difficulty                DIFFICULTY NOT NULL,
    diet                    DIET NOT NULL,
    dish                    DISH NOT NULL,
    CONSTRAINT PK_recipe PRIMARY KEY(id_recipe)
);

CREATE SEQUENCE recipe_sequence
  start 1
  increment 1
  OWNED BY recipe.id_recipe;

CREATE TABLE recipe_food
(
    id_recipe                 BIGINT NOT NULL,
    id_food                     BIGINT NOT NULL,
    quantity                INT NOT NULL,
    is_optional                BOOLEAN NOT NULL,
    CONSTRAINT FK_recipe_food_recipe FOREIGN KEY(id_recipe)
        REFERENCES recipe(id_recipe),
    CONSTRAINT FK_recipe_food_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);

--tabella AVOIDING_FOOD
CREATE TABLE avoiding_food
(
    id_person        BIGINT NOT NULL,
    id_food        BIGINT NOT NULL,
    CONSTRAINT FK_avoiding_food_person FOREIGN KEY(id_person)
        REFERENCES person(id_person),
    CONSTRAINT FK_avoiding_food_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);

--tabella FOOD_STORAGE
CREATE TABLE food_storage
(
    id_person               BIGINT NOT NULL,
    id_food                 BIGINT NOT NULL,
    quantity                INT NOT NULL,
    CONSTRAINT FK_food_storage_person FOREIGN KEY(id_person)
        REFERENCES person(id_person),
    CONSTRAINT FK_food_storage_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);

--tabella FOOD_CATEGORY
CREATE TABLE food_category
(
    id_food         BIGINT NOT NULL,
    category        CATEGORY NOT NULL,
    CONSTRAINT FK_food_category_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);