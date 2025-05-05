package ch.tbz.lib;

import static ch.tbz.lib.Input.*;
import java.util.ArrayList;

// Komplexer Datentyp: Buch
// International Standard Book Number = ISBN

class Buch { // ganze Klase alleine geschrieben
    String titel;
    String autor;
    String isbn;
    int erscheinungsjahr;
    boolean verfuegbar;

    public Buch(String titel, String autor, String isbn, int jahr) {
        this.titel = titel;
        this.autor = autor;
        this.isbn = isbn;
        this.erscheinungsjahr = jahr;
        this.verfuegbar = true;
    }

    public void statusWechseln() { //selber
        this.verfuegbar = !this.verfuegbar;
    }

    public String toString() { //selber
        return titel + " von " + autor + " (" + erscheinungsjahr + ") - ISBN: " + isbn + " - " +
               (verfuegbar ? "Verfügbar" : "Ausgeliehen");
    }
}

// Komplexer Datentyp: Bibliothek
class Bibliothek {
    ArrayList<Buch> buecher = new ArrayList<>();

    public void buchHinzufuegen(Buch buch) { 
        buecher.add(buch);// hier musste ich nachschauen wie man die Bücher in das Array hinzufügt
        System.out.println("Buch wurde hinzugefügt.");
    }

    public void buchSuchen(String suchbegriff) {
        boolean gefunden = false;
        for (Buch buch : buecher) { //hier war ich mir nicht sicher wie ich irritieren sollte
            if (buch.titel.toLowerCase().contains(suchbegriff.toLowerCase()) ||
                buch.autor.toLowerCase().contains(suchbegriff.toLowerCase())) {// das contains musste ich nachschauen
                System.out.println(buch);
                gefunden = true;
            }
        }
        if (!gefunden) {
            System.out.println("Kein Buch gefunden.");
        }
    }

    public void listeAnzeigen() {
        if (buecher.isEmpty()) {
            System.out.println("Keine Bücher vorhanden.");
        } else {
            for (Buch buch : buecher) { //hier war ich mir nicht sicher wie ich irritieren sollte
                System.out.println(buch);
            }
        }
    }

    public void statusAendern(String isbn) {
        for (Buch buch : buecher) { //hier war ich mir nicht sicher wie ich irritieren sollte
            if (buch.isbn.equals(isbn)) {
                buch.statusWechseln();
                System.out.println("Status geändert: " + (buch.verfuegbar ? "Verfügbar" : "Ausgeliehen"));
                return;
            }
        }
        System.out.println("Buch mit dieser ISBN nicht gefunden.");
    }
}

// Hauptklasse
public class MiniBibliothek {//alles selber geschrieben

    public static void main(String[] args) {
        programmStarten();
    }

    public static void programmStarten() {
        Bibliothek bibliothek = new Bibliothek();
        boolean abfrage = true;

        do {

            userInterface();

            int wahl = inputInt("Ihre Wahl: ");

            switch (wahl) {
                case 1:
                    String titel = inputString("Titel: ");
                    String autor = inputString("Autor: ");
                    String isbn = inputString("ISBN: ");
                    int jahr = inputInt("Erscheinungsjahr: ");

                    Buch neuesBuch = new Buch(titel, autor, isbn, jahr);
                    bibliothek.buchHinzufuegen(neuesBuch);
                    break;

                case 2:
                    String suchbegriff = inputString("Titel oder Autor: ");
                    bibliothek.buchSuchen(suchbegriff);
                    break;

                case 3:
                    String isbnStatus = inputString("ISBN des Buches: ");
                    bibliothek.statusAendern(isbnStatus);
                    break;

                case 4:
                    bibliothek.listeAnzeigen();
                    break;

                case 0:
                    abfrage = false;
                    System.out.println("Programm wird beendet...");
                    break;

                default:
                    System.out.println("Ungültige Eingabe!");
            }

        } while (abfrage);
    }
    
    public static void userInterface(){
        System.out.println("\nMini-Bibliothek Menü");
        System.out.println("1. Buch erfassen");
        System.out.println("2. Buch suchen");
        System.out.println("3. Status ändern");
        System.out.println("4. Bücher anzeigen");
        System.out.println("0. Beenden");
    }
}