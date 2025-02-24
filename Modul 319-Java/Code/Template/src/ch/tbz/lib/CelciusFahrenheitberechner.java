package ch.tbz.lib;

import static ch.tbz.lib.Input.*;

public class CelciusFahrenheitberechner {

    public static void main(String[] args) {

        String wasRechnen;
        int grad;
        double resultat;  // Ergebnis kann Nachkommastellen haben, daher double

        wasRechnen = inputString("Gib C oder F ein:");
        grad = inputInt("Wieviel Grad hast du?");

        if (wasRechnen.equalsIgnoreCase("C")) {
            resultat = (grad * 9.0 / 5) + 32;
            System.out.println(grad + "°C sind " + resultat + "°F.");
        } else if (wasRechnen.equalsIgnoreCase("F")) {
            resultat = (grad - 32) * 5.0 / 9;
            System.out.println(grad + "°F sind " + resultat + "°C.");
        } else {
            System.out.println("Falsche Eingabe, bitte 'C' oder 'F' eingeben.");
        }
    }
}
