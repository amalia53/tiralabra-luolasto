# Viikkoraportti 2
Tunteja käytetty: 12

### Mitä tein?

- Checkstylen konfigurointi
- Jacocon konfigurointi
    - codecov ei toimi tällähetkellä
- Javadoc
- Luotiin tekstikäyttöliittymä, joka kysyy kokoa ja tulostaa luolaston
- Luotiin cellular automataan perustuva algoritmi, joka generoi luolia
- Suurin osa testeistä algoritmille

### Mitä opin ja vaikeudet

Gradlen kanssa tuli ongelmia uudelleennimeämisen jälkeen, joten jouduin säätämään gitin ja gradlen kanssa, jotta pääsin virheestä eroon. Codecovin kofaus onnistui ensin, mutta koodi ei päivittynyt ja nyt codecov väittää, ettei committeja ole ja badge sanoo unknown. Jacoco-raportti toimii kuitenkin. Tämä viikko meni konfliktien ja konfigurointien kanssa painiessa, itse algoritmin kirjoitus sujui melko ongelmitta.

### Mitä seuraavaksi?
- Suunnittelen minkä toisen algoritmin valitsen tehtäväksi
- Parantelen tämän hetkistä algoritmia niin, ettei synny irrallisia luolia, jotta luolasto on yhtenäinen
- -Lisää testejä (injektio) 
- Selvitän Codecovin ongelman, jotta testikattavuus saadaan näkyviin.
