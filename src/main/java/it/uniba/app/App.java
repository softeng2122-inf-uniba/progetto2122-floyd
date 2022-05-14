package it.uniba.app;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public final class App {
    
   

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Wordsmith user = new Wordsmith();

       // Diamo per scontato che gli argomenti possibili siano esclusivamente 1 di numero e che sia esattamente o uno o l'altro
       if (args.length == 1){
           if (args[0].equals("--help") || args[0].equals("-h")){
               Wordsmith.showHelp();
           }
       } 

       while (true){
            String userInput = UserInput.get();
        
           switch (userInput){
               case "/help":{
                   Wordsmith.showHelp();
                   break;
               }
               default: {
                   System.out.println("Comando non riconosciuto o attualmente non disponibile. /help per visualizzare la lista dei comandi.");
                   break;
               }
           }
       }
    }
}
