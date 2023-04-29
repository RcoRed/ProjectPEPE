DROP TABLE IF EXISTS food_category, category, food_storage, avoiding_food, ingredient, recipe, food, token,person, person_auth;
DROP TYPE IF EXISTS WORK, DIET, PHYSICAL_ACTIVITY, AVG_COST, DIFFICULTY, DISH, SEX, ROLE, TOKEN_TYPE;

CREATE TYPE WORK                    AS ENUM('SEDENTARY','ACTIVE','VERY_ACTIVE');
CREATE TYPE DIET                    AS ENUM('VEGAN', 'VEGETARIAN', 'OMNIVOROUS');
CREATE TYPE PHYSICAL_ACTIVITY       AS ENUM('NO', 'LOW', 'NORMAL', 'HIGH');
CREATE TYPE AVG_COST                AS ENUM('LOW', 'MEDIUM', 'HIGH', 'EXOTIC');
CREATE TYPE DIFFICULTY              AS ENUM('LOW', 'MEDIUM', 'HIGH');
CREATE TYPE DISH                    AS ENUM('APPETIZER', 'FIRST', 'SECOND', 'DESSERT');
CREATE TYPE SEX                     AS ENUM('MALE', 'FEMALE', 'UNDEFINEED');
CREATE TYPE ROLE                    AS ENUM('USER', 'ADMIN');
CREATE TYPE TOKEN_TYPE              AS ENUM('BEARER');

--tabella PERSON_AUTH
CREATE TABLE person_auth
(
    id_person_auth          BIGINT NOT NULL,
    email                   VARCHAR(50) NOT NULL,
    password                TEXT NOT NULL,
    role                    ROLE NOT NULL,
    CONSTRAINT PK_person_auth PRIMARY KEY(id_person_auth)
);
CREATE SEQUENCE person_auth_sequence
    start 1
    increment 1
    OWNED BY person_auth.id_person_auth;

--tabella PERSON
CREATE TABLE person
(
    id_person               BIGINT NOT NULL,
    firstname               VARCHAR(32) NOT NULL,
    lastname                VARCHAR(32) NOT NULL,
    dob                     DATE NOT NULL,
    weight                  INT NOT NULL,
    height                  INT NOT NULL,
    sex                     sex NOT NULL,
    work                    work NOT NULL,
    diet                    diet NOT NULL,
    physical_activity       physical_activity NOT NULL,
    ideal_weight            INT NOT NULL,
    calorie_req             INT NOT NULL,
    CONSTRAINT PK_person PRIMARY KEY(id_person),
    CONSTRAINT FK_person_person_auth FOREIGN KEY(id_person)
            REFERENCES person_auth(id_person_auth)
);


--tabella TOKEN
CREATE TABLE token
(
    id_token                BIGINT NOT NULL,
    token                   TEXT NOT NULL,
    token_type              TOKEN_TYPE NOT NULL,
    revoked                 BOOLEAN NOT NULL,
    expired                 BOOLEAN NOT NULL,
    id_person_auth          BIGINT NOT NULL,
    UNIQUE(token_type),
    CONSTRAINT PK_token PRIMARY KEY(id_token),
    CONSTRAINT FK_token_person_auth FOREIGN KEY(id_person_auth)
                REFERENCES person_auth(id_person_auth)
);
CREATE SEQUENCE token_sequence
    start 1
    increment 1
    OWNED BY token.id_token;


--tabella FOOD
CREATE TABLE food (
    id_food                 BIGINT NOT NULL,
    name                    VARCHAR(40) NOT NULL,
    kal                     DECIMAL,
    carbs                   DECIMAL,
    sugar                   DECIMAL,
    protein                 DECIMAL,
    fat                     DECIMAL,
    fiber                   DECIMAL,
    saturated_fat           DECIMAL,
    avg_cost AVG_COST,
    CONSTRAINT PK_food PRIMARY KEY (id_food)
);
CREATE SEQUENCE food_sequence
    start 1
    increment 1
    OWNED BY food.id_food;


--tabella RECIPE
CREATE TABLE recipe
(
    id_recipe               BIGINT NOT NULL,
    name                    VARCHAR(50) NOT NULL,
    description             TEXT NOT NULL,
    to_cook                 BOOLEAN NOT NULL,
    image_file_path         VARCHAR(69),
    tot_nutritional_value   INT NOT NULL,
    tot_preparation_time    INT NOT NULL,
    number_ingredient       INT NOT NULL,
    total_cost              AVG_COST NOT NULL,
    difficulty              DIFFICULTY NOT NULL,
    diet                    DIET NOT NULL,
    dish                    DISH NOT NULL,
    CONSTRAINT PK_recipe PRIMARY KEY(id_recipe)
);
CREATE SEQUENCE recipe_sequence
    start 1
    increment 1
    OWNED BY recipe.id_recipe;


--tabella INGREDIENT
CREATE TABLE ingredient
(
    id_ingredient           BIGINT NOT NULL,
    id_recipe               BIGINT,
    id_food                 BIGINT NOT NULL,
    weight                  DECIMAL,
    quantity                DECIMAL,
    is_optional             BOOLEAN NOT NULL,
    CONSTRAINT PK_ingredient PRIMARY KEY(id_ingredient),
    CONSTRAINT FK_ingredient_recipe FOREIGN KEY(id_recipe)
        REFERENCES recipe(id_recipe),
    CONSTRAINT FK_ingredient_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);
CREATE SEQUENCE ingredient_sequence
    start 1
    increment 1
    OWNED BY ingredient.id_ingredient;


--tabella AVOIDING_FOOD
CREATE TABLE avoiding_food
(
    id_avoiding_food        BIGINT NOT NULL,
    id_person               BIGINT NOT NULL,
    id_food                 BIGINT NOT NULL,
    CONSTRAINT PK_avoiding_food PRIMARY KEY(id_avoiding_food),
    CONSTRAINT FK_avoiding_food_person FOREIGN KEY(id_person)
        REFERENCES person(id_person),
    CONSTRAINT FK_avoiding_food_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);
CREATE SEQUENCE avoiding_food_sequence
    start 1
    increment 1
    OWNED BY avoiding_food.id_avoiding_food;


--tabella FOOD_STORAGE
CREATE TABLE food_storage
(
    id_food_storage         BIGINT NOT NULL,
    id_person               BIGINT NOT NULL,
    id_food                 BIGINT NOT NULL,
    quantity                INT NOT NULL,
    CONSTRAINT PK_food_storage PRIMARY KEY(id_food_storage),
    CONSTRAINT FK_food_storage_person FOREIGN KEY(id_person)
        REFERENCES person(id_person),
    CONSTRAINT FK_food_storage_food FOREIGN KEY(id_food)
        REFERENCES food(id_food)
);
CREATE SEQUENCE food_storage_sequence
    start 1
    increment 1
    OWNED BY food_storage.id_food_storage;


--tabella CATEGORY
CREATE TABLE category
(
  id_category               BIGINT NOT NULL,
  name                      VARCHAR(32) NOT NULL,
  CONSTRAINT PK_category PRIMARY KEY(id_category)
);
CREATE SEQUENCE category_sequence
    start 1
    increment 1
    OWNED BY category.id_category;


--tabella FOOD_CATEGORY
CREATE TABLE food_category
(
    id_food_category        BIGINT NOT NULL,
    id_food                 BIGINT NOT NULL,
    id_category             BIGINT NOT NULL,
    CONSTRAINT PK_food_category PRIMARY KEY(id_food_category),
    CONSTRAINT FK_food_category_food FOREIGN KEY(id_food)
        REFERENCES food(id_food),
    CONSTRAINT FK_food_category_category FOREIGN KEY(id_category)
        REFERENCES category(id_category)
);
CREATE SEQUENCE food_category_sequence
    start 1
    increment 1
    OWNED BY food_category.id_food_category;