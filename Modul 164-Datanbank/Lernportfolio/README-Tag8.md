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

ERD sollte so aussehen:

stichtag int,
sort int,
cd varchar(5),
quarlang varchar(255),
tarifsort int,
tarifcd varchar(5),
tariflang varchar(255),
SteuerEinkommen_p50 decimal(5,2),
SteuerEinkommen_p25 decimal(5,2),
SteuerEinkommen_p75 decimal(5,2)

### Daten Bulkimport

LOAD DATA LOCAL INFILE 'C:/steuerdaten.csv'
INTO TABLE steuern
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(stichtag, sort, cd, quarlang, tarifsort, tarifcd, tariflang, SteuerEinkommen_p50, SteuerEinkommen_p25, SteuerEinkommen_p75);

### Daten erstellen

-- Datenbank erstellen und verwenden

CREATE DATABASE steuern;
USE steuern;
 
create table steuern (
 
stichtag int,
 
sort int,
 
cd varchar(5),
 
quarlang varchar(255),
 
tarifsort int,
 
tarifcd varchar(5),
 
tariflang varchar(255),
 
SteuerEinkommen_p50 decimal(5,2),
 
SteuerEinkommen_p25 decimal(5,2),
 
SteuerEinkommen_p75 decimal(5,2)
);

### Aufgabe 4

### Analyse der Felder _p25, _p50, _p75
 
In meiner Arbeit mit den importierten Excel-Daten und dem zugehörigen SQL-Skript habe ich festgestellt, dass die Felder **_p25**, **_p50** und **_p75** wichtige statistische Kennzahlen darstellen:
 
- **_p25**: Dieses Feld repräsentiert das 25. Perzentil (auch unteres Quartil genannt). Es zeigt den Wert an, unter dem 25 % der Daten liegen. Ich verwende diesen Wert, um den unteren Bereich der Datenverteilung zu analysieren.
 
- **_p50**: Dieses Feld entspricht dem 50. Perzentil, also dem Median. Das bedeutet, dass 50 % der Daten unterhalb dieses Wertes liegen. Der Median gibt mir einen guten Überblick über die zentrale Tendenz der Verteilung.
 
- **_p75**: Dieses Feld stellt das 75. Perzentil (oberes Quartil) dar. Es zeigt den Wert, unter dem 75 % der Daten zu finden sind, und hilft mir dabei, den oberen Bereich der Verteilung zu verstehen.

 ### Aufgabe 5

 ```
SELECT quarlang AS Quartier, tariflang AS Tarif, SteuerEinkommen_p75
FROM steuern
WHERE SteuerEinkommen_p75 IS NOT NULL
ORDER BY SteuerEinkommen_p75 DESC
LIMIT 1;
```
 
**b.    Welches Quartier hat das niedrigste Steuereinkommen für _p50?**
 
```
SELECT quarlang AS Quartier, tariflang AS Tarif, SteuerEinkommen_p50
FROM steuern
WHERE SteuerEinkommen_p50 IS NOT NULL
ORDER BY SteuerEinkommen_p50 ASC
LIMIT 1;
```
 
**c.    Welches Quartier hat das höchste Steuereinkommen für _p50?**
 
```
SELECT quarlang AS Quartier, tariflang AS Tarif, SteuerEinkommen_p50
FROM steuern
WHERE SteuerEinkommen_p50 IS NOT NULL
ORDER BY SteuerEinkommen_p50 DESC
LIMIT 1;
```
 


 ### Aufgabe 6

 ![image](https://github.com/user-attachments/assets/65e027e8-f516-41f4-823c-7d8ac38d7537)


