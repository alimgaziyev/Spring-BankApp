CREATE TABLE Transaction (
    id INTEGER not null PRIMARY KEY,
    account_id NVARCHAR(40) NOT NULL,
    transaction_type NVARCHAR(80) NOT NULL,
    amount DOUBLE NOT NULL,
    date NVARCHAR(40) NOT NULL,
    is_transferred BOOLEAN NOT NULL
);