CREATE SEQUENCE SEQ_INGREDIENT
INCREMENT by 1
START WITH 1;

CREATE TABLE INGREDIENT (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(100),
    QUANTITY NUMBER
);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'tomato', 100);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'prosciutto', 50);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'ton', 70);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'spicy pepper', 30);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'salami', 60);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'mushrooms', 40);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'flour', 200);
INSERT INTO INGREDIENT (ID, NAME, QUANTITY) VALUES (SEQ_INGREDIENT.nextval, 'cheese', 90);

SELECT * FROM INGREDIENT;
CREATE SEQUENCE SEQ_PIZZA
    INCREMENT BY 1
    START WITH 1;

DROP TABLE PIZZA;
CREATE TABLE PIZZA (
       ID NUMBER PRIMARY KEY,
       TYPE VARCHAR2(100),
       COST NUMBER
);
DELETE FROM PIZZA;
INSERT INTO PIZZA (ID, TYPE, COST) VALUES (SEQ_PIZZA.nextval, 'margherita', 100);
INSERT INTO PIZZA (ID, TYPE, COST) VALUES (SEQ_PIZZA.nextval, 'salami', 200);
INSERT INTO PIZZA (ID, TYPE, COST) VALUES (SEQ_PIZZA.nextval, 'prosciutto', 150);
INSERT INTO PIZZA (ID, TYPE, COST) VALUES (SEQ_PIZZA.nextval, 'tonna', 400);
INSERT INTO PIZZA (ID, TYPE, COST) VALUES (SEQ_PIZZA.nextval, 'diavola', 250);

SELECT * FROM PIZZA;
DROP TABLE PIZZA_INGREDIENT;
CREATE TABLE PIZZA_INGREDIENT (
      PIZZA_ID NUMBER,
      INGREDIENT_ID NUMBER,
      QUANTITY NUMBER,
      PRIMARY KEY (PIZZA_ID, INGREDIENT_ID),
      FOREIGN KEY (PIZZA_ID) REFERENCES PIZZA(ID),
      FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENT(ID)
);
DELETE FROM PIZZA_INGREDIENT;
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (11,7,1);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (11,1,5);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (11,8,3);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (12,7,1);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (12,5,10);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (12,8,3);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (13,7,1);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (13,2,5);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (13,8,3);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (13,6,2);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (14,7,1);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (14,8,3);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (14,3,1);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (15,7,1);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (15,5,10);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (15,8,3);
INSERT INTO PIZZA_INGREDIENT (PIZZA_ID, INGREDIENT_ID, QUANTITY) VALUES (15,4,1);

SELECT NAME, P.QUANTITY
FROM INGREDIENT I JOIN PIZZA_INGREDIENT P ON (I.ID= P.INGREDIENT_ID) ;

SELECT * from PIZZA_INGREDIENT;
commit;
SELECT i.NAME, p.QUANTITY
FROM INGREDIENT i JOIN PIZZA_INGREDIENT p ON (p.INGREDIENT_ID = i.ID)
WHERE p.PIZZA_ID = 16;
CREATE SEQUENCE SEQ_CUSTOMER
    INCREMENT by 1
    START WITH 1;

CREATE TABLE CUSTOMER (
      ID NUMBER PRIMARY KEY,
      NAME VARCHAR2(100),
      MONEY NUMBER,
      DRINK VARCHAR2(100),
      COST_DRINK NUMBER,
      SCORE_GAME NUMBER
);
CREATE TABLE RECEIPT_PIZZA (
    RECEIPT_ID NUMBER,
    PIZZA_ID NUMBER,
    PIZZA_SIZE VARCHAR2(50),
    PRIMARY KEY (RECEIPT_ID, PIZZA_ID),
    FOREIGN KEY (RECEIPT_ID) REFERENCES RECEIPT(ID),
    FOREIGN KEY (PIZZA_ID) REFERENCES PIZZA(ID)
);
CREATE SEQUENCE SEQ_RECEIPT
    INCREMENT by 1
    START WITH 1;

CREATE TABLE RECEIPT (
         ID NUMBER PRIMARY KEY,
         CUSTOMER_ID NUMBER,
         DATE_RECEIPT DATE,
         FINAL_COST NUMBER,
         TIP NUMBER,
         FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID)
);
SELECT * from INGREDIENT;
DELETE  from INGREDIENT;

SELECT * from CUSTOMER;
DELETE FROM CUSTOMER;
SELECT * from RECEIPT_PIZZA;
DELETE from RECEIPT_PIZZA;
SELECT * from RECEIPT;
DELETE  FROM RECEIPT;
commit ;

CREATE SEQUENCE SEQ_EMPLOYEE
    INCREMENT by 1
    START WITH 1;

CREATE TABLE EMPLOYEE (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(100),
    AGE NUMBER,
    SALARY NUMBER
);
CREATE SEQUENCE SEQ_EMPSKILL
    INCREMENT by 1
    START WITH 1;

drop table EmployeeSkills;
CREATE TABLE EmployeeSkills (
       id NUMBER PRIMARY KEY,
       employeeID INT,
       customerID INT,
       skillName VARCHAR2(100),
       FOREIGN KEY (employeeID) REFERENCES Employee(id),
       FOREIGN KEY (customerID) REFERENCES CUSTOMER(ID)
);
CREATE TABLE EmployeeRating (
        employee_id INT,
        customer_id INT,
        rating INT,
        PRIMARY KEY (employee_id,customer_id),
        FOREIGN KEY (employee_id) REFERENCES Employee(id),
        FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id)
);
commit ;

INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'John Doe', 30, 50000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Jane Smith', 28, 55000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Emily Johnson', 35, 60000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Michael Brown', 40, 65000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Sarah Davis', 32, 52000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'David Wilson', 45, 70000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Laura Garcia', 29, 48000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'James Martinez', 33, 53000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Linda Rodriguez', 37, 58000);
INSERT INTO Employee (id,name, age, salary) VALUES (SEQ_EMPLOYEE.nextval,'Robert Hernandez', 50, 75000);

INSERT INTO CUSTOMER VALUES (3,'Alin',1000,'Soda',100,10);
INSERT INTO CUSTOMER VALUES (4,'Jon',1000,'Soda',100,10);

select * from customer;
select * from EMPLOYEERATING;
INSERT INTO EmployeeRating (employee_id,customer_id, rating) VALUES (1,2, 8);
INSERT INTO EmployeeRating (employee_id,customer_id, rating) VALUES (1,3,9);
INSERT INTO EmployeeRating (employee_id,customer_id, rating) VALUES (3,2, 7);
INSERT INTO EmployeeRating (employee_id,customer_id, rating) VALUES (5,3, 3);
INSERT INTO EmployeeRating (employee_id, customer_id,rating) VALUES (5,2, 4);

SELECT employee_id, AVG(rating)
FROM EmployeeRating
GROUP BY  employee_id
HAVING AVG(rating)=(SELECT MAX(avg_rating)
                    FROM (SELECT employee_id, AVG(rating) as avg_rating
                          FROM EmployeeRating
                          GROUP BY  employee_id
                         )
                    );

INSERT INTO EmployeeSkills (id, employeeID, customerID, skillName) VALUES (1, 2, 2, 'Java Programming');
INSERT INTO EmployeeSkills (id, employeeID, customerID, skillName) VALUES (2, 3, 2, 'Database Management');

SELECT count(*)
fROM EmployeeSkills
WHERE employeeID=2
group by employeeID;
commit ;
select * from employee;
select * from EmployeeSkills;
select * from EMPLOYEERATING;
delete from EMPLOYEESKILLs;
delete from EMPLOYEERATING;
delete from CUSTOMER;
delete from RECEIPT;
delete from RECEIPT_PIZZA;

select * from CUSTOMER;
select * from RECEIPT_PIZZA;
select * from RECEIPT;
select * from EMPLOYEERATING;
select * from EMPLOYEESKILL;
