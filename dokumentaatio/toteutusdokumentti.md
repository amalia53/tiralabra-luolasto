
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

## Puutteet ja parannusideat

- BSP:n testaus
- BSP:n tunnelien luominen niin, ettei samoja luolia yhdistettäisi toisiinsa vaan saataisiin paremman näköinen tunneliverkosto
- BSP:n tunnelien luomisen koodin refaktorointi
- Satunnainen-luookassa parempi metodi todennäköisyydelle, jolla saataisiin CA-generaattorin luolan alustus toimimaan yhtä siististi kuin Random-luokalla, jota päädyin käyttämään. Omalla luolastot näyttivät huonommalta
