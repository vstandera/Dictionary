CREATE TABLE dictionnarydb.role (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE dictionnarydb.role_users (
  `role_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FKipeyaf3dve9njdrl1t23ndidv` (`users_id`),
  KEY `FKele6ufqrv6w1uoxqw6h1vkki0` (`role_id`)
);

create table dictionnarydb.sentence
(
	id bigint auto_increment
		primary key,
	local_date_time datetime null,
	number_of_view int not null,
	sentence_usage_id bigint null
);

create index FK3xollxfibh1dj99s2egafik9e
	on sentence (sentence_usage_id);

create table dictionnarydb.sentence_usage
(
	id bigint auto_increment
		primary key,
	sentence varchar(255) null
);

create table dictionnarydb.sentence_words
(
	sentence_id bigint not null,
	words_id bigint not null
);

create index FK8yfmimsjlvneic0ol135aggt6
	on sentence_words (sentence_id);

create index FKp0sa0048ue3dr422yj1ylg7g4
	on sentence_words (words_id);

create table dictionnarydb.user
(
	id bigint auto_increment
		primary key,
	password varchar(255) null,
	username varchar(255) null
);

create table dictionnarydb.user_roles
(
	user_id bigint not null,
	roles_id bigint not null
);

create index FK55itppkw3i07do3h7qoclqd4k
	on user_roles (user_id);

create index FKj9553ass9uctjrmh0gkqsmv0d
	on user_roles (roles_id);

CREATE TABLE dictionnarydb.word (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) DEFAULT NULL,
  `word_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

