CREATE DATABASE Autoladen;

USE Autoladen;

CREATE TABLE Auto (
    AutoID INT PRIMARY KEY,
    Marke VARCHAR(50),
    Modell VARCHAR(50),
    Baujahr INT,
    Preis DECIMAL(10, 2)
);

CREATE TABLE Verkaeufer (
    VerkaeuferID INT PRIMARY KEY,
    Name VARCHAR(100),
    Ort VARCHAR(100)
);

CREATE TABLE Verkauf (
    VerkaufID INT PRIMARY KEY,
    AutoID INT,
    VerkaeuferID INT,
    Verkaufsdatum DATE,
    FOREIGN KEY (AutoID) REFERENCES Auto(AutoID),
    FOREIGN KEY (VerkaeuferID) REFERENCES Verkaeufer(VerkaeuferID)
);

-- Autos
INSERT INTO Auto VALUES
(1, 'BMW', '3er', 2018, 23000.00),
(2, 'Audi', 'A4', 2019, 25000.00),
(3, 'VW', 'Golf', 2016, 15000.00),
(4, 'Tesla', 'Model 3', 2021, 35000.00);

-- Verkäufer
INSERT INTO Verkaeufer VALUES
(1, 'Max Müller', 'Zürich'),
(2, 'Anna Schmidt', 'Bern'),
(3, 'Lukas Weber', 'Basel');

-- Verkäufe
INSERT INTO Verkauf VALUES
(1, 1, 1, '2024-06-10'),
(2, 2, 2, '2024-07-15'),
(3, 4, 3, '2025-01-20');


WITH AlleAutos AS (
    SELECT AutoID, Marke, Modell, Preis
    FROM Auto
)
SELECT * FROM AlleAutos;

WITH BMWVerkaeufer AS (
    SELECT ve.Name AS VerkäuferName, ve.Ort AS VerkäuferOrt
    FROM Auto a
    JOIN Verkauf v ON a.AutoID = v.AutoID
    JOIN Verkaeufer ve ON v.VerkaeuferID = ve.VerkaeuferID
    WHERE a.Marke = 'BMW' AND a.Modell = '3er'
)
SELECT * FROM BMWVerkaeufer;

DELIMITER //

CREATE PROCEDURE GetVerkaeufeVonVerkaeufer(IN name VARCHAR(100))
BEGIN
    SELECT a.Marke, a.Modell, a.Preis, v.Verkaufsdatum
    FROM Auto a
    JOIN Verkauf v ON a.AutoID = v.AutoID
    JOIN Verkaeufer ve ON v.VerkaeuferID = ve.VerkaeuferID
    WHERE ve.Name = name;
END //

DELIMITER ;

CALL GetVerkaeufeVonVerkaeufer('Max Müller')



DELIMITER //

CREATE PROCEDURE EinfacheVerkaeuferStatistik()
BEGIN
    SELECT 
        ve.Name AS Verkaeufer,
        COUNT(*) AS AnzahlVerkaeufe
    FROM Verkaeufer ve
    JOIN Verkauf v ON ve.VerkaeuferID = v.VerkaeuferID
    GROUP BY ve.Name;
END //

DELIMITER ;

CALL EinfacheVerkaeuferStatistik();
