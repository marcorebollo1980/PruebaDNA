package com.adn.test;

public class MutacionADN {

    public static void main(String[] args) {
        //Secuencia de ADN en un arreglo de cadenas
        String[] secuenciaADN = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
        };
        
        boolean tieneMutacion = hasMutation(secuenciaADN);
        
        if (tieneMutacion) {
            System.out.println("Se encontró una mutación.");
        } else {
            System.out.println("No se encontró una mutación.");
        }
    }
    
    public static boolean hasMutation(String[] dna) {
        int n = dna.length;
        
        // Verificar filas y columnas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char base = dna[i].charAt(j);
                
                // Verificar horizontalmente
                if (j + 3 < n &&
                    base == dna[i].charAt(j + 1) &&
                    base == dna[i].charAt(j + 2) &&
                    base == dna[i].charAt(j + 3)) {
                    return true;
                }
                
                // Verificar verticalmente
                if (i + 3 < n &&
                    base == dna[i + 1].charAt(j) &&
                    base == dna[i + 2].charAt(j) &&
                    base == dna[i + 3].charAt(j)) {
                    return true;
                }
                
                // Verificar diagonalmente hacia abajo y derecha
                if (i + 3 < n && j + 3 < n &&
                    base == dna[i + 1].charAt(j + 1) &&
                    base == dna[i + 2].charAt(j + 2) &&
                    base == dna[i + 3].charAt(j + 3)) {
                    return true;
                }
                
                // Verificar diagonalmente hacia abajo e izquierda
                if (i + 3 < n && j - 3 >= 0 &&
                    base == dna[i + 1].charAt(j - 1) &&
                    base == dna[i + 2].charAt(j - 2) &&
                    base == dna[i + 3].charAt(j - 3)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
