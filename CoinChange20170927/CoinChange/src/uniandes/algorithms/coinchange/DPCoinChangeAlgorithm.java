package uniandes.algorithms.coinchange;

import java.util.Arrays;

public class DPCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        int[] dp = new int[totalValue + 1];
        int[] monedasUsadas = new int[totalValue + 1];
        Arrays.fill(dp, totalValue + 1);
        dp[0] = 0;

        for (int valorActual = 1; valorActual <= totalValue; valorActual++) {
            for (int indiceDenominacion = 0; indiceDenominacion < denominations.length; indiceDenominacion++) {
                if (denominations[indiceDenominacion] <= valorActual) {
                    if (dp[valorActual - denominations[indiceDenominacion]] + 1 < dp[valorActual]) {
                        dp[valorActual] = dp[valorActual - denominations[indiceDenominacion]] + 1;
                        monedasUsadas[valorActual] = indiceDenominacion;
                    }
                }
            }
        }

        int[] resultado = new int[denominations.length];
        while (totalValue > 0) {
            int indiceMoneda = monedasUsadas[totalValue];
            resultado[indiceMoneda]++;
            totalValue -= denominations[indiceMoneda];
        }
        return resultado;
    }
}
