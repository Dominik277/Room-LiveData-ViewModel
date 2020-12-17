/*

------------------
MainMainActivity
------------------
-Ova klasa je jako jednostavna.Kao i na pocetku svake klase prvo deklariramo varijable i tipove tih varijabli koje ce biti
 prikazane za tu određenu aktivnost.U aktivnosti koju predstavlja ova klasa imamo u XML-u definirane RecyclerView i Floating
 Action Button i upravo stvaramo varijable tih tipova kako bi ih mogli referencirati dalje u kodu.I sada dolazimo do toga
 unutar onCreate() metode te pomocu metode findViewById() idemo u XML i pojedine View-ove pohranjujemo unutar objekata koje
 smo prethodno deklarirali, u nasem slucaju to su recyclerview_tasks i buttonAddTask.Za RecyclerView postavljamo LayoutManager
 koji nam sluzi kako bi odrerili kako ce RecyclerView biti poslozen, imamo LinearLayoutManager(u nasem slucaju),GridLayoutManager
 i FrameLayoutManager.Nakon toga smo deklarirali clickListener za floatin action button, odnosno definirali smo sta ce se desiti
 prilikom klika na njega.U nasem slucaju prilikom klika na floating action button se otvara novi activity pomocu objekta klase
 Intent, a activity koji se otvara je AddTaskActivity.I na kraju imamo metodu getTask() koja sluzi kako bi otisla u bazu podataka
 i dohvatila podatke koje smo trazili.Ova metoda također radi u background thread-u kako bi se oslobodio UI thread pomocu metoda
 doInBackground() i onPostExecute().I isto također kao i u svakoj klasi kako bi upogonili sve sto smo deklarirali unutar klase
 koja nasljeđuje AsyncTask moramo napraviti objekt te klase i pozvati metodu execute() preko tog objekta.

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
 AddTastActivity i UpdateTaskActivity.Pri otvaranju ove activity-a koji smo definirali ovom klasom cemo vidjeti naslov "Update Task"
 tri EditText-a sa hintovima i dva gumba koji sluze za updejtanje i brisanje pojedinog podsjetnika.Kao i uvijek, na pocetku smo
 deklarirali imena varijabli i kojih tipova su te varijable kako bi ih mogli referencirati u daljnjem toku programa.Nakon toga smo
 tim varijabla dodjelili View-ove koji se nalaze u XML-u pomocu metode findViewById(), na taj način smo od XML view-ova stvorili
 java objekte tipa View.Poslije toga smo svakom gumbu dali neku funkcionalnost.Gumbu za updejtanje smo dali funkcionalnost da kada se
 klikne poziva se metoda updateTask koju cemo deklarirati u daljenjem kodu te nam se ispisuje Toast poruka na dnu ekrana sta smo tocno
 kliknuli.Također smo dali funkcionalnost u delete gumbu koji osim sto poziva metodu deleteTask() koju cemo također deklarirati u sljedecih
 par linija također nam izbacuje AlertDialog sa pozitivnim i negativnim gumbovima i naslovom "Are you sure".Nakon toga imamo metodu loadTask()
 kojoj je zadaca da ucita podatke pomocu metode setText().Poslije toga dolazimo do metode updateTask().Prva stvar koju radimo unutar updateTask()
 metode je da ono sto korisnik unese unutar EditTexta mi spremamo u varijeble (sTask,sDesc,sFinishBy) pomocu metoda getText() i toString().Nakon
 smo napravili provjeru ako je korisnik kojim slucajem kliknuo dalje a da nije upisao tekst u polje onda mu se pokazuje mali crveni uslikcnik
 sa popup porukom koja ga upozorava da je polje ostavio prazno i tako za sva tri polja.Nakon te provjere dolazi se do glavnog dijela ove metode
 a to je updejtanje podataka unutar baze podataka a to se izvodi u background thread-u uz pomoc AsyncTask klase, ovu operaciju updejtanja baze
 podatake moramo raditi u background threa-u jer kada bi ovo radili u main UI thread-u onda bi nam se aplikacija stalno crashala.Sav teski posao
 se radi unutar metode doInBackground() i onda se rezultati koji su se izvrsili unutar te metode salji u onPostExecute() metodu koja ima mogucnost
 azuriranja UI-a.Isto sve radimo i sa metodom deleteTask() koja se također izvodi u zasebnom background thread-u kako bi se osiguralo da
 aplikacija brze radi i ne crasha se.I na kraju kako bi upogonili sve ovo gore sto smo napravili moramo kreirati objekt ove klase i preko njega
 pozvati metodu execute().

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
-Ova klasa nam sluzi kao adapter za RecyclerView.Pri planiranju aplikacije odlucili smo da cemo podatke na ekranu prikazivati pomocu RecyclerView-a
 Da bi podaci dosli do RecyclerView-a prvo moramo kreirati adapter koji to omogucava.Adapter također sluzi i za određivanje na koji nacin ce podaci
 biti prikazani u RecyclerView-u.RecyclerView mozemo zamisliti kao manekenku u izlogu ciji je zadatak samo stajati i prikazivati tipa novu haljinu, a
 adapter mozemo zamisliti kao njenog menađera koji se brine o tome sta ce ona obuci i na koji nacin ce stajati u izlogu.Znaci uloga adaptera je da
 opskrbi RecylcerView sa podacima i kaze mu na koji nacin ce prikazivati podatke.Kao i na pocetku svakle klase deklarirali smo par varijabli tipa
 Context i tipa List<Task>.Nakon toga smo kreirali custom konstruktor koji ce nam sluziti za instanciranje objekata.Posto smo klasu TasksAdapter
 nasljedili od klase RecyclerView.Adapter klase moramo implementirati njene tri metode a to su onCreateViewHolder(),onBindViewHolder()i getItemCount().
 onCreateViewHolder() se poziva samo kada nema stvoren niti jedan ViewHolder te se on stvori pomocu ove metode, onBindViewHolder() metoda sluzi kako bi
 poslala sve podatke koje adapter u sebi ima prema RecyclerView-u na tocno određenoj pozicij, a zna o kojo se pozciji radi jer mu se kao parametar
 predaje position.Kada novi RecylcerView dode na ekrana kada scrollamo taj adapter je prazan, u njemu nema podataka, a upravo ova metoda ce se
 pobrinuti da podaci dodu u taj redak.getItemCount() metoda jednostavno sluzi kako bi vratila broj redaka u RecyclerView-u.Na kraju jos unutar ove
 klase pravimo jos jednu inner(nested) klasu koja nasljeđuje RecyclerView.ViewHolder klasu.Ova klasa nam sluzi kako bi sadrzala sve one View-ove
 koje jedan redak u RecaclerView-u ima.Također smo u nasem slucaju odredili da klikom na pojedini redak se otvara novi activity, a to je UpdateTaskActivity
 pomocu objekta Intent klase.

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