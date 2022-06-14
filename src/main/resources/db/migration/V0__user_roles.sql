CREATE TABLE User_Roles
(
    id IDENTITY NOT NULL primary key,
    user_id INTEGER not null,
    role_id INTEGER not null
);