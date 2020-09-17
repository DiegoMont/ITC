USE sakila;

DESCRIBE film;
DESCRIBE film_actor;

SELECT title, COUNT(*) AS 'total number of actors'
FROM film JOIN film_actor ON film.film_id = film_actor.film_id
WHERE release_year = 2006 AND length BETWEEN 1 AND 55
GROUP BY title;

delimiter //
DROP PROCEDURE IF EXISTS procedimiento;
CREATE PROCEDURE procedimiento (IN release_year YEAR, IN min_length SMALLINT UNSIGNED, IN max_length SMALLINT UNSIGNED)
BEGIN 
  SELECT title, COUNT(*) AS 'total number of actors'
  FROM film JOIN film_actor ON film.film_id = film_actor.film_id
  WHERE film.release_year = release_year AND length BETWEEN min_length AND max_length
  GROUP BY title;
END//
delimiter ;

CALL procedimiento(2006, 1, 55);

delimiter //
CREATE EVENT evento
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
  CALL procedimiento(2006, 1, 55);
END//
delimiter ;

EXPLAIN SELECT title, COUNT(*) AS 'total number of actors'
FROM film JOIN film_actor ON film.film_id = film_actor.film_id
WHERE release_year = 2006 AND length BETWEEN 1 AND 55
GROUP BY title;

CREATE INDEX idx1 ON film(release_year, length);

EXPLAIN SELECT title, COUNT(*) AS 'total number of actors'
FROM film JOIN film_actor ON film.film_id = film_actor.film_id
WHERE release_year = 2006 AND length BETWEEN 1 AND 55
GROUP BY title;