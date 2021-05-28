# Viikkoraportti 3
Tunteja käytetty: 12

### Mitä tein?

- Jacocon konfigurointi uudestaan
    - codecov toimii nyt
- Javadocien päivitys
- Loin luolaston yhtenäisyyden tarkistavan algoritmin
    - Syvyyshaku
- Mikäli ei yhtenäinen...
    - poistetaan llian pienet luolat
    - etsitään lyhin reitti luolastoon löydetystä erillisestä luolasta, ja tarkoituksena on yhdistää luola luolastoon
        - tällä hetkellä vasta etsitään lyhin polku  
- Kirjoitin testit muulle kuin yhdistämiselle ja reitin etsimiselle
        - testit puuttuvat myös alustamisesta ja generoinnista viime viikolta
- Tutkin muita algoritmeja luolan generointiin ja päivitin määrittelydokumenttia
    - Valitsin toiseksi algortmiksi BSP-puuhun perustuvan generaattorin
### Mitä opin ja vaikeudet

Suuremmilla kartan koko -syötteillä tulee stackoverflow-virhe, koska syvyyshaku tekee liian monta rekursiivista kutsua. Mahdollisesti voisi siirtää ehtoja jo ennen kutsua. En tiedä lähdinkö kovin järjevästi luomaan luolaston yhdistämistä, en vielä ole keksinyt, miten lähden löytämästäni lähimmästä luolaston pisteestä takaisin yhdistettävään luolaan muuttaen seinät tunneliksi.

### Mitä seuraavaksi?

- Aloitan BSP-algoritmin teon
- Luolaston yhdistäminen
- StackOverflown estoa vähentämällä rekursion kutsuja
