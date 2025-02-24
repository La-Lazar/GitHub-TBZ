package ch.tbz.lib;

import static ch.tbz.lib.Input.*;

public class CurrencyConverter {

    public static void main(String[] args) {

        boolean abfrage = true;
        double schuhkosten;
        double transportCustomerkosten;
        double exchangerate;
        double resultat1;
        String wahl;

        do {
            schuhkosten = inputDouble("Wie viel kostet der Schuh?");
            transportCustomerkosten = inputDouble("Was sind die Transport- und Zollkosten?");
            exchangerate = inputDouble("Was ist der Wechselkurs?");

            wahl = inputString("Möchtest du in Dollar (USD) oder CHF umrechnen? Gib 'USD' oder 'CHF' ein:");

            resultat1 = (schuhkosten + transportCustomerkosten) * exchangerate;

            System.out.println("Deine Gesamtkosten sind: " + resultat1 + " " + wahl);

            String sigma = inputString("Möchtest du eine weitere Berechnung durchführen? (J/N)");
            
            if (sigma.equalsIgnoreCase("N")) {
                abfrage = false;
            } else if (!sigma.equalsIgnoreCase("J")) {
                System.out.println("Falsche Eingabe! Bitte 'J' oder 'N' eingeben.");
            }

        } while (abfrage);

        System.out.println("Programm beendet.");
    }
}
