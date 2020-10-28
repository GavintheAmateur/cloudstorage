CREATE TABLE IF NOT EXISTS USERS
(
    id    INT PRIMARY KEY auto_increment,
    username  VARCHAR(20),
    salt      VARCHAR,
    password  VARCHAR,
    firstname VARCHAR(20),
    lastname  VARCHAR(20),
    created_dt TIMESTAMP,
    created_by VARCHAR,
    modified_dt TIMESTAMP,
    modified_by VARCHAR
);

CREATE TABLE IF NOT EXISTS NOTES
(
    id          INT PRIMARY KEY auto_increment,
    title       VARCHAR(20),
    description VARCHAR(1000),
    user_id          INT,
    created_dt TIMESTAMP,
    created_by VARCHAR,
    modified_dt TIMESTAMP,
    modified_by VARCHAR,
    foreign key (user_id) references USERS (id)
);

CREATE TABLE IF NOT EXISTS FILES
(
    id      INT PRIMARY KEY auto_increment,
    name    VARCHAR,
    content_type VARCHAR,
    size    VARCHAR,
    user_id      INT,
    data    BLOB,
    created_dt TIMESTAMP,
    created_by VARCHAR,
    modified_dt TIMESTAMP,
    modified_by VARCHAR,
    foreign key (user_id) references USERS (id)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS
(
    id INT PRIMARY KEY auto_increment,
    url          VARCHAR(100),
    username     VARCHAR(30),
    key          VARCHAR,
    password     VARCHAR,
    user_id       INT,
    created_dt TIMESTAMP,
    created_by VARCHAR,
    modified_dt TIMESTAMP,
    modified_by VARCHAR,
    foreign key (user_id) references USERS (id)
);
