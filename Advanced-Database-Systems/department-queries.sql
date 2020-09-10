/*Count the number of male employees*/
SELECT COUNT(*) AS "Number of male employees"
FROM employees
WHERE gender = "M";

CREATE INDEX index_gender
ON employees(gender);

/*Count all the employees in a given department*/
SELECT departments.dept_name, COUNT(emp_no)
FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no
GROUP BY departments.dept_no;

/*Select all the employees with more than 1 department*/
WITH count_departments AS 
(SELECT emp_no, COUNT(*) AS num_departments
FROM dept_emp
GROUP BY emp_no) SELECT *
FROM count_departments
WHERE num_departments > 1;

/*Count the number of senior engineers*/
SELECT COUNT(*)
FROM titles
WHERE title LIKE 'Senior Engineer';

CREATE INDEX index_title
ON titles(title);

/*Select all the senior engineers with more than 1 department*/
WITH num_departments AS 
(SELECT titles.emp_no, COUNT(*) AS 'cuenta'
FROM titles JOIN dept_emp ON titles.emp_no = dept_emp.emp_no
WHERE title LIKE 'Senior Engineer'
GROUP BY titles.emp_no) SELECT *
FROM num_departments
WHERE num_departments.cuenta  > 1;

/*Select the titles for the top 10 salaries*/
SELECT titles.title, salaries.salary
FROM salaries JOIN titles ON salaries.emp_no = titles.emp_no
ORDER BY salary DESC
LIMIT 10;

/*Select the information of all the employees considering their manager information*/
SELECT
  employees.emp_no AS "ID",
  employees.first_name AS "First name",
  employees.last_name AS "Last name",
  employees.gender AS "Gender",
  departments.dept_name AS "Department",
  manager.first_name AS "Manager first name",
  manager.last_name AS "Manager last name"
FROM employees
  JOIN dept_emp
    ON employees.emp_no = dept_emp.emp_no
  JOIN departments
    ON dept_emp.dept_no = departments.dept_no
  JOIN dept_manager
    ON dept_emp.dept_no = dept_manager.dept_no
  JOIN employees AS manager
    ON dept_manager.emp_no = manager.emp_no
WHERE dept_emp.to_date > CURDATE()
  AND dept_manager.to_date > CURDATE();