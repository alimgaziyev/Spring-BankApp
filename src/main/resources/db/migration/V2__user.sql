CREATE TABLE Users
(
id IDENTITY NOT NULL primary key,
email NVARCHAR(80) NOT NULL,
encoded_password NVARCHAR(80)  NOT NULL
);