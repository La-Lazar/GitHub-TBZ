package ch.tbz.lib;

import static ch.tbz.lib.Input.*;

public class Rasterzeichner {
    public static void main(String[] args) {
        String iZahl = inputString("Gib die Größe des Rasters ein: ");
        int iVert = Integer.parseInt(iZahl);
        int iHor = iVert;
        String sZeile;

        System.out.println("\nKomplett gefülltes Raster:");
        for (int i = 0; i < iVert; i++) {
            for (int j = 0; j < iHor; j++) {
                System.out.print("X ");
            }
            System.out.println();
        }

        System.out.println("\nNur Umrandung:");
        for (int i = 0; i < iVert; i++) {
            for (int j = 0; j < iHor; j++) {
                if (i == 0 || i == iVert - 1 || j == 0 || j == iHor - 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        System.out.println("\nAbwechselnde Linien (X und *):");
        for (int i = 0; i < iVert; i++) {
            for (int j = 0; j < iHor; j++) {
                char zeichen = ((i + j) % 2 == 0) ? 'X' : '*';
                System.out.print(zeichen + " ");
            }
            System.out.println();
        }

        System.out.println("\nMultiplikationsraster:");
        for (int i = 1; i <= iVert; i++) {
            for (int j = 1; j <= iHor; j++) {
                sZeile = (i * j) + "\t";
                System.out.print(sZeile);
            }
            System.out.println();
        }

        System.out.println("\nDas Programm wird nun beendet.");
    }
}
