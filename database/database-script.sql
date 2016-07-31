#create the database if it doesn't exist and then use it.
CREATE DATABASE IF NOT EXISTS SampleAPI;
USE SampleAPI;

#drop all the tables.
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `user`;

#create all the tables again.

#create user table.
CREATE TABLE user
(
  profilename VARCHAR(30),
  firstname VARCHAR(30),
  lastname VARCHAR(30),
  PRIMARY KEY(profilename)
);

#create post table.
CREATE TABLE post
(
  postid VARCHAR(36),
  author VARCHAR(30),
  message VARCHAR(500),
  created DATE,
  PRIMARY KEY(postid),
  FOREIGN KEY(author) REFERENCES user(profilename) ON DELETE CASCADE
);

#seed the database (using https://www.mockaroo.com/).

#seed user table.
INSERT INTO user VALUES ('nrussell0', 'Norma', 'Russell');
INSERT INTO user VALUES ('jlawrence1', 'Jessica', 'Lawrence');
INSERT INTO user VALUES ('dcox2', 'Deborah', 'Cox');
INSERT INTO user VALUES ('jtucker3', 'Joan', 'Tucker');
INSERT INTO user VALUES ('dgarza4', 'Doris', 'Garza');
INSERT INTO user VALUES ('ccunningham5', 'Clarence', 'Cunningham');
INSERT INTO user VALUES ('jhenderson6', 'Joyce', 'Henderson');
INSERT INTO user VALUES ('cburns7', 'Carl', 'Burns');
INSERT INTO user VALUES ('tcarter8', 'Theresa', 'Carter');
INSERT INTO user VALUES ('tbutler9', 'Theresa', 'Butler');
INSERT INTO user VALUES ('gsimsa', 'Gregory', 'Sims');
INSERT INTO user VALUES ('cbaileyb', 'Christina', 'Bailey');
INSERT INTO user VALUES ('mwestc', 'Marie', 'West');
INSERT INTO user VALUES ('bkennedyd', 'Barbara', 'Kennedy');
INSERT INTO user VALUES ('csimpsone', 'Cheryl', 'Simpson');
INSERT INTO user VALUES ('pbowmanf', 'Philip', 'Bowman');
INSERT INTO user VALUES ('ahuntg', 'Alice', 'Hunt');
INSERT INTO user VALUES ('pbrownh', 'Philip', 'Brown');
INSERT INTO user VALUES ('mcunninghami', 'Michael', 'Cunningham');
INSERT INTO user VALUES ('bgordonj', 'Benjamin', 'Gordon');

#seed post table.
INSERT INTO post VALUES ('1c2010cf-daaa-429f-8916-80c1fb6446e2', 'bgordonj', 'Aenean auctor gravida sem.', '2016-04-07 00:08:18');
INSERT INTO post VALUES ('e769f6e4-4707-4859-b57e-bb9928bc7d58', 'mcunninghami', 'Integer ac neque.', '2016-03-03 10:22:41');
INSERT INTO post VALUES ('1d7096cb-5816-4046-9ba2-2f6a609d7a6e', 'mcunninghami', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue.', '2015-08-13 16:43:50');
INSERT INTO post VALUES ('58273870-e863-44c7-b7ed-3a4902c467a3', 'mcunninghami', 'Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.', '2016-05-03 16:28:11');
INSERT INTO post VALUES ('1be03c1a-cac0-490b-a291-a44d886fd23c', 'mcunninghami', 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '2016-07-09 15:22:51');
INSERT INTO post VALUES ('bf440df4-dc62-4af3-875a-6b542af85fe8', 'pbowmanf', 'Fusce consequat. Nulla nisl.', '2016-03-24 17:10:59');
INSERT INTO post VALUES ('8dcaa9f8-c0ba-4d64-b6ec-f05d0c8d9fd8', 'gsimsa', 'Proin risus. Praesent lectus.', '2015-08-22 18:35:52');
INSERT INTO post VALUES ('12f65efc-2989-4977-8d37-e761f5a19585', 'bgordonj', 'Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '2016-06-25 02:59:17');
INSERT INTO post VALUES ('c2d3b9f4-d64d-4cbe-84a1-b1e744205cae', 'pbowmanf', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '2016-05-06 11:34:31');
INSERT INTO post VALUES ('7dd3a97f-93bb-4a03-9a4e-445d54b30004', 'jtucker3', 'Etiam faucibus cursus urna.', '2015-09-28 14:33:20');
INSERT INTO post VALUES ('cd892ec9-c8db-48c2-bd83-a39cc08bf9c9', 'bgordonj', 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', '2016-03-31 11:14:35');
INSERT INTO post VALUES ('6ae4f26e-c5be-493c-b558-e5675e8a4209', 'cburns7', 'Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', '2016-03-05 00:03:22');
INSERT INTO post VALUES ('60ef7e18-b530-44b1-8bd4-706b3531f3af', 'cburns7', 'Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.', '2016-01-21 23:09:12');
INSERT INTO post VALUES ('22ba66f5-1001-4a5d-85ce-e52716a07cf0', 'cburns7', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '2016-01-16 10:06:08');
INSERT INTO post VALUES ('662515c6-4927-4a93-a6f5-33700a7076d1', 'bgordonj', 'Integer non velit.', '2016-06-14 23:20:32');
INSERT INTO post VALUES ('77ea88a4-bc1e-4632-a6ce-d5bc7c56fe58', 'jtucker3', 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '2016-05-06 15:33:03');
INSERT INTO post VALUES ('4890f2fb-2dc3-4bf2-8427-daf38079a80f', 'csimpsone', 'Aenean fermentum.', '2016-01-16 01:40:46');
INSERT INTO post VALUES ('58ecbc1a-4a6d-46b9-9f2c-e5fb5f3d304f', 'csimpsone', 'Praesent blandit lacinia erat.', '2015-11-08 10:25:49');
INSERT INTO post VALUES ('536ae0bc-6d48-4d03-b919-ebf8e5827f4b', 'csimpsone', 'In eleifend quam a odio.', '2016-07-05 16:31:08');
INSERT INTO post VALUES ('e2299674-405b-43ff-aea5-2e3c6709eeac', 'jtucker3', 'Curabitur at ipsum ac tellus semper interdum.', '2015-11-19 03:38:21');
INSERT INTO post VALUES ('055d0d23-399b-47c4-9a33-490f03db38f2', 'dcox2', 'Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', '2016-02-11 05:37:09');
INSERT INTO post VALUES ('66564e52-4c91-480c-9ffe-2239f8fc279c', 'pbrownh', 'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', '2016-05-04 19:46:37');
INSERT INTO post VALUES ('c3e5e3f4-8a65-4c80-a552-12f7fed132d3', 'dcox2', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '2016-05-03 09:22:20');
INSERT INTO post VALUES ('77cf4eef-2986-436b-81a6-1bd1b39bdaed', 'bgordonj', 'Morbi non quam nec dui luctus rutrum. Nulla tellus.', '2016-01-05 14:17:58');
INSERT INTO post VALUES ('2796c7c9-6717-4424-8d03-57b4bfff906b', 'pbrownh', 'Mauris lacinia sapien quis libero.', '2016-03-04 13:28:24');
INSERT INTO post VALUES ('a604a8e7-4c3a-44c8-a6eb-496783a4166d', 'pbrownh', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.', '2016-03-04 20:35:01');
INSERT INTO post VALUES ('9d76b806-3726-489c-a759-66401f2eabc4', 'dcox2', 'Aliquam quis turpis eget elit sodales scelerisque.', '2015-12-15 03:26:30');
INSERT INTO post VALUES ('8a0d10e7-1a25-46ad-9e95-e6334bff3f06', 'bgordonj', 'Proin eu mi. Nulla ac enim.', '2016-07-02 07:55:49');
INSERT INTO post VALUES ('e7425889-c7e4-4f15-a8bc-3e1db3f14030', 'dcox2', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.', '2015-10-03 03:20:02');
INSERT INTO post VALUES ('a3553cb8-bf6c-46a8-be8a-12e9e8522db5', 'jlawrence1', 'Sed accumsan felis. Ut at dolor quis odio consequat varius.', '2016-02-19 19:41:10');
INSERT INTO post VALUES ('28f96b14-ddc6-464e-84f1-fc1943d9ea72', 'tbutler9', 'Aenean sit amet justo. Morbi ut odio.', '2015-10-06 14:20:26');
INSERT INTO post VALUES ('16e27c19-f64f-4062-ae60-a68c525212de', 'tbutler9', 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor.', '2016-04-01 23:32:49');
INSERT INTO post VALUES ('545addf1-6d9d-4eb9-a96d-2158ef3a53d6', 'tbutler9', 'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', '2016-04-20 09:47:03');
INSERT INTO post VALUES ('1a5d1f8c-599d-4074-a968-7f314f63bd0f', 'bgordonj', 'Phasellus sit amet erat. Nulla tempus.', '2015-09-09 20:31:52');
INSERT INTO post VALUES ('51cdcde9-e41d-4613-a6b0-fc6eec171523', 'pbrownh', 'In hac habitasse platea dictumst.', '2016-07-07 08:12:00');
INSERT INTO post VALUES ('f2f35523-fcd5-49df-8c1e-07cd9394704f', 'dgarza4', 'Suspendisse potenti. Nullam porttitor lacus at turpis.', '2016-05-26 06:54:24');
INSERT INTO post VALUES ('50c91f50-246b-450d-b307-65876d9ad06e', 'tcarter8', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.', '2016-03-08 11:53:14');
INSERT INTO post VALUES ('6ccfee65-a8d8-4b99-b2bb-05eb35b0c654', 'mwestc', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.', '2015-12-03 10:25:46');
INSERT INTO post VALUES ('178297d9-cde4-4bfa-9894-4e080af0acd7', 'tcarter8', 'Vivamus vestibulum sagittis sapien.', '2015-10-06 15:00:56');
INSERT INTO post VALUES ('317a32f8-b4b0-41c9-8ed3-510e7cc9fd5c', 'tcarter8', 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus.', '2016-01-22 06:56:34');
INSERT INTO post VALUES ('e3e18c56-a5b4-4758-866e-06c4f2d08746', 'mwestc', 'Donec quis orci eget orci vehicula condimentum.', '2015-09-19 13:10:55');
INSERT INTO post VALUES ('dfa97df4-94b2-46cc-afd7-938c20efaf3a', 'mwestc', 'Cras in purus eu magna vulputate luctus.', '2016-07-12 12:16:46');
INSERT INTO post VALUES ('7bb9f4ec-a012-4a9f-aaa6-2540f83e0143', 'mwestc', 'In sagittis dui vel nisl. Duis ac nibh.', '2016-02-14 07:36:09');
INSERT INTO post VALUES ('c79b51a2-685a-4821-8d54-1ef22efdd6ad', 'mwestc', 'Nulla facilisi.', '2016-07-28 15:04:53');
INSERT INTO post VALUES ('f89ce3e0-db4f-4105-afd5-05c79e6e6658', 'dgarza4', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', '2015-11-10 10:28:26');
INSERT INTO post VALUES ('4ef20c6c-df00-4b4e-9ec3-40613c92036b', 'dgarza4', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '2016-07-23 21:37:42');
INSERT INTO post VALUES ('4bc7a6b5-05f4-4336-8d4f-bd4cc818370b', 'mwestc', 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', '2016-06-25 16:11:36');
INSERT INTO post VALUES ('0a578ac2-70df-46ba-a412-4cfca93dc6b0', 'mwestc', 'Suspendisse ornare consequat lectus.', '2016-06-15 03:57:47');
INSERT INTO post VALUES ('385e39d3-2cf1-44d5-a672-a8f15b49ff59', 'mwestc', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.', '2016-01-04 20:04:48');
INSERT INTO post VALUES ('eb4e7646-d607-41e1-b873-4973be1c91a2', 'mwestc', 'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', '2016-02-14 05:07:09');