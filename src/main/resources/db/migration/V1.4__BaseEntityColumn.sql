ALTER TABLE sentence add (create_date datetime null,change_date datetime null, create_user_name varchar(255) null, modify_user_name varchar(255) null);

ALTER TABLE word add (create_date datetime null,change_date datetime null, create_user_name varchar(255) null, modify_user_name varchar(255) null);

ALTER TABLE sentence_usage add (create_date datetime null,change_date datetime null, create_user_name varchar(255) null, modify_user_name varchar(255) null);
