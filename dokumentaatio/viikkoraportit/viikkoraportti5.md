# Viikkoraportti 5
Tunteja käytetty: 8

### Mitä tein?

- BSP-puuhun perustuvan algoritmin aloitus
  - luo puun ja luolat
  - ei vielä tunneleita luolien välille
- Yritin korjata luolien yhdistämistä Cellular automata -algoritissa, mutta ongelma ei vielä ratkennut
- Javadoc uudelle koodille
- Loin testaus- ja toteutusdokumentit, mutten ehtinyt niitä vielä aloittamaan
    
### Mitä opin ja vaikeudet

Cellular automata -algoritmilla luodun luolaston ei yhtenäisten luolien yhdistämisessä on joku kunnon sekoilu. Pitäisi varmaa aloittaa puhtaalta pöydältä. 
Alkaa olla sellainen sisäisten silmukoiden meri. Ajattelin kokeilla seuraavaksi, jos etsittäisiin kaikista yhdistettävän luolan pisteistä lyhin reitti luolastoon 
ja lopuksi valitaan niistä lyhin. Lisäksi pitäisi vielä keksiä, miten lähimmän pisteen löydettyä saataisiin reitti luotua. Nyt algoritmi voi yhdistää luolan myös 
poistettavaan luolaan, koska vierailtu-taulukko säilyttää ne ja näin ei saisi tokikaan käydä eli sekin pitäisi korjata.

Uuden algoritmin kanssa alueet ovat näillä arvoilla kovin samankokoisia. Pitää varmastikin tehdä niin, ettei jokaista luolaa jaettaisi kahtia järjestyksessä vaan 
otettaisiin satunnaisia, jolloin osa jäisi isommiksi. En ehtinyt kirjottamaan testejä, testattu vain syötteillä, eli pitäisi vielä testaa kunnolla.

### Mitä seuraavaksi?

- Testit ajantasalle
- Dokumentoinnin aloitus
- BSP-algoritmin arvojen viilaus / jakaminen satunnaisesti, jotta luolastosta tulisi kiinnostavampi
- Käytävien luonti luolien väliin
- Cellular automata -algoritmin luolastojen yhdistäminen
- Oman Random -luokan luominen
  - molemmat algoritmit tarvitsevat random-luokkaa, joka arpoo tiettyjen arvojen sisältä satunnaisen numeron
