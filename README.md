|                             |                                                               |
|:----------------------------|:--------------------------------------------------------------|
| **Naziv projekta**          | Project koledar                                               |
| **Kraj in datum**           | Ljubljana, 18.10.2019                                         | 

## Cilj
Namen projekta je razvoj aplikacije, ki prikazuje datume v mescu in letu, ki ga sami določimo.

## Tehnične podrobnosti
+Aplikacija je razvita s programskim jezikom Java (openJDK 13)
+Za prikaz GUI elementov je uporabljena knjižnica JavaFx (openJFX 13)
+Za dependency management in build projekta v jar datoteko je uporabljen Maven (Maven 3.6.2)
+Za izgradnjo .exe ovojnice iz jar datoteke je uporabljen program launch4j

##Navodila
###Zagon jar datoteke iz cmd
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
