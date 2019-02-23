SET search_path = test;

INSERT INTO course VALUES (1, 'Math');
INSERT INTO course VALUES (2, 'Database');
INSERT INTO course VALUES (3, 'Java');
INSERT INTO course VALUES (4, 'Compiler');

SELECT pg_catalog.setval('course_cid_seq', 4, true);

INSERT INTO student VALUES (1, 'Rugal');
INSERT INTO student VALUES (2, 'Adelheid');
INSERT INTO student VALUES (3, 'Orochi');

SELECT pg_catalog.setval('student_sid_seq', 3, true);

INSERT INTO registration VALUES (1, 1, 1, 100);
INSERT INTO registration VALUES (2, 1, 2, 100);
INSERT INTO registration VALUES (3, 1, 3, 100);
INSERT INTO registration VALUES (4, 1, 4, 99);
INSERT INTO registration VALUES (5, 2, 1, 90);
INSERT INTO registration VALUES (6, 2, 2, 91);
INSERT INTO registration VALUES (7, 2, 3, 97);
INSERT INTO registration VALUES (8, 2, 4, 87);
INSERT INTO registration VALUES (9, 3, 1, 60);
INSERT INTO registration VALUES (10, 3, 2, 63);
INSERT INTO registration VALUES (11, 3, 3, 100);

SELECT pg_catalog.setval('registration_rid_seq', 11, true);
