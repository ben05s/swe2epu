
EPU Backoffice
 
Entwickelt von Patrick Schwab & Benjamin Steindl


- Benutzerhandbuch:
Das Programm EPU Backoffice ist eine Applikation die einem Betrieb die Managementaufgaben erleichtern soll.
Mit dieser Applikation k�nnen Kunden, Kontakte, Projekte, Rechnungen, und vieles mehr, verwaltet werden. 
Gleichzeitig ist das Programm in der Lage Statistische Daten zu sammeln, wie zum Beispiel: Arbeitststunden, 
Prognosen �ber die Einnahmen der kommenden Jahre, usw.

Das Programm wurde in der Programmiersprache JAVA geschrieben und verf�gt �ber eine Grafische Benutzer Oberfl�che(GUI) 
mit der der Benutzer Interagieren kann und einer Datenbank wo alle relevanten Daten gespeichert werden. 
Als Datenbank wurde die OpenSource HyperSQL DataBase(HSQLDB) verwendet. Ein kompatibles Datenbankschema kann �ber die
von uns bereitgestellten SQL-Scripts im Verzeichnis db_scripts/ einfach �bernommen werden und sogar mit Testdaten bef�llt
werden.
 
Die Applikation verf�gt sowohl �ber einen DatenbankAccess als auch �ber eine MockDatenbank, welche die eigentliche 
Datenbank simuliert um die Schnittstellen zwischen der Applikation und der Datenbank besser Testen zu k�nnen. 

In einer Konfigurationsdatei kann man Spezifizieren mit welcher Datenbank gearbeitet werden soll und welches Logging 
Framework verwendet wird. Wird das Feld f�r den Datenbankpfad leer gelassen wird automatisch mit dem Mock Objekten gearbeitet.

Um alle gew�nschten Entit�ten in der GUI abzubilden wurde jeder Entit�t(Kunde, Rechnungen,...) ein eigener Tab zugeteilt. 
Die Objekte die Dynamisch aus der Datenbank geladen werden k�nnen jederzeit mit deinem Rechtsklick auf das gew�nschte Objekt 
ver�ndert oder gel�scht werden.  Es ist auch m�glich neue Objekte zu erstellen und diese in die Datenbank zu speichern.

Bei Starten der Applikation muss unbedingt das Konfigurations-File(beispielsweise: config/app.properties) als Parameter angegeben werden 
damit das Logging und der Dynamische DataAccessLayer korrekt funktionieren.

- L�sungsbeschreibung:
Klassische n-tier Architektur, mit DAL, BL und PL. Im Presentation Layer haben wir uns f�r die Model-View-Controller Architektur entschieden.
Der DAL erzeugt dynamisch mit Hilfe von Reflection CRUD - SQL Statements.


- Worauf sind wir stolz:
Besonders gut gelungen ist das einfache, leicht verst�ndliche und ebenso simpel bedienbare GUI.  
Ebenso das Klassendesign und die saubere Trennung der Layer (DataAccess, Business, Presentation) 
ist und gut gelungen und hat das arbeiten sinnvoll unterst�tzt.


- Was wir das n�chste mal anders machen w�rden:
Bei dem n�chsten Software Projekt wollen wir auf jeden Fall mehr Zeit in die Planungsphase investieren da es 
leichter ist das Konzept umzusetzten wenn man sich intensiv mit der Spezifikation auseinander gesetzt hat 
(Spezifikation war leider sehr ungenau, daher viele unklarheiten und aufw�ndige �nderungen im Source Code).

- Reports:
Siehe folder reports/. Anmerkung: Das einzige auf Eclipse Indigo verf�gbare Code Coverage Tool 'Eclemma' hatte Probleme in der Zusammenarbeit mit JUnit bei 10 von 50 Tests. Diese brachen aufgrund von internen Exceptions vorzeitig ab (und z�hlten in Folge nicht zu Code Coverage).