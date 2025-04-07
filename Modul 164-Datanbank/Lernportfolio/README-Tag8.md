### Aufgabe 7

LOAD DATA LOCAL INFILE 'C:/Schueler.csv'
INTO TABLE Quartier
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

### Aufgabe 8

SELECT COUNT(sf.SchuelerNr) AS Teilnehmerzahl
FROM Lehrer l
JOIN Lehrer_Freifach lf ON l.LehrerNr = lf.LehrerNr
JOIN Schueler_Freifach sf ON lf.FreifachNr = sf.FreifachNr
WHERE l.Name = 'Inge Sommer';

SELECT s.Klasse, COUNT(DISTINCT sf.SchuelerNr) AS Anzahl_SchuelerInnen
FROM Schueler s
JOIN Schueler_Freifach sf ON s.SchuelerNr = sf.SchuelerNr
GROUP BY s.Klasse
ORDER BY s.Klasse;

SELECT s.SchuelerNr, s.Name, f.Beschreibung
FROM Schueler s
JOIN Schueler_Freifach sf ON s.SchuelerNr = sf.SchuelerNr
JOIN Freifach f ON sf.FreifachNr = f.FreifachNr
WHERE f.Beschreibung IN ('Chor', 'Elektronik');

### Aufgabe 9

SELECT COUNT(sf.SchuelerNr) AS Teilnehmerzahl
INTO OUTFILE 'C:/M164/teilnehmer_inge_sommer.csv'
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
FROM Lehrer l
JOIN Lehrer_Freifach lf ON l.LehrerNr = lf.LehrerNr
JOIN Schueler_Freifach sf ON lf.FreifachNr = sf.FreifachNr
WHERE l.Name = 'Inge Sommer';

SELECT s.Klasse, COUNT(DISTINCT sf.SchuelerNr) AS Anzahl_SchuelerInnen
INTO OUTFILE 'C:/M164/klassen_statistik.csv'
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
FROM Schueler s
JOIN Schueler_Freifach sf ON s.SchuelerNr = sf.SchuelerNr
GROUP BY s.Klasse
ORDER BY s.Klasse;

SELECT s.SchuelerNr, s.Name, f.Beschreibung
INTO OUTFILE 'C:/M164/chor_oder_elektronik.csv'
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
FROM Schueler s
JOIN Schueler_Freifach sf ON s.SchuelerNr = sf.SchuelerNr
JOIN Freifach f ON sf.FreifachNr = f.FreifachNr
WHERE f.Beschreibung IN ('Chor', 'Elektronik');

### Aufgabe 10

![image](https://github.com/user-attachments/assets/e4967589-b975-4ade-ac89-036b4f6edcfe)


### ERD Steurdaten

![image](https://github.com/user-attachments/assets/3bb1b16c-dced-44f3-81bb-6479d971b52e)


### Daten Bulkimport

LOAD DATA LOCAL INFILE 'C:/normalized_quartier.csv'
INTO TABLE Quartier
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
 
LOAD DATA LOCAL INFILE 'C:/normalized_steuertarif.csv'
INTO TABLE Steuertarif
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
 
LOAD DATA LOCAL INFILE 'C:/normalized_jahresdaten.csv'
INTO TABLE Jahresdaten
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

### Daten erstellen

-- Datenbank erstellen und verwenden

CREATE DATABASE IF NOT EXISTS Steuerdaten;

USE Steuerdaten;
 
-- Tabelle Quartier

CREATE TABLE Quartier (

    QuartierID INT PRIMARY KEY,

    Name VARCHAR(100) NOT NULL,

    Region VARCHAR(100)  -- optional, falls weitere regionale Angaben erwünscht

);
 
-- Tabelle Steuertarif

CREATE TABLE Steuertarif (

    TarifID INT PRIMARY KEY,

    Beschreibung VARCHAR(100) NOT NULL,

    Grundtarif DECIMAL(10,2),

    Spitzensteuersatz DECIMAL(5,2)

);
 
-- Tabelle Jahresdaten

-- Hier nehmen wir an, dass es pro Jahr und Quartier genau einen Datensatz gibt.

CREATE TABLE Jahresdaten (

    Jahr INT NOT NULL,

    QuartierID INT NOT NULL,

    TarifID INT NOT NULL,

    Einkommen DECIMAL(15,2),

    Steuerbetrag DECIMAL(15,2),

    PRIMARY KEY (Jahr, QuartierID),

    FOREIGN KEY (QuartierID) REFERENCES Quartier(QuartierID),

    FOREIGN KEY (TarifID) REFERENCES Steuertarif(TarifID)

);

 
### Aufgabe 4

### Analyse der Felder _p25, _p50, _p75
 
In meiner Arbeit mit den importierten Excel-Daten und dem zugehörigen SQL-Skript habe ich festgestellt, dass die Felder **_p25**, **_p50** und **_p75** wichtige statistische Kennzahlen darstellen:
 
- **_p25**: Dieses Feld repräsentiert das 25. Perzentil (auch unteres Quartil genannt). Es zeigt den Wert an, unter dem 25 % der Daten liegen. Ich verwende diesen Wert, um den unteren Bereich der Datenverteilung zu analysieren.
 
- **_p50**: Dieses Feld entspricht dem 50. Perzentil, also dem Median. Das bedeutet, dass 50 % der Daten unterhalb dieses Wertes liegen. Der Median gibt mir einen guten Überblick über die zentrale Tendenz der Verteilung.
 
- **_p75**: Dieses Feld stellt das 75. Perzentil (oberes Quartil) dar. Es zeigt den Wert, unter dem 75 % der Daten zu finden sind, und hilft mir dabei, den oberen Bereich der Verteilung zu verstehen.

 ### Aufgabe 5

 ### a) Quartier mit maximalem Steuereinkommen für _p75
 
Ich verbinde die Tabelle mit den Jahresdaten, in der unter anderem das Feld **_p75** abgelegt ist, mit der Tabelle **Quartier**. Anschließend sortiere ich nach dem Feld **_p75** absteigend und wähle das oberste Ergebnis aus:
 
```
SELECT q.Name AS Quartier, j._p75 AS Steuereinkommen_p75
FROM Jahresdaten j
JOIN Quartier q ON j.QuartierID = q.QuartierID
ORDER BY j._p75 DESC
LIMIT 1;
```
 
---
 
### b) Quartier mit dem niedrigsten Steuereinkommen für _p50
 
Hier sortiere ich die Jahresdaten anhand des Feldes **_p50** in aufsteigender Reihenfolge, sodass das Quartier mit dem geringsten Steuereinkommen oben erscheint:
 
```
SELECT q.Name AS Quartier, j._p50 AS Steuereinkommen_p50
FROM Jahresdaten j
JOIN Quartier q ON j.QuartierID = q.QuartierID
ORDER BY j._p50 ASC
LIMIT 1;
```
 
---
 
### c) Quartier mit dem höchsten Steuereinkommen für _p50
 
Um das Quartier mit dem höchsten Wert im Feld **_p50** zu ermitteln, sortiere ich in absteigender Reihenfolge:
 
```
SELECT q.Name AS Quartier, j._p50 AS Steuereinkommen_p50
FROM Jahresdaten j
JOIN Quartier q ON j.QuartierID = q.QuartierID
ORDER BY j._p50 DESC
LIMIT 1;
```


 ### Aufgabe 6

 ![image](https://github.com/user-attachments/assets/65e027e8-f516-41f4-823c-7d8ac38d7537)


