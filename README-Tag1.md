# Aufgabe 1

## 1. Stufen der Wissenstreppe und Beispiel mit einem Wechselkurs
Die Wissenstreppe nach Klaus North beschreibt den Weg von Daten zu Wissen und Kompetenz:

1. **Zeichen** – Einzelne Zeichen oder Symbole ohne Kontext  
   *Beispiel: „1.12“*
2. **Daten** – Zeichen in einem bestimmten Kontext  
   *Beispiel: „1.12 USD/EUR“ (eine Zahl mit einer Einheit)*
3. **Information** – Daten mit Bedeutung durch Strukturierung  
   *Beispiel: „Der aktuelle Wechselkurs beträgt 1.12 USD für 1 EUR“*
4. **Wissen** – Informationen, die interpretiert und verknüpft werden  
   *Beispiel: „Der Kurs ist im Vergleich zur Vorwoche um 2% gestiegen“*
5. **Verständnis** – Wissen mit einer tiefgehenden Bedeutung  
   *Beispiel: „Ein steigender USD/EUR-Kurs bedeutet, dass der Euro schwächer wird“*
6. **Handlungsfähigkeit** – Fähigkeit, Wissen anzuwenden  
   *Beispiel: „Ich kaufe jetzt USD, weil ich auf einen weiteren Kursanstieg spekuliere“*
7. **Kompetenz** – Erfahrene Anwendung und Problemlösung  
   *Beispiel: „Ich nutze Marktanalysen, um Wechselkursbewegungen profitabel vorherzusagen“*

---

## 2. Abbildung von Netzwerk-Beziehungen im logischen Modell
Im logischen Datenmodell werden Netzwerk-Beziehungen oft als **Relationen** in einer relationalen Datenbank modelliert:

- **1:1-Beziehung** → Primärschlüssel einer Tabelle als Fremdschlüssel in einer anderen Tabelle.  
- **1:N-Beziehung** → Fremdschlüssel auf der „N“-Seite der Beziehung.  
- **M:N-Beziehung** → Eine zusätzliche **Zwischentabelle** mit Fremdschlüsseln beider Tabellen wird benötigt.

Im gezeigten Bild sieht man eine **1:N-Beziehung** zwischen `Person` und `Kleidungsstück`, dargestellt durch eine Verbindungslinie mit „1“ auf der einen Seite und „∞“ (unendlich) auf der anderen.4

---

## 3. Anomalien in einer Datenbasis und Arten
Anomalien treten auf, wenn eine Datenbank schlecht normalisiert ist. Es gibt drei Hauptarten:

1. **Einfügeanomalie** – Man kann keine Daten hinzufügen, weil andere Daten fehlen.  
   *Beispiel: Ein neuer Mitarbeiter kann nicht hinzugefügt werden, wenn er noch keiner Abteilung zugewiesen wurde.*
   
2. **Löschanomalie** – Das Löschen eines Datensatzes entfernt ungewollt weitere wichtige Informationen.  
   *Beispiel: Löscht man den letzten Mitarbeiter einer Abteilung, geht die Abteilungsinformation verloren.*
   
3. **Änderungsanomalie** – Eine Änderung muss an mehreren Stellen durchgeführt werden, was zu Inkonsistenzen führt.  
   *Beispiel: Die Abteilungsbezeichnung muss in mehreren Datensätzen geändert werden, was fehleranfällig ist.*

---

## 4. Gibt es redundante Daten?
Ja, redundante Daten existieren, wenn dieselben Informationen mehrfach in einer Datenbank gespeichert werden. Dies kann problematisch sein, weil:

- **Speicherplatz verschwendet wird.**  
- **Dateninkonsistenzen entstehen können, wenn Änderungen nicht überall vorgenommen werden.**  
- **Leistungsprobleme bei großen Datenmengen auftreten können.**  

Durch die **Normalisierung** (z. B. 3. Normalform) kann Redundanz reduziert werden.

---

## 5. Datenstrukturierung bei der Erhebung und Ablage von Daten
Bei der Strukturierung von Daten gibt es zwei Hauptaspekte:

1. **Inhaltliche Strukturierung** – Wie die Daten untereinander in Beziehung stehen (z. B. Tabellen mit Primär- und Fremdschlüsseln).
2. **Formatierung der Daten** – Wie die Daten gespeichert und dargestellt werden (z. B. Datentypen wie INT, VARCHAR).

### Kategorien der Strukturierung:
- **Unstrukturierte Daten** – Rohdaten ohne feste Form (z. B. Texte, Bilder, Videos).  
- **Semistrukturierte Daten** – Teilweise geordnete Daten (z. B. JSON, XML).  
- **Strukturierte Daten** – Daten mit klar definierten Attributen und Beziehungen (z. B. relationale Datenbanken).

### Anforderungen für Datenbanken:
- Daten müssen **in Tabellen organisiert** sein.  
- Es müssen **Primär- und Fremdschlüssel** zur Vermeidung von Redundanzen genutzt werden.  
- Die **Normalisierung** sorgt für Konsistenz und Integrität.

---

## 6. Beschreibung des Bildes mit den richtigen Fachbegriffen
Das gezeigte Bild stellt eine **relationale Tabelle** dar. Die Begriffe lauten:

1. **Tabellenname** („Mitarbeiter“) – Bezeichnet die Tabelle, die die Datensätze enthält.
2. **Primärschlüssel (MitarbeiterId)** – Eindeutige Identifikation für jede Zeile.
3. **Spaltennamen (Attribute)** – Definieren die Art der gespeicherten Daten (z. B. Vorname, Nachname, Alter).
4. **Datenwerte (Tupel)** – Konkrete Einträge in der Tabelle.
5. **Fremdschlüssel (Abteilungskürzel)** – Verweis auf eine andere Tabelle, um Beziehungen herzustellen.

Diese Struktur entspricht einer **relationalen Datenbank** mit normalisierten Tabellen.

# Aufgabe 2

## 1. Arten von Datenmodellen
- **Konzeptionelles Datenmodell**:  
  - Implementierungsunabhängig  
  - Reine Fachlichkeit  
  - Direkt aus Anforderungen entwickelt  

- **Logisches Datenmodell**:  
  - Abbildung des konzeptionellen Modells auf ein Datenbanksystem  
  - Relationales Modell, etc.  

- **Physisches Datenmodell**:  
  - Erweiterung des logischen Modells um technische Aspekte  
  - Indizes, Partitionierung, etc.  

## 2. Modellierungsmethoden und Anwendungsbereiche
- **3. Normalform (3NF)**:  
  - Hauptsächlich in operativen Systemen (z. B. ERP)  
  - Core DWH / Enterprise DWH  
  - Anwendungsfreie Speicherung der Daten  

- **Star Schema**:  
  - Optimiert für Auswertungssysteme  
  - Nutzung im Reporting Layer (Data Mart) eines Data Warehouses  

- **Data Vault Modellierung**:  
  - Moderne, erweiterbare Modellierung  
  - Fokus auf Automatisierung der Beladeprozesse  
  - Hauptsächlich im Core DWH / Integration Layer  



