use discord;

INSERT INTO user(usr_id,usr_email, usr_firstname, usr_lastname, usr_password)
    value(1,"pierre.martin@message.fr","Pierres","Nath","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y"),
    (2,"julien.cans@message.fr","Julien","Cansell","$2a$10$PNdDRl/YjYp8ayQHlVvBseWWdxWUmweQ99//vNmlRCMkynwpyk.8y");

INSERT INTO role(rol_id, rol_name)
    VALUE(1,0),(2,1);

INSERT INTO user_role(user_usr_id, role_rol_id)
    VALUE (1,1),(1,2),(2,1);

INSERT INTO channel(cha_id, cha_name, cha_visibility)
    VALUE (1,"DEVLOG Java",1),(2,"DEVLOG C#",0);

INSERT INTO user_group(gro_id, gro_name)
    VALUE (1,"Groupe numero 1"),
    (2,"Groupe numero 2");

INSERT INTO is_member_of(gro_id, usr_id, cha_id)
    VALUE (1,1,1), (2,2,2);

INSERT INTO file (fil_id, fil_name, fil_path)
    VALUE(1,"File de test","Path de test");

INSERT INTO subject(sub_id, sub_sent_at)
    VALUE(1,'2023-05-23 11:51:15.000000');

INSERT INTO message (msg_content, sub_id, msg_file, msg_sender)
    VALUE("Message de test",1,1,1);
