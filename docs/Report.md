# Indice

<ul>
  <li><strong>1</strong>. Introduzione</li>
  <li><strong>2</strong>. Modello di dominio</li>
  <li><strong>3</strong>. Requisiti specifici</li>
    <ul>
    <li><strong>3.1.</strong> Requisiti funzionali</li>
    <li><strong>3.2.</strong> Requisiti non funzionali</li>
    </ul>
  </li>
  <li><strong>5</strong>. OO Design</li>
</ul>

---

## Introduzione

Wordle è un gioco di parole:
* Indovina una parola di 5 lettere in 6 tentativi
* Dopo ogni tentativo, i colori delle tessere cambieranno per mostrare il **feedback**

![start](./img/wordle_start.png)

![feedback](./img/wordle_feedback.png)

![end](./img/wordle_end.png)

[Versione ufficiale in inglese](www.nytimes.com/games/wordle)  

### Variante:
* Interfaccia a linea di comando (CLI)
* Parole in lingua italiana 

---

## Modello di dominio

![ModelloDiDominio](./img/modelloDiDominio.png)

---

## Requisiti specifici

### Requisiti funzionali

<ul>
<li> <p><strong>Come giocatore voglio mostrare l'help con elenco comandi</strong><br>

**_Criteri di accettazione_**<br>
Al comando **/help**<br> 
o invocando l'app con flag _--help_ o _-h_

il risultato è una descrizione concisa, che normalmente appare all'avvio del programma, seguita dalla lista di comandi disponibili, uno per riga, come da esempio successivo:<br>
•	gioca<br>
•	esci<br>
•	...<br></p></li>

<li> <p><strong>Come giocatore voglio iniziare una nuova partita</strong><br>

**_Criteri di accettazione_**<br>
Al comando **/gioca**<br>

se nessuna partita è in corso l'app mostra la matrice dei tentativi vuota, ma senza mostrare la tastiera, e si predispone a ricevere il primo tentativo o altri comandi.</p></li>

<li> <p><strong>Come giocatore voglio abbandonare la partita</strong><br>

**_Criteri di accettazione_**<br>
Al comando **/abbandona**<br>

l'app chiede conferma<br>
• se la conferma è positiva, l'app comunica l’abbandono<br>
• se la conferma è negativa, l'app si predispone a ricevere un altro tentativo o altri comandi<br></p></li>

<li> <p><strong>Come giocatore voglio chiudere il gioco</strong><br>

**_Criteri di accettazione_**<br>
Al comando **/esci**<br>

l'applicazione chiede conferma<br>
• se la conferma è positiva, l'app si chiude restituendo un _zero exit code_<br>
• se la conferma è negativa, l'app si predispone a ricevere nuovi tentativi o comandi<br></p></li>

<li> <p><strong>Come giocatore voglio effettuare un tentativo per indovinare la parola segreta</strong><br>

**_Criteri di accettazione_**<br>
Digitando caratteri sulla tastiera e invio l’applicazione risponde:<br>
• _Tentativo incompleto_ se i caratteri sono inferiori a quelli della parola segreta<br>
• _Tentativo eccessivo_ se i caratteri sono superiori a quelli della parola segreta<br>
• _Tentativo non valido_ se ci sono caratteri che non corrispondono a lettere dell’alfabeto<br>

altrimenti

riempiendo la prima riga libera della matrice dei tentativi con i caratteri inseriti e colorando lo sfondo di verde se la lettera è nella parola segreta e nel posto giusto, di giallo se la lettera è nella parola segreta ma nel posto sbagliato e di grigio se la lettera non è nella parola segreta.<br>

Se le lettere sono tutte verdi l’applicazione risponde<br>
• _Parola segreta indovinata_<br>
_Numero tentativi: <…>_<br>
e si predispone a nuovi comandi

Se il tentativo fallito è l’ultimo possibile , l’applicazione risponde<br>
• _Hai raggiunto il numero massimo di tentativi._<br>
_La parola segreta è <…>_ <br>
e si predispone a nuovi comandi

Se la parola segreta non è stata impostata l’applicazione risponde<br>
_Parola segreta mancante_<br></p></li>

<li> <p><strong>Come paroliere voglio impostare una parola segreta manualmente</strong><br>

**_Criteri di accettazione_**<br>
Al comando **/nuova** _\<parola>_

l’applicazione risponde:<br>
• _Parola segreta troppo corta_ se i caratteri sono inferiori a quelli del gioco<br>
• _Parola segreta troppo lunga_ se i caratteri sono superiori a quelli del gioco<br>
• _Parola segreta non valida_ se ci sono caratteri che non corrispondono a lettere dell’alfabeto<br>

altrimenti

l’applicazione risponde con _OK_ e memorizza la parola fino a chiusura applicazione.

È possibile cambiare la parola durante una sessione di gioco anche senza uscire dall’applicazione.<br></p></li>

<li> <p><strong>Come paroliere voglio mostrare la parola segreta</strong><br>

**_Criteri di accettazione_**<br>
Al comando **/mostra**

l’applicazione risponde visualizzando la parola segreta<br></p></li>
</ul>

### Requisiti non funzionali
<ul>
<li> <p><strong>Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16</strong><br>
Elenco di terminali supportati<br>
Linux:<br>
- terminal<br>
Mac OS<br>
- terminal<br>
Windows<br>
- Powershell<br>
- Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ....)<br>

Comando per l’esecuzione del container<br>
Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, il comando Docker da usare per eseguire il container contenente l’applicazione è:<br>

`docker run --rm -it ghcr.io/softeng2122-inf-uniba/wordle-floyd:latest`

</p></li>
</ul>

---

## OO Design

### Come giocatore voglio mostrare l'help con elenco comandi (#20)

![ClassDiag1](./img/class_issue%2320.png)
Diagramma di sequenza se viene invocato /help fuori dalla partita
![SeqDiag1](./img/seq_issue%2320.png)
Diagramma di sequenza se viene invocato /help dentro alla partita
![SeqDiag1.2](./img/seq_issue%2320%20n2.png)

---

### Come giocatore voglio iniziare una nuova partita (#21)

![ClassDiag2](./img/class_issue%2321.png)
![SeqDiag2](./img/seq_issue%2321.png)

---

### Come paroliere voglio impostare una parola segreta manualmente (#18)

![ClassDiag3](./img/class_issue%2318.png)
![SeqDiag3](./img/seq_issue%2318.png)

---

### Come 