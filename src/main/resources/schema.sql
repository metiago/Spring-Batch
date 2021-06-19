CREATE TABLE IF NOT EXISTS ANIMAL  (
    AID int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id int(11) NOT NULL,
    scientificName VARCHAR(50),
    status VARCHAR(50),
    veterinarian VARCHAR(50),
    created DATE
);
