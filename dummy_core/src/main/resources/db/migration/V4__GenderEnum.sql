CREATE TYPE gender AS ENUM ('Male', 'Female');

ALTER TABLE student ALTER COLUMN gender TYPE gender USING(gender::gender);