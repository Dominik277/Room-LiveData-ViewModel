/*

------------------
MainMainActivity
------------------

------------------
AddTastActivity
------------------
-Osim Main aktivnosti koja se prikazuje pri pokretanju aplikacije kao prvi prozor mi također imamo jos dvije aktivnosti, a to su
 AddTastActivity i UpdateTaskActivity.Ova klasa nam predstavlja logiku za aktivnost AddTastActivity.U XML-u smo definirali tocno
 kako ce izgledati ekran kada se otvori ova aktivnost, a ona ce izgledati tako da ce gore pisati naslov "Add a Task" a ispod tog
 naslova ce biti tri EditText-a u koje korisnik upisuje zadatk,opis zadatka i do kad bi trebao ispuniti taj zadatak, te jos na
 kraju imamo i gumb pomocu kojeg se te informacije pohranjuju u bazu podataka.Mi nakon sto smo definirali vizualni dio u XML-u
 jos uvijek ne mozemo nista jer nismo implementirali nikakvu logiku, a upravo to se pravi unutar ove klase.Kao pocetak ove klase
 prvo deklariramo imena preko kojih cemo referencirati te View-ove(tri EditText-a i jedan Button).Nakon toga imenima tih varijabli
 dodjelimo te View-e iz XML-a pomocu metode findViewById().Nakon toga navodimo sta ce se desiti kada kliknemo misem na gumb,definirali
 smo da ce se prilikom klika na gumb izvesti metoda saveTask() koju cemo deklarirati u sljedecem koraku.saveTask() metoda nam sluzi
 kako bi ono sto je korisnik unio u polja EditText-a pohranili u bazu podataka.Na pocetku ove metode tekst koji se unio u svaki
 EditText se pohranjuje u odgovarajuce varijable(sTask,sDesc,sFinishBy) pomocu metoda getText() i toString().Sada kada imamo tekst
 pohranjen unutar varijabli onda mozemo izvrsavati i kontrolu, u nasem slucaju ako korisnik nije nista unio u polje EditText-a onda
 se na kraju polja prikazuje mali crveni usklicnik sa popup porukom da nije unio nikakav tekst a stisnuo je dalje i tako za sva tri
 polja.Poslije toga se obavlja glavni zadatak u ovoj klasi, a to je unosenje podataka koje je korisnik unio u bazu podataka.To se radi
 u nested(inner) klasi koja nasljeđuje AsyncTask klasu,a ova klasa nam sluzi za obavljanje teskih zadataka u zasebnom thread-u.Ako bi
 proces spremanja i povlacenja podataka iz baze podataka radili na main UI thread-u onda bi to prouzrocilo aplikaciju da se crasha jer
 bi main UI thread bio preopterecen, pa se zbog toga ovakvi teski zadati obavljaju u backgroundu pomocu metode doInBackground() i onda
 se rezultati koji se dobiju u doInBackground metodi salju metodi onPostExecute() koja ima zadatak da ta rjesenja posalje main UI thread-u
 kako bi se on mogao azurirati.

------------------
UpdateTaskActivity
------------------
-Osim Main aktivnosti koja se prikazuje pri pokretanju aplikacije kao prvi prozor mi također imamo jos dvije aktivnosti, a to su
 AddTastActivity i UpdateTaskActivity.

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