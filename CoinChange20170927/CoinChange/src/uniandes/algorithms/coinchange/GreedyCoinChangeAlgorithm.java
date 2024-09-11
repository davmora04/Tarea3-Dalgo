package uniandes.algorithms.coinchange;

public class GreedyCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int valorTotal, int[] denominaciones) {
        int[] resultado = new int[denominaciones.length];
        for (int i = denominaciones.length - 1; i >= 0; i--) {
            resultado[i] = valorTotal / denominaciones[i];
            valorTotal %= denominaciones[i];
        }
        return resultado;
    }
}
