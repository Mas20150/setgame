# 🃏 SET Game – Java Implementation

Willkommen zu unserer digitalen Version des beliebten Kartenspiels **SET®**, das die visuelle Wahrnehmung und Reaktionsfähigkeit herausfordert. Dieses Projekt wurde in Java entwickelt und basiert auf den offiziellen Spielregeln von SET®.

## 🎯 Spielziel

Das Ziel des Spiels ist es, aus 12 offen ausgelegten Karten ein **SET** aus drei Karten zu identifizieren. Jede Karte besitzt vier Merkmale:

- **Farbe** (z. B. Rot, Grün, Lila)
- **Form** (z. B. Oval, Welle, Raute)
- **Anzahl** (1, 2 oder 3 Symbole)
- **Schraffierung** (Voll, gestreift, leer)

Ein gültiges SET besteht aus drei Karten, bei denen **jedes Merkmal** entweder bei allen Karten **gleich** oder **unterschiedlich** ist.

## 🧠 Beispiel für ein SET

Ein gültiges SET könnte sein:

- Drei Karten mit **gleicher Farbe**, **gleicher Form**, **gleicher Anzahl** und **gleicher Schraffierung**  
  _oder_
- Drei Karten mit **unterschiedlicher Farbe**, **unterschiedlicher Form**, **unterschiedlicher Anzahl** und **unterschiedlicher Schraffierung**

## 📦 Projektstruktur

Das Projekt ist modular aufgebaut:

- `enums/` – Definition der Karteneigenschaften (Farbe, Form, etc.)
- `repository/` – Verwaltung von Spiel- und Spielerzuständen
- `windows/` – GUI-Komponenten für Spielstart, Einstellungen und Spielverlauf
- `database/` – Hibernate-Anbindung zur Persistenz
- `SetEnt.java` – zentrale Logik zur SET-Erkennung
- `module-info.java` – Moduldefinition für Java 9+

## 🖥️ Spielablauf

1. Der Dealer legt 12 Karten offen aus.
2. Spieler rufen „SET“, wenn sie glauben, ein gültiges SET gefunden zu haben.
3. Das SET wird überprüft:
   - **Richtig** → Spieler erhält einen Punkt, Karten werden ersetzt.
   - **Falsch** → Spieler verliert einen Punkt, Karten bleiben liegen.
4. Gibt es kein SET → 3 weitere Karten werden ausgelegt.
5. Das Spiel endet, wenn der Kartenstapel leer ist.

## 🧑‍💻 Entwicklerhinweise

- Die GUI basiert auf Swing.
- Die Spielregeln sind vollständig implementiert.
- Datenbankverbindung über Hibernate (`HibernateConnector.java`)
- Erweiterbar für Multiplayer, Highscore-Tracking und Online-Modus.
- Das Programm nutzt Hibernate, die Zugangsdaten stehen in der conf Datei.
- Die Datenbank ist über das Hochschulnetz erreichbar. Ein SQL Script der Datenbank liegt ebenfalls bei.

