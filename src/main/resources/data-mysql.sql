use discord;

INSERT INTO user(usr_id,usr_email, usr_firstname, usr_lastname, usr_password)
    value(1,"pierre.martin@message.fr","Pierres","Nath","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (2,"julien.cans@message.fr","Julien","Cansell","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (3,"lacus@yahoo.org","Angela Graves","Celeste Henry","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (4,"mauris.non@aol.couk","Risa Clements","Wanda Ware","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (5,"bibendum.sed.est@yahoo.couk","Melissa Rowe","Gillian Hurley","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (6,"magnis.dis.parturient@protonmail.edu","Zena Emerson","Desirae Hopkins","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (7,"augue.scelerisque@yahoo.couk","Jerome Coffey","Robin Cote","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (8,"diam.nunc@icloud.ca","Carter Espinoza","Hyacinth Mills","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (9,"sociis.natoque.penatibus@yahoo.org","Kennedy Oneal","Dexter Melendez","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (10,"montes@icloud.net","Savannah Brooks","Raya Frank","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (11,"tempor.bibendum@protonmail.couk","Daryl Conrad","Daphne Zamora","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (12,"mauris@aol.org","Ignatius Nichols","Zenaida Valencia","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (13,"urna.suscipit@aol.couk","Bert Yang","Jolie Knox","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (14,"eget@protonmail.com","Jamalia Roberson","Robin Malone","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (15,"pede.cum@google.ca","Charde Trujillo","Carlos Ball","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (16,"blandit.viverra@icloud.ca","Trevor Mathews","Stuart Green","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (17,"mauris@hotmail.net","Ariana Kaufman","Noah Calhoun","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (18,"non@aol.org","Maya Church","Rana Tyson","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (19,"nulla.dignissim@hotmail.com","Yoko Mckay","Kirsten Bruce","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (20,"porttitor.vulputate@google.net","Matthew Lane","Myles Brady","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (21,"nunc.ullamcorper@yahoo.com","Nigel Schroeder","Naida Peters","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (22,"ac@outlook.org","Francesca Parsons","Bernard Travis","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y");

INSERT INTO role(rol_id, rol_name)
    VALUE(1,0),(2,1);

INSERT INTO user_role(user_usr_id, role_rol_id)
    VALUE (1,1),(1,2),(2,1),
    (3,1),(4,1),(5,1),
    (6,1),(7,1),(8,1),
    (9,1),(10,1),(11,1),
    (12,1),(13,1),(14,1),
    (15,1),(16,1),(17,1),
    (18,1),(19,1),(20,1),
    (21,1),(22,1);

INSERT INTO channel(cha_id, cha_name, cha_visibility)
    VALUE (1,"DEVLOG Java",1),(2,"DEVLOG C#",0);

INSERT INTO user_group(gro_id, gro_name)
    VALUE (1,"Groupe numero 1"),
    (2,"Groupe numero 2");

INSERT INTO is_member_of(gro_id, usr_id, cha_id)
    VALUE (1,1,1), (2,2,2),
    (1,3,1), (2,4,2),
    (1,5,1), (2,6,2),
    (1,7,1), (2,8,2),
    (1,9,1), (2,10,2),
    (1,11,1), (2,12,2),
    (1,13,1), (2,14,2),
    (1,15,1), (2,16,2),
    (1,17,1), (2,18,2),
    (1,19,1), (2,20,2),
    (1,21,1), (2,22,2);

INSERT INTO file (fil_id, fil_name, fil_path)
    VALUE(1,"File de test","Path de test");

INSERT INTO subject(sub_id, sub_sent_at)
    VALUE(1,'2023-05-23 11:51:15.000000');

INSERT INTO message (msg_content, sub_id, msg_file, msg_sender)
    VALUE("Message de test",1,1,1);
