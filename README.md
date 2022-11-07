# smarthome-bridge

von Maximilian Burr und Cedric Becker

## Funktionalität

Wir wollen ein Werkzeug zur Verbindung verschiedener Smarthome Systeme entwickeln.
Entsprechend konfiguriert sollen Aktionen in einem System Events in einem anderen auslösen.

Unser Programm wird eine Schnittstelle zur Anbindung unterschiedlicher Smarthome Systeme besitzen.
Für die Systeme können verschiedene Adapter und Plugins entwickelt werden, um diese anzubinden.
Im Laufe dieses Projekts wollen wir ein Plugin für ein bestehendes Smarthome System und eines für das Spiel Minecraft entwickeln.
In Minecraft sollen dann bestimmte Redstone-Elemente als Ein- und Ausgänge fungieren.

Eine weitere Schnittstelle soll zur Konfiguration dienen.
An diese soll eine grafische Oberfläche angebunden werden, welche in unserem Fall ebenfalls über Minecraft umgesetzt werden soll.

Die Konfiguration (also "Verdrahtung") der Smarthome Systeme wird gespeichert.

### Optional

Optional soll nicht nur die Übertragung binärer Zustände, sondern auch die analoger Zustände möglich sein.
(In Minecraft kann Restone z.B. unterschiedliche Signalstärken haben.)

## Kundennutzen

_Haben Sie sich schon immer gedacht, Sie würden gerne ihre echten Lampen in Minecraft einschalten?
Dies ist jetzt möglich, mit Smarthome-Bridge, Ihrem Smart Home Verbindungstool.
Bauen Sie Ihr Haus in Minecraft, konfigurieren Sie es, und los gehts!_

_Alternativ können Sie so Ihr Traum-Smarthome planen und testen._

Mittels der Logik ist der Kunde außerdem in der Lage, mehrere Smarthome Systeme über die Logik in nur einem der Systeme zu steuern.

## Technologien

Programmiersprache: Java

Die Plugins für die Smarthome Systeme sind vom Anwendungskern getrennt und kommunizieren je nach System mit einem von diesem bereitgestellten Interface.

Für einen modifizierten Minecraft-Server (Spigot), der die Einbindung von Java-Plugins erlaubt, entwickeln wir ein Dummy-Plugin, das dessen API in eine REST-API übersetzt.
Das Plugin, das an unser Kern-System angebunden ist, kommuniziert über diese REST-API mit dem Dummy-Plugin.
