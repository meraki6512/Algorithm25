# Write your MySQL query statement below
SELECT email from Person group by Email having count(email) > 1;
-- SELECT DISTINCT(p1.email) from Person p1, Person p2 where p1.id <> p2.id AND p1.email and p2.email;
-- SELECT DISTINCT(p1.email) from Person p1 JOIN Person p2 ON p1.email = p2.email AND p1.id <> p2.id;