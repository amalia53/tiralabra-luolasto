
# Toteutusdokumentti

## Rakenne

Ohjelma on jaettu paketeihin: ohjelma, ui, generaattorit ja tietorakenteet. 

Ohjelma-pakkauksesta löytyy käynnistävä Main-luokka.

ui-pakkauksesta löytyy tekstipohjainen käyttöliittymä TekstiUi, joka kysyy käyttäjältä halutun luolaston kokoa ja tulostaa kahdella eri algoritmilla luodut luolastot.

##Generaattorit

Generaattori-pakkauksesta löytyy kahdella eri algoritmilla toteutetut generaattorit CAGeneraattori (Cellular automata) ja BSPGeneraattori.

## Tietorakenteet

Tietorakenteet-pakkauksesta löytyy 
- ArrayListiä muistuttava oma Lista-toteutus, jossa voidaan säilyttää Koordinaatteja
- ArrayListia muituttava oma LuolaLista-toteutus, jossa voidaan säilyttää Alue-tietorakenteita
- Kekoa muistuttava oma Keko-toteutus, joka pitää Etaisyys-tietorakenteita säilössä järjestettynä etäisyyden mukaan
- Alue-tietorakenne, jossa säilytetään BSP-generaattorin puun alkion tietoja
- Luola-tietorakenne, jossa säilytetään BSP-generaattorin luomien luolastojen luolan tietoja
- Koordinantti-tietorakenne
- Etaisyys-tietorakenne, jossa tallessa koordinantti ja sen etäisyys
- Randomia muistuttava oma Satunnainen-toteutus

## Suorituskyky

Ohjelma ajetaan ja 120 kokoiset kartat luodaan 5 sekunnissa.

## Puutteet ja parannusideat

- Aika -ja tilavaatimusten tutkiminen
- BSP:n ja Luola-tietorakenteen testaus
- BSP:n tunnelien luominen niin, ettei samoja luolia yhdistettäisi toisiinsa vaan saataisiin paremman näköinen tunneliverkosto. Tämän voisi toteuttaa simerkiki luolan luonnin yhteydessä päätetyillä ovilla
- BSP-tunnelit tulevat todennäköisemmin pystysuuntaisina, sillä tunnelin luonti lopetetaan, jos törmätään tunneliin tai luolaan, ja mutkittelevien tunnelien luonti aloitetaan pystysuuntaisista. Tässä voisi luoda ainakin satunnaisen aloituksen joko pysty- tai vaakasuunnassa.
- BSP:n tunnelien luomisen koodin refaktorointi
- Suurempien karttojen tekemien mahdollisuus: StakOverFlown välttäminen yhtenäisyyden tarkisuksessa
- Satunnainen-luokassa parempi metodi todennäköisyydelle, jolla saataisiin CA-generaattorin luolan alustus toimimaan yhtä siististi kuin Random-luokalla, jota päädyin käyttämään. Omalla luolastot näyttivät huonommalta

## Lähteet

[http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels](http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels)
[https://gamedevelopment.tutsplus.com/tutorials/generate-random-cave-levels-using-cellular-automata--gamedev-9664](https://gamedevelopment.tutsplus.com/tutorials/generate-random-cave-levels-using-cellular-automata--gamedev-9664)
[https://eskerda.com/bsp-dungeon-generation/](https://eskerda.com/bsp-dungeon-generation/)
