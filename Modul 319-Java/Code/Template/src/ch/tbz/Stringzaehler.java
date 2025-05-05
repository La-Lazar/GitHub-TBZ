package ch.tbz;

import static ch.tbz.lib.Input.inputString;

public class Stringzaehler {

    public static void main(String[] args) {

    String stringSchreiben;
    stringSchreiben = inputString("Schreibe einen Satz");

    int anzahlLeerzeichen = 0;
    for (int i = 0; i < stringSchreiben.length(); i++) {
        if (stringSchreiben.charAt(i) == ' ') {
            anzahlLeerzeichen++;
        }
    }
    anzahlLeerzeichen = anzahlLeerzeichen + 1;
    System.out.println("Es hat " + anzahlLeerzeichen + " Wörter");

    }
}
