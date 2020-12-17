/*

------------------
MainMainActivity
------------------

------------------
AddTastActivity
------------------

------------------
UpdateTaskActivity
------------------

-----
Task
-----
-Ovo je POJO klasa unutar koje se nalaze svi nasi podaci vezani za aplikaciju.Pojo klasa se sastoji samo od atributa, metoda i konstruktora
 ova klasa je najednostavniji oblik klase te se moze pozvati objekt te klase iz bilo kojeg dijela programa zbog toga sto za ovu klasu nije
 vezan niti jedan framework.Postoje nekakva nepisana pravila vezana uz ovu klasu, a to su da polja ove klase moraju biti privatna kako njima
 ne bi mogao pristupiti korisnik iz neke druge klase i promjeniti im vrijednost, zbog toga se u ovoj metodi jos obavezno prave i getteri i
 setteri koji sluze kako bi dobili vrijednost nekog polja ili postavili vrijednost nekog polja.Getteri i setteri imaju jos i funkcionalnost
 da npr. ako dodjeljujemo preko gettera nekom atributu int vrijednost i ako ne zelimo da ta vrijednost bude nula to onda navedemo u tijelu
 metode, ovo inace ne bi mogli kada bi pristupali cisim atributima.Ova klasa bi također trebala imati i default i custom konstruktor koji
 nam sluze za instanciranja objekata.Svaka POJO klasa unutar baze podataka nam predstavlja jednu tablicu, ali moramo staviti prije deklaracije
 klase anotaciju @Entity.Postoji jos mnogo funkcionalnosti koje se mogu upogoniti s pomocu ove klase, ali necu ici u velike detalje.

-------------
TasksAdapter
-------------

-------------
AppDatabase
-------------

---------------
DatabaseClient
---------------

---------
TaskDao
---------

 */