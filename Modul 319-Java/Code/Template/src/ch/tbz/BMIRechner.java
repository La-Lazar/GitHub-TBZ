package ch.tbz;

import static ch.tbz.lib.Input.inputDouble;
import static ch.tbz.lib.Input.inputString;

public class BMIRechner {

    public static void main(String[] args) {
        String nochmal;

        do {
            // EINGABE
            double[] daten = eingabe();

            // VERARBEITUNG
            double bmi = verarbeitung(daten[0], daten[1]);

            // AUSGABE
            ausgabe(bmi);

            nochmal = inputString("Möchtest du einen weiteren BMI berechnen? (ja/nein): ").toLowerCase();

        } while (nochmal.equals("ja"));
    }

    //Hier wird die Eingabe gespeichert (Gewicht und die Grösse) in einem double[]
    public static double[] eingabe() {
        double gewicht = inputDouble("Gib dein Gewicht in kg ein: ");
        double groesse = inputDouble("Gib deine Körpergröße in Metern ein (z.B. 1.75): ");
        return new double[]{gewicht, groesse};
    }

    //Hier wird das double[] genommen und verarbeitet das man den BMI hat
    public static double verarbeitung(double gewicht, double groesse) {
        return gewicht / (groesse * groesse);
    }

    //Hier wird entschieden was für einen BMI der Nutzer hat
    public static void ausgabe(double bmi) {
        System.out.printf("Dein BMI beträgt: %.2f\n", bmi);

        if (bmi < 18.5) {
            System.out.println("Du hast Untergewicht.");
        } else if (bmi < 25) {
            System.out.println("Du hast Normalgewicht.");
        } else if (bmi < 30) {
            System.out.println("Du hast Übergewicht.");
        } else {
            System.out.println("Du hast starkes Übergewicht.");
        }
    }
}
