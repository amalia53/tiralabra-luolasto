# Testausdokumentti

Molemmat generaattorit luovat määritelmän mukaisia karttoja. Molemmat algoritmit luovat yhtenäisiä luolastoja, joka tarkistetaan ohjelmaa ajaessa. Molemmissa kartoissa voidaan kulkea ylös, alas, vasemmalle ja oikealle, joka myös tarkistetaan ohjelmaa ajaessa. 

Ohjelmalla voidaan luoda 20 - 120 kokoisia karttoja. Mikäli ohjelmalle antaa syötteen näiden ulkopuolelta, pyytää ohjelma uutta syötettä. BSP-generaattorilla voitaisiin luoda ainakin 200 kokoisia, mutta CA-generaattorin yhtenäisyyden tarkistaminen aiheuttaa DtackOverFlown. 

## CA-generaattori

Aluksi luodaan luolasto, joka ei välttämättä ole yhtenäinen. Luolastosta etsitään luolasto, joka käydään läpi ja etsitään kaikki luolaston osat, johon päästään kulkemalla ylös, alas, vasemmalle tai oikealle. Mikäli luolaston kaikkia osia ei löydetty tällä haulla, tarkistetaan löydetyn luolaston koko. Mikäli löydetty luolasto on pieni, se poistetaan. Mikäli se on suuri, tehdään haku uudelleen. Jos löydämme kaksi suurempaa luolastoa, yhdistämme ne tunnelilla. Kun kaikki luolaston osat on löydetty, ja pienet luolat poistettu ja suuremmat yhdistetty, on luolasto varmasti yhtenäinen, kun voidaan kulkea ylös, alas, vasemmalle tai oikealle.

Generaattorin metodit on testattu kattavasti JUnit-testeillä.

## BSP-generaattori

Aluksi luodaan luolat, jotka sitten yhdistetään satunnaisesti tunneleilla. Sitten ohjelmassa tarkistetaan, onko luolasto yhtenäinen tekemällä haku, jossa etsitään kaikki luolaston osat, johon päästään kulkemalla ylös, alas, vasemmalle tai oikealle. Mikäli löytyy osia, joita ei löydetty haulla, yhdistetään ne tunnelilla satunnaiseen luolaston toiseen osaan. Haku ja tunnelin lisäys tehdään kunnes luolasto on varmasti yhtenäinen. 
