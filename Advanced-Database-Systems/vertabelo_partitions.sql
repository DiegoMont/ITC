USE vertabelo;

/*Count all the measures from 2016 on*/
SELECT sql_no_cache COUNT(*)
FROM measures
WHERE measure_timestamp > "2015-12-31 23:59";

/*Partition the table using a range partition for the measure_timestamp column: less than 2015, less than 2016, all the remaining rows*/
ALTER TABLE measures
PARTITION BY RANGE ( YEAR(measure_timestamp) ) (
  PARTITION p0 VALUES LESS THAN (2015),
  PARTITION p1 VALUES LESS THAN (2016),
  PARTITION p2 VALUES LESS THAN MAXVALUE
);