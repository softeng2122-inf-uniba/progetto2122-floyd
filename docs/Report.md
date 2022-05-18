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

<li> <p>
