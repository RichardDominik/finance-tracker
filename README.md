# Backend connection

Potrebne programy:

Gradle 6.3 <br/>
Java 11 <br/> 
PgAdmin 4 <br/>

# Postup:

1. Stiahnut si subory z backend branchu
2. V priecinku src/main/resouces/application.properties dat meno svojej postgre databazy a heslo
3. Spustit create script v pgadmin
4. Cez command line sa presunut do priecinku, kde su stiahnute subory s backendu a spustit command: gradle bootRun
5. Pre istotu skontrolovat napriklad cez postman, ze server funguje prikazmi: <br/>
    a) POST na adresu localhost:8084/sign-up a telo v tvare json suboru:<br/>
      {<br/>
	      "email" : "test@email.com",<br/>
	      "password" : "heslo123"<br/>
      }<br/>
    b) POST na adresu localhost:8084/login a poslat ten isty json subor, v headeri by sa mal nachadzat autorizacny token<br/>
6. V zdrojovych suboroch v tomto branchi treba zmenit v priecinku res/xml/network_security_config.xml ip adresu na svoju
7. V src/main/java/com/extremeprogramming/financetracker/backEndConnection/ServiceBuilder.kt treba zmenit ip na tu istu ip ako do security configu, sem treba pridat aj port 8084
8. Ak je server spusteny, login a registracia by mali fungovat :)


# Endpointy:

/sign-up = endpoint na registraciu, tvar POST a telo json v tvare <br/>
{<br/>
	      "email" : "test@email.com",<br/>
	      "password" : "heslo123"<br/>
      }<br/>
      
/login = endpoint na prihlasenie, tvar POST telo rovnake a ako odozva pride v headeri autorizacny token, ktory ma zivotnost 10 dni<br/>
/record/create = endpoint na vytvorenie zaznamu pre kategoriu, tvar POST telo json v tvare <br/>
{<br/>
	"categoryid:...,<br/>
	"record":{<br/>
	"type":{"credit","debit"},<br/>
	"description":...,<br/>
	"amount":...[cislo]<br/>
	}<br/>
}<br/>
	
/records = endpoint na vylistovanie vsetkych recordov pre dacu kategoriu, tvar GET s parametrom "categoryid"<br/>
/record/{id} = endpoint na vymazanie recordu podla idcka, poslat ako DELETE
