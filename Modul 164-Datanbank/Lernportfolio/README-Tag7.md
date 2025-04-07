deDatenbanken sind ein zentraler Bestandteil der Geschäftstätigkeit von Unternehmen und Websites, da sie kritische Informationen wie Kundendaten speichern. Die Sicherheit und Verfügbarkeit dieser Daten sind entscheidend, insbesondere in Bezug auf Schutz vor externen Angriffen und internen Problemen wie Hardwaredefekten oder Benutzerfehlern.

Um einem Datenverlust vorzubeugen, sollten regelmäßige Backup-Prozesse durchgeführt werden. Es existieren verschiedene Backup-Methode, wie vollständige, inkrementelle und differenziale Backups, die je nach Anforderungen des Nutzers am besten geeignet sind.

Zu den verfügbaren Tools für die Backup-Erfaltung gehören unter anderem MySQLDump, phpMyAdmin, BigDump, HeidiSQL und MariaBackups. Jedes Tool hat spezifische Eigenschaften und Begrenzungen, weshalb das passende Lösungsweg zu bestimmen ist.

Ein weiterer Schutzfaktor besteht darin, dass externe Speichermedien wie Festplatten in einem gesicherten und abgeschirmten Bereich aufbewahrt werden sollten. Darüber hinaus sollten die Daten verschlüsselt werden, um im Falle eines Diebstahls nicht nutzbar zu sein.

Um die Sicherheitsmaßnahmen zu gewährleisten, empfiehlt es sich, einen eigenständigen Backup-User mit begrenzten Berechtigungen einzurichten. Dies ist in der SQL-Anweisung unten dargestellt:

GRANT RELOAD, PROCESS, LOCK TABLES, REPLICATION CLIENT ON *.* TO 'backupuser'@'localhost' IDENTIFIED BY 'backup123';
Diese Maßnahmen garantieren eine langfristige Datensicherheit und sorgen für einen reibungslosen Ablauf der Geschäftstätigkeit.

#### Daten normalisieren

![image](https://github.com/user-attachments/assets/4a610ab2-05ae-49b1-8256-5e894cfa8a6e)


#### Aufgabe 3
-- Erstellen der Datenbank
CREATE DATABASE IF NOT EXISTS Freifaecher;
USE Freifaecher;

-- Tabelle: Schueler
CREATE TABLE Schueler (
    SchuelerNr INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    GebDatum DATE NOT NULL,
    Klasse VARCHAR(10) NOT NULL,
    Klassenlehrer VARCHAR(100) NOT NULL
);

-- Tabelle: Freifach
CREATE TABLE Freifach (
    FreifachNr INT PRIMARY KEY,
    Beschreibung VARCHAR(100) NOT NULL UNIQUE,
    Tag VARCHAR(10) NOT NULL,
    Zimmer VARCHAR(10) NOT NULL
);

-- Tabelle: Lehrer
CREATE TABLE Lehrer (
    LehrerNr INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    GebDatum DATE NOT NULL
);

-- Tabelle: Schueler_Freifach (n:m Beziehung zwischen Schueler und Freifach)
CREATE TABLE Schueler_Freifach (
    SchuelerNr INT,
    FreifachNr INT,
    PRIMARY KEY (SchuelerNr, FreifachNr),
    FOREIGN KEY (SchuelerNr) REFERENCES Schueler(SchuelerNr) ON DELETE CASCADE,
    FOREIGN KEY (FreifachNr) REFERENCES Freifach(FreifachNr) ON DELETE CASCADE
);

-- Tabelle: Lehrer_Freifach (n:m Beziehung zwischen Lehrer und Freifach)
CREATE TABLE Lehrer_Freifach (
    LehrerNr INT,
    FreifachNr INT,
    PRIMARY KEY (LehrerNr, FreifachNr),
    FOREIGN KEY (LehrerNr) REFERENCES Lehrer(LehrerNr) ON DELETE CASCADE,
    FOREIGN KEY (FreifachNr) REFERENCES Freifach(FreifachNr) ON DELETE CASCADE
);


#### Aufgabe 4 übertragen

USE Freifaecher;

SET GLOBAL local_infile = 1;

LOAD DATA LOCAL INFILE 'C:/Schueler.csv'
INTO TABLE Schueler
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Daten in die Tabelle Freifach importieren
LOAD DATA LOCAL INFILE 'C:/Freifach.csv'
INTO TABLE Freifach
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Daten in die Tabelle Lehrer importieren
LOAD DATA LOCAL INFILE 'C:/Lehrer.csv'
INTO TABLE Lehrer
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Daten in die Tabelle Schueler_Freifach importieren
LOAD DATA LOCAL INFILE 'C:/Schueler_Freifach.csv'
INTO TABLE Schueler_Freifach
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Daten in die Tabelle Lehrer_Freifach importieren
LOAD DATA LOCAL INFILE 'C:/Lehrer_Freifach.csv'
INTO TABLE Lehrer_Freifach
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

SHOW VARIABLES LIKE 'secure_file_priv';


#### Aufgabe 5 

DELETE t1 FROM Schueler t1
INNER JOIN Schueler t2 
WHERE t1.SchuelerNr > t2.SchuelerNr AND t1.Name = t2.Name;

SELECT * FROM Schueler WHERE Name IS NULL OR Name = '';

UPDATE Schueler SET Name = 'Unbekannt' WHERE Name IS NULL OR Name = '';

SELECT * FROM Schueler WHERE Klasse NOT IN (SELECT DISTINCT Klasse FROM Schueler);

UPDATE Schueler SET Klasse = '1A' WHERE Klasse = '1a'; -- Einheitliches Format

SELECT * FROM Schueler_Freifach sf 
LEFT JOIN Freifach f ON sf.FreifachNr = f.FreifachNr 
WHERE f.FreifachNr IS NULL;

DELETE FROM Schueler_Freifach WHERE FreifachNr NOT IN (SELECT FreifachNr FROM Freifach);

#### Aufgabe 6

SELECT * FROM Schueler;
SELECT * FROM Freifach;
SELECT * FROM Lehrer;
SELECT * FROM Schueler_Freifach;
SELECT * FROM Lehrer_Freifach;

SELECT COUNT(*) FROM Schueler;
SELECT COUNT(*) FROM Freifach;
SELECT COUNT(*) FROM Lehrer;
SELECT COUNT(*) FROM Schueler_Freifach;
SELECT COUNT(*) FROM Lehrer_Freifach;

SELECT * FROM Schueler LIMIT 10;
SELECT * FROM Freifach LIMIT 10;

SELECT * FROM Schueler WHERE Name IS NULL OR GebDatum IS NULL;
SELECT * FROM Freifach WHERE Beschreibung IS NULL;

