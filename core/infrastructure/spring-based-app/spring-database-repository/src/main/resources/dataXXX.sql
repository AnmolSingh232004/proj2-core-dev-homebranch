insert into activity_type
values
(1, 'GYM desc', 'GYM'),
(2, 'CAR desc', 'CAR'),
(3, 'BASKET desc', 'BASKET'),
(4, 'HOUSE desc', 'HOUSE');

insert into activity
values
(1, 'STRENGTH', 'UpperChest_S', 1),
(2, 'HYPERTROPHY', 'PullOverBack_H', 1),
(3, 'GAS', 'GAS_MONTHLY', 4),
(4, 'GAS', 'GAS_MAINTENANCE', 4),
(5, 'CAR_PARTS', 'NewTires', 2),
(6, 'CAR_CASUAL', 'Petrol', 2);

insert into day_activity (id, amount_factor, date, logged_reps, todo_kg, todo_reps, todo_sets, activity_id, activitytype_id, is_logged)
values
    --amountFactor, date, loggedReps, todokg, todoreps, todosets, activityId, activityTypeId, isLogged
    ('1', NULL, '2021-01-01', NULL, '33', '3', '3', '1', '1', FALSE),
    ('2', '25', '2021-01-01', '12', '50', '4', '3', '2', '1', TRUE),
    ('3', '150', '2021-01-01', NULL, NULL, NULL, NULL, '3', '4', FALSE),
    ('4', '60', '2021-01-01', NULL, NULL, NULL, NULL, '4', '4', FALSE),
    ('5', '120', '2021-01-01', NULL, NULL, NULL, NULL, '5', '2', FALSE),
    ('6', '25', '2021-01-01', NULL, NULL, NULL, NULL, '6', '2', FALSE),
    ('7', NULL, '2021-04-04', '12', '50', '5', '3', '2', '1', TRUE);


-- TEST QUERIES ====>

-- ----- SHOW 3RD BIGGEST AMOUNT_FACTOR -----
-- SELECT * FROM day_activity
-- order by CAST(amount_factor AS INT) DESC
--     limit 2,1;

-- ----- SHOW 3RD BIGGEST AMOUNT_FACTOR (WITHOUT TOP/LIMIT)-----
-- SELECT amount_factor
-- FROM day_activity d1
-- where 3 =
--       (SELECT COUNT(amount_factor)
--        FROM day_activity d2
--        WHERE CAST(d2.amount_factor AS INT) > CAST(d1.amount_factor AS INT));
--
--TIPS TO UNDESTAND IT: Per d1 element, we count all d2 elements that are higher than current d1 element.
-- EXAMPLE: If there are 3 d2's higher that current d1, then d1 is 4rth.


-- ----- FIND DUPLICATE ROWS IN TABLE-----
-- SELECT COUNT(AMOUNT_FACTOR)
-- FROM DAY_ACTIVITY
-- GROUP BY AMOUNT_FACTOR

-- ----- SHOW ONLY EVEN/ODD ROWS-----
-- SELECT *
-- FROM DAY_ACTIVITY
-- WHERE mod(ID,2) != 0
--
-- SELECT *
-- FROM DAY_ACTIVITY
-- WHERE mod(ID,2) == 0

-- ----- SHOW ROW WITH MIN/MAX ID-----
-- SELECT *
-- FROM DAY_ACTIVITY
-- where ID=
--       (select max(ID) from DAY_ACTIVITY)

-- ----- PARTITION: IN RESULTSET, PART THEM BY A COL, AND EXECUTE SOME AGGR FUNCTION, LIKE MAX-----
-- SELECT is_logged, date, logged_reps, activitytype_id, activity_id,
--     todo_kg,
--     max(todo_kg) over (partition by activitytype_id) AS COL44
-- FROM DAY_ACTIVITY

-- ----- CREATE NEW TABLE WITH EXISTING SCHEMA AND NO DATA-----
-- CREATE TABLE TABLEXXX AS
--     SELECT * FROM TABLEORIGINAL
-- WHERE 4=5;

-- ----- SELECT LAST 3 DAYACTIVITIES AND SHOW THEM AS ASC-----
-- SELECT * FROM
--     (SELECT * FROM DAY_ACTIVITY ORDER BY ID DESC LIMIT 3)
--         temp
-- ORDER BY ID ASC


-- ----- SELECT day activities HAVING SAME ACTTYPE-ID -----
-- SELECT DISTINCT D1.ID, D1.AMOUNT_FACTOR, D1.ACTIVITY_ID, D1.ACTIVITYTYPE_ID
-- FROM DAY_ACTIVITY D1, DAY_ACTIVITY D2
-- WHERE D1.ACTIVITYTYPE_ID =  D2.ACTIVITYTYPE_ID
--   AND D1.ID != D2.ID;


-- ----- JOIN DAYACTIVITIES AND ACTIVITYTYPES -----
-- SELECT DAY_ACTIVITY.AMOUNT_FACTOR, ACTIVITY_TYPE.DESCRIPTION
-- FROM DAY_ACTIVITY
--          LEFT JOIN ACTIVITY_TYPE
--                    ON DAY_ACTIVITY.ACTIVITYTYPE_ID = ACTIVITY_TYPE.ID


-- ----- COUNT SAME AMOUNT_FACTORS / GROUP BY -----
-- SELECT AMOUNT_FACTOR, COUNT(1) FROM DAY_ACTIVITY GROUP BY AMOUNT_FACTOR;


-- ----- TEST GROUP BY without H2 issues -----
-- CREATE TABLE visits (`userId` int, `webpageId` int);
--
-- INSERT INTO visits (`userId`, `webpageId`)
-- VALUES
--     (0, 123),
--     (0, 124),
--     (1, 123),
--     (2, 123),(2, 143),(2, 153);

-- QUERIES  1, 2 -------
-- SELECT userId, COUNT(DISTINCT webpageId) AS count
-- FROM visits
-- GROUP BY userId
-- HAVING COUNT(DISTINCT webpageId) > 1;
--
-- SELECT userId
-- FROM visits
-- GROUP BY userId
-- HAVING COUNT(webpageId) > 1

-- RESULTS OF 1, 2 -------
-- userId	count
-- 0	2
-- 2	3
--
-- userId
-- 0
-- 2


-- ----- PARTITION EXAMPLE -----
-- SELECT
--     AMOUNT_FACTOR,
--     IS_LOGGED,
--     ACTIVITYTYPE_ID,
--     COUNT(*) OVER (PARTITION BY ACTIVITYTYPE_ID)
-- FROM DAY_ACTIVITY