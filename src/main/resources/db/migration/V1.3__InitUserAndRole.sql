INSERT INTO dictionnarydb.role (id, role) VALUES (1, 'ROLE_USER');
INSERT INTO dictionnarydb.role (id, role) VALUES (2, 'ROLE_ADMIN');

INSERT INTO dictionnarydb.user (id, password, username) VALUES (1, '$2a$10$tw7k5YSkQnOVVuQqIam/..o0sKRqYwfBmYd2dVId17u.lzbbT.Gxm', 'vasek');
INSERT INTO dictionnarydb.user (id, password, username) VALUES (2, '$2a$10$dR35UjYfYT3HEpHLdVWDH.vOPPNjH4jSsGTw17HomBuP8aMlWS9BK', 'kamca');
INSERT INTO dictionnarydb.user (id, password, username) VALUES (3, '$2a$10$lE1iZJEd/.F/OxU5qEdCbumYII63BKhj/0BfeKF50dbAW1zHFaNVC', 'pepa');
INSERT INTO dictionnarydb.user (id, password, username) VALUES (4, '$2a$10$WIgetSkWGR9b5ubOmD4Otef9YlY2VPrCtysZWuD1i2XQH5xr3VXPq', 'user');
INSERT INTO dictionnarydb.user (id, password, username) VALUES (5, '$2a$10$Sx7eiJ3iscnJaSeCXqk08ufQLTtCdjjjva2Cj.PRd.7hsl9row.0G', 'admin');

INSERT INTO dictionnarydb.user_roles (user_id, roles_id) VALUES (1,2);
INSERT INTO dictionnarydb.user_roles (user_id, roles_id) VALUES (2,1);
INSERT INTO dictionnarydb.user_roles (user_id, roles_id) VALUES (3,2);
INSERT INTO dictionnarydb.user_roles (user_id, roles_id) VALUES (4,1);
INSERT INTO dictionnarydb.user_roles (user_id, roles_id) VALUES (5,2);
