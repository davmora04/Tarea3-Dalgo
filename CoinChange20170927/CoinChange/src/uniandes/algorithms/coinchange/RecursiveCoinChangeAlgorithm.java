package uniandes.algorithms.coinchange;

import java.util.Arrays;

public class RecursiveCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int valorTotal, int[] denominaciones) {
        int[] resultado = new int[denominaciones.length];
        int[] memo = new int[valorTotal + 1];
        
        Arrays.fill(memo, -1);
        memo[0] = 0;

        calcularMinMonedas(valorTotal, denominaciones, memo);

        reconstruirResultado(valorTotal, denominaciones, resultado, memo);
        
        return resultado;
    }

    private int calcularMinMonedas(int valorTotal, int[] denominaciones, int[] memo) {
        if (valorTotal < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[valorTotal] != -1) {
            return memo[valorTotal];  
        }

        int minMonedas = Integer.MAX_VALUE;

        for (int i = 0; i < denominaciones.length; i++) {
            int moneda = denominaciones[i];
            if (moneda <= valorTotal) {
                int monedasActuales = calcularMinMonedas(valorTotal - moneda, denominaciones, memo);
    
                if (monedasActuales != Integer.MAX_VALUE) {
                    minMonedas = Math.min(minMonedas, monedasActuales + 1);
                }
            }
        }

        memo[valorTotal] = minMonedas;
        return minMonedas;
    }

    private void reconstruirResultado(int valorTotal, int[] denominaciones, int[] resultado, int[] memo) {
        while (valorTotal > 0) {
            for (int i = 0; i < denominaciones.length; i++) {
                int moneda = denominaciones[i];
                if (moneda <= valorTotal && memo[valorTotal - moneda] == memo[valorTotal] - 1) {
                    resultado[i]++;
                    valorTotal -= moneda;
                    break;
                }
            }
        }
    }
}
