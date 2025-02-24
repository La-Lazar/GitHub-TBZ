CREATE SCHEMA IF NOT EXISTS mein_schema DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE mein_schema.tbl_fahrer (
    Fahrer_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50),
    Vorname VARCHAR(30),
    Geburtsdatum DATETIME,
    Telefonnummer VARCHAR(12),
    Einkommen FLOAT(10,2)
) CHARACTER SET utf8mb4;

CREATE TABLE mein_schema.tbl_disponent (
    Disponent_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50),
    Vorname VARCHAR(30),
    Geburtsdatum DATETIME,
    Telefonnummer VARCHAR(12),
    Einkommen FLOAT(10,2)
) CHARACTER SET utf8mb4;

DROP TABLE mein_schema.tbl_fahrer;

CREATE TABLE mein_schema.tbl_fahrer (
    Fahrer_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50),
    Vorname VARCHAR(30),
    Geburtsdatum DATETIME,
    Telefonnummer VARCHAR(12),
    Einkommen FLOAT(10,2)
) CHARACTER SET utf8mb4;


CREATE TABLE mein_schema.tbl_Mitarbeiter (
    MA_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50),
    Vorname VARCHAR(30),
    Geburtsdatum DATETIME,
    Telefonnummer VARCHAR(12),
    Einkommen FLOAT(10,2)
) CHARACTER SET utf8mb4;


ALTER TABLE mein_schema.tbl_Mitarbeiter 
MODIFY Name VARCHAR(50) CHARACTER SET latin1,
MODIFY Vorname VARCHAR(30) CHARACTER SET latin1;

ALTER TABLE mein_schema.tbl_fahrer 
DROP COLUMN Name,
DROP COLUMN Vorname,
DROP COLUMN Telefonnummer;

ALTER TABLE mein_schema.tbl_disponent 
DROP COLUMN Name,
DROP COLUMN Vorname,
DROP COLUMN Telefonnummer;

ALTER TABLE mein_schema.tbl_Mitarbeiter 
ADD CONSTRAINT fk_fahrer FOREIGN KEY (MA_ID) REFERENCES mein_schema.tbl_fahrer(Fahrer_ID),
ADD CONSTRAINT fk_disponent FOREIGN KEY (MA_ID) REFERENCES mein_schema.tbl_disponent(Disponent_ID);

