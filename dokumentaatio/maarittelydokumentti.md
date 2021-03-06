# Määrittelydokumentti

Dokumentaation kieli: Suomi

Opinto-ohjelma: Tietojenkäsittelytieteen kandidaatti TKT

Luolageneraattorin on tarkoitus pystyä luomaan kaksiulotteisia luolia ja käytäviä annetun kokoiseen ruudukkoon. Luolat yhdistyvät toisiinsa käytävillä ja mistä vain luolasta pääsee jotain reittiä pitkin toiseen luolaan, eli luolasto on yhtenäinen. Liikkua voi neljään suuntaan, eli ylös, alas, vasemmalle tai oikealle, mikäli vastassa ei ole seinää. 

### Tietorakenteet

Cellular Automata -algoritmissa luolastokartta tallennetaan matriisiin. 

Toisessa algoritmissa luon luolista BSP-puun.

### Algoritmit

Käytetään Cellular Automata -algoritmia toisen generaattorin luontiin. Valitsin tämän algoritmin, sillä se vaikuttaisi luovan kiinnostavan oloisia, luonnollisen näköisiä luolastoja. Luolaston yhtenäisyyden tarkistamiseksi, ajattelin käyttää syvyyshakua. Isot erilliset luolastot yhdistetään etsimällä lyhin polku Bellmanin ja Fordin algoritmia mukaillen. 

Toiseen generaattoriin käytän luola ja käytävä -tyylisempää luolastoa luovaa BSP-puuhun perustuvaa algoritmia. 

### Syötteet

Ohjelma saa syötteenään luolaston koon eli alueen, jolle luolia ja käytäviä voidaan generoida. Alue on n * n kokoinen ja syötteenä pyydetään n:n arvo.

### Lähteitä

[http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels](http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels)
[https://gamedevelopment.tutsplus.com/tutorials/generate-random-cave-levels-using-cellular-automata--gamedev-9664](https://gamedevelopment.tutsplus.com/tutorials/generate-random-cave-levels-using-cellular-automata--gamedev-9664)
[https://eskerda.com/bsp-dungeon-generation/](https://eskerda.com/bsp-dungeon-generation/)
