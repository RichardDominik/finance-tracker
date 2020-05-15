# backend connection

Potrebne programy:

Gradle 6.3
Java 11
PgAdmin 4

Postup:

1. Stiahnut si subory s backend branchu
2. Spustit create script v pgadmin
3. Cez command line sa presunut do priecinku, kde su stiahnute subory s backendu a spustit command: gradle bootRun
4. Pre istotu skontrolovat napriklad cez postman, ze server funguje prikazmi:
    a) POST na adresu localhost:8084/sign-up a telo v tvare json suboru:
      {
	      "email" : "test@email.com",
	      "password" : "heslo123"
      }
    b) POST na adresu localhost:8084/login a poslat ten isty json subor, v headeri by sa mal nachadzat autorizacny token
5. V zdrojovych suboroch v tomto branchi treba zmenit v priecinku res/xml/network_security_config.xml ip adresu na svoju
6. V src/main/java/com/extremeprogramming/financetracker/backEndConnection/ServiceBuilder.kt treba zmenit ip na tu istu ip ako do security configu, sem treba pridat aj port 8084
7. Ak je server spusteny, login a registracia by mali fungovat :)
