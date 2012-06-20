
EPU Backoffice
 
Entwickelt von Patrick Schwab & Benjamin Steindl


- Benutzerhandbuch:
Das Programm EPU Backoffice ist eine Applikation die einem Betrieb die Managementaufgaben erleichtern soll.
Mit dieser Applikation können Kunden, Kontakte, Projekte, Rechnungen, und vieles mehr, verwaltet werden. 
Gleichzeitig ist das Programm in der Lage Statistische Daten zu sammeln, wie zum Beispiel: Arbeitststunden, 
Prognosen über die Einnahmen der kommenden Jahre, usw.

Das Programm wurde in der Programmiersprache JAVA geschrieben und verfügt über eine Grafische Benutzer Oberfläche(GUI) 
mit der der Benutzer Interagieren kann und einer Datenbank wo alle relevanten Daten gespeichert werden. 
Als Datenbank wurde die OpenSource HyperSQL DataBase(HSQLDB) verwendet. Ein kompatibles Datenbankschema kann über die
von uns bereitgestellten SQL-Scripts im Verzeichnis db_scripts/ einfach übernommen werden und sogar mit Testdaten befüllt
werden.
 
Die Applikation verfügt sowohl über einen DatenbankAccess als auch über eine MockDatenbank, welche die eigentliche 
Datenbank simuliert um die Schnittstellen zwischen der Applikation und der Datenbank besser Testen zu können. 

In einer Konfigurationsdatei kann man Spezifizieren mit welcher Datenbank gearbeitet werden soll und welches Logging 
Framework verwendet wird. Wird das Feld für den Datenbankpfad leer gelassen wird automatisch mit dem Mock Objekten gearbeitet.

Um alle gewünschten Entitäten in der GUI abzubilden wurde jeder Entität(Kunde, Rechnungen,...) ein eigener Tab zugeteilt. 
Die Objekte die Dynamisch aus der Datenbank geladen werden können jederzeit mit deinem Rechtsklick auf das gewünschte Objekt 
verändert oder gelöscht werden.  Es ist auch möglich neue Objekte zu erstellen und diese in die Datenbank zu speichern.

Bei Starten der Applikation muss unbedingt das Konfigurations-File(beispielsweise: config/app.properties) als Parameter angegeben werden 
damit das Logging und der Dynamische DataAccessLayer korrekt funktionieren.

- Lösungsbeschreibung:
Klassische n-tier Architektur, mit DAL, BL und PL. Im Presentation Layer haben wir uns für die Model-View-Controller Architektur entschieden.
Der DAL erzeugt dynamisch mit Hilfe von Reflection CRUD - SQL Statements.


- Worauf sind wir stolz:
Besonders gut gelungen ist das einfache, leicht verständliche und ebenso simpel bedienbare GUI.  
Ebenso das Klassendesign und die saubere Trennung der Layer (DataAccess, Business, Presentation) 
ist und gut gelungen und hat das arbeiten sinnvoll unterstützt.


- Was wir das nächste mal anders machen würden:
Bei dem nächsten Software Projekt wollen wir auf jeden Fall mehr Zeit in die Planungsphase investieren da es 
leichter ist das Konzept umzusetzten wenn man sich intensiv mit der Spezifikation auseinander gesetzt hat 
(Spezifikation war leider sehr ungenau, daher viele unklarheiten und aufwändige änderungen im Source Code).

- Reports:
Siehe folder reports/. Anmerkung: Das einzige auf Eclipse Indigo verfügbare Code Coverage Tool 'Eclemma' hatte Probleme in der Zusammenarbeit mit JUnit bei 10 von 50 Tests. Diese brachen aufgrund von internen Exceptions vorzeitig ab (und zählten in Folge nicht zu Code Coverage).