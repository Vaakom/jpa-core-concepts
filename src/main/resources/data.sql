insert into person (id, name, location, birth_date) values (1001, 'Nemo', 'Wanderland', sysdate());
insert into person (id, name, location, birth_date) values (1002, 'Piter', 'Neverwere', sysdate());
insert into person (id, name, location, birth_date) values (1003, 'Penny', 'Hawai', sysdate());

insert into course (id, name, created, updated, is_deleted) values (1001, 'Course one', sysdate(), sysdate(), false);
insert into course (id, name, created, updated, is_deleted) values (1002, 'Course two', sysdate(), sysdate(), false);
insert into course (id, name, created, updated, is_deleted) values (1003, 'Course three', sysdate(), sysdate(), false);

insert into passport (id, number) values (4001, 'E6541324');
insert into passport (id, number) values (4002, 'F9173642');
insert into passport (id, number) values (4003, 'A7635118');

insert into student (id, name, passport_id) values (2001, 'Mr First', 4001);
insert into student (id, name, passport_id) values (2002, 'Mr Second', 4002);
insert into student (id, name, passport_id) values (2003, 'Mr Third', 4003);

insert into review (id, rating, description, course_id) values (5001, 'FIVE', 'Great', 1001);
insert into review (id, rating, description, course_id) values (5002, 'FOUR', 'Wonderful', 1001);
insert into review (id, rating, description, course_id) values (5003, 'FIVE', 'Awesome', 1003);

insert into student_course (student_id, course_id) values (2001, 1001);
insert into student_course (student_id, course_id) values (2002, 1001);
insert into student_course (student_id, course_id) values (2003, 1001);
insert into student_course (student_id, course_id) values (2001, 1003);