/*The actor with the most number of movies (TOP 20)*/
SELECT actor.first_name, actor.last_name, COUNT(*) FROM actor JOIN film_actor ON actor.actor_id = film_actor.actor_id GROUP BY actor.last_name ORDER BY COUNT(*) DESC LIMIT 20;

/*The movies' titles of the actor with the most number of movies*/
WITH mejor_actor AS (SELECT film_actor.actor_id, COUNT(*) FROM actor JOIN film_actor ON actor.actor_id = film_actor.actor_id GROUP BY actor.last_name ORDER BY COUNT(*) DESC LIMIT 1) SELECT film.title FROM film JOIN film_actor ON film.film_id = film_actor.film_id JOIN mejor_actor ON mejor_actor.actor_id = film_actor.actor_id;

/*The movies with the most number of actors(20)*/
SELECT *, COUNT(actor_id) FROM film JOIN film_actor ON film.film_id = film_actor.film_id GROUP BY actor_id ORDER BY COUNT(actor_id) DESC LIMIT 20;

/*The countries with the highest number of customers (TOP 20)*/
SELECT country, COUNT(*) FROM customer JOIN address ON customer.address_id = address.address_id JOIN city ON city.city_id = address.city_id JOIN country ON country.country_id = city.country_id GROUP BY country ORDER BY COUNT(*) DESC LIMIT 20;

/*The number of customers per country*/
SELECT country, COUNT(*) FROM customer JOIN address ON customer.address_id = address.address_id JOIN city ON city.city_id = address.city_id JOIN country ON country.country_id = city.country_id GROUP BY country;

/*The country with the maximum number of customers*/
SELECT country, COUNT(*) FROM customer JOIN address ON customer.address_id = address.address_id JOIN city ON city.city_id = address.city_id JOIN country ON country.country_id = city.country_id GROUP BY country ORDER BY COUNT(*) LIMIT 1;

/*The top rented movies (TOP 20)*/
SELECT title, COUNT(*) FROM rental JOIN inventory ON rental.inventory_id = inventory.inventory_id JOIN film ON film.film_id = inventory.film_id GROUP BY title ORDER BY COUNT(*) DESC LIMIT 20;

/*The customers who rent more movies (TOP 20)*/
SELECT rental.customer_id, COUNT(*) FROM customer JOIN rental ON customer.customer_id = rental.customer_id GROUP BY rental.customer_id ORDER BY COUNT(*) DESC LIMIT 20;

/*The movies' titles rented by the customer with more rents*/
WITH mejor_cliente AS (SELECT rental.customer_id, COUNT(*) FROM customer JOIN rental ON customer.customer_id = rental.customer_id GROUP BY rental.customer_id ORDER BY COUNT(*) DESC LIMIT 1) SELECT title FROM mejor_cliente JOIN rental ON rental.customer_id = mejor_cliente.customer_id JOIN inventory ON inventory.inventory_id = rental.inventory_id JOIN film ON film.film_id = inventory.film_id;

/*The movies' titles that have never been rented*/
SELECT title FROM film LEFT JOIN inventory ON film.film_id = inventory.film_id WHERE inventory.inventory_id IS NULL;

/*The category with the highest number of movies*/
SELECT name FROM category JOIN film_category ON category.category_id = film_category.category_id GROUP BY category.category_id ORDER BY COUNT(*) DESC LIMIT 1;

/*The number of movies per category*/
SELECT name, COUNT(*) FROM category JOIN film_category ON category.category_id = film_category.category_id GROUP BY category.category_id;

/*The number of rentals per category*/
SELECT category.name, COUNT(*) FROM inventory JOIN film_category ON inventory.film_id = film_category.film_id JOIN category ON film_category.category_id = category.category_id GROUP BY category.category_id;

/*Obtain a list with all the movies of a given actor*/
DELIMITER $$
DROP PROCEDURE IF EXISTS film_list;
CREATE PROCEDURE film_list(IN v_nombre VARCHAR(30), IN v_apellido VARCHAR(30), OUT v_lista VARCHAR(50))
BEGIN
  SELECT title FROM actor JOIN film_actor ON actor.actor_id = film_actor.actor_id JOIN film ON film.film_id = film_actor.film_id WHERE @nombre = film.first_name AND @apellido = film.last_name;
END $$
DELIMITER ;
