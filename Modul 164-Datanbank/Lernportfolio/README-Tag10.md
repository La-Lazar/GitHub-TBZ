### Was ist eine CTE (Common Table Expression)?
Eine CTE ist wie eine temporäre Tabelle, die du direkt in einer SQL-Abfrage definierst. Du benutzt sie, um deine Abfrage klarer und strukturierter zu machen – besonders bei mehreren Schritten.


Aufbau einer CTE

'''
sql
Kopieren
Bearbeiten
WITH NameDerCTE AS (
    SELECT ...
    FROM ...
    WHERE ...
)
SELECT * FROM NameDerCTE;
'''

Du definierst zuerst die CTE mit WITH, gibst ihr einen Namen, schreibst die SELECT-Anweisung hinein – und kannst sie danach wie eine Tabelle benutzen.

### Warum CTE verwenden?

#### Lesbarkeit: Der SQL-Code ist einfacher zu verstehen.

#### Wiederverwendung: Du kannst dieselben Daten mehrmals verwenden.

#### Aufteilung: Du kannst komplexe Abfragen in kleinere Schritte teilen.

#### Möchtest du ein Beispiel mit mehreren CTEs hintereinander?

Stored Procedures (gespeicherte Prozeduren) sind vordefinierte SQL-Befehle, die in der Datenbank gespeichert sind und eine bestimmte Aufgabe ausführen.

### Wichtigste Merkmale:

*Wiederverwendbar: Einmal erstellt, kann man sie immer wieder verwenden.*

*Zentral gespeichert: Die Logik liegt direkt in der Datenbank, nicht im Anwendungs-Code.*

*Schneller: Läuft direkt in der Datenbank, dadurch oft bessere Leistung.*

*Sicherer: Man kann Rechte vergeben – Benutzer dürfen z. B. nur die Prozedur aufrufen, aber nicht direkt auf die Daten zugreifen.*

### Einfache Erklärung:
*Statt SQL-Code jedes Mal neu zu schreiben, speichert man ihn einmal als Prozedur – und ruft ihn nur noch bei Bedarf auf.*

Möchtest du ein Beispiel für MySQL oder SQL Server?