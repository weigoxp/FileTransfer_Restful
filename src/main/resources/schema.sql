CREATE TABLE IF NOT EXISTS metaData (
                                     id integer NOT NULL AUTO_INCREMENT,
                                     name varchar(200) DEFAULT NULL,
                                     size varchar(64) DEFAULT NULL,
                                     type varchar(10) DEFAULT NULL,
                                     PRIMARY KEY (id)
);