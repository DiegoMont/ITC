SELECT COUNT(*) AS "Number of male employees"
FROM employees
WHERE gender = "M";

SELECT departments.dept_name, COUNT(emp_no)
FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no
GROUP BY dept_no;

SELECT emp_no
FROM dept_emp
GROUP BY emp_no
WHERE COUNT(*) > 1;
