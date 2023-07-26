create table tickets(
    id SERIAL PRIMARY KEY,
    users varchar(50),
    creationdate date,
    updatedate date,
    status varchar(50)
)


