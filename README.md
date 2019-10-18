|                             |                                                               |
|:----------------------------|:--------------------------------------------------------------|
| **Naziv projekta**          | Project koledar                                               |
| **Kraj in datum**           | Ljubljana, 18.10.2019                                         | 

## Cilj
Namen projekta je razvoj aplikacije, ki prikazuje datume v mescu in letu, ki ga sami določimo.

## Tehnične podrobnosti
+ Aplikacija je razvita s programskim jezikom Java (openJDK 13)
+ Za prikaz GUI elementov je uporabljena knjižnica JavaFx (openJFX 13)
+ Za dependency management in build projekta v jar datoteko je uporabljen Maven (Maven 3.6.2)
+ Za izgradnjo .exe ovojnice iz jar datoteke je uporabljen program launch4j

## Navodila
Aplikacija se nahaja v mapi `\shade` .

Izvorna koda se nahaja v `src\main\java` .

Vsebino datoteke `prazniki.txt` lahko poljubno spreminjamo,velja:
```
dd.mm.pon - praznik je ponavljajoč
dd.mm.yyyy.nepon - praznik je neponavljajoč
```
Kjer:
+ dd pomeni dan
+ mm pomeni mesec
+ yyyy pomeni leto

### Zagon aplikacije z .jar datoteko iz cmd
1. Postavimo se v mapo s datoteko koledar.jar
2. Vpišemo ukaz:
```cmd
java -jar koledar.jar "arg0"
```
(kjer je arg0 pot do datoteke s praznik)
Primer:
```cmd
java -jar koledar.jar "files\prazniki.txt"
```

### Zagon aplikacije z .bat datoteko
1. zaženemo datoteko koledar_launcher.bat (konfiguracija se izvede sama)

### Zagon aplikacije z .exe datoteko
1. zaženemo datoteko koledar.exe (JVM ni potrebna)

##Opozorilo
Aplikacija brez datoteke s prazniki na pravem mestu ne bo delovala!
