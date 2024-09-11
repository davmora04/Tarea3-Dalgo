package uniandes.algorithms.coinchange;

import java.util.Arrays;

public class RecursiveCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        int[] result = new int[denominations.length];
        int[] memo = new int[totalValue + 1];
        memo[0] = 0;  

        calculateMinCoins(totalValue, denominations, memo);

        reconstructResult(totalValue, denominations, result, memo);
        
        return result;
    }

    private int calculateMinCoins(int totalValue, int[] denominations, int[] memo) {
        if (memo[totalValue] != -1) {
            return memo[totalValue];
        }

        int minCoins = Integer.MAX_VALUE;

        for (int i = 0; i < denominations.length; i++) {
            int coin = denominations[i];
            if (coin <= totalValue) {
                int currentCoins = calculateMinCoins(totalValue - coin, denominations, memo);
    
                if (currentCoins != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, currentCoins + 1);
                }
            }
        }

        memo[totalValue] = minCoins;
        return minCoins;
    }

    private void reconstructResult(int totalValue, int[] denominations, int[] result, int[] memo) {
        
        while (totalValue > 0) {
            for (int i = 0; i < denominations.length; i++) {
                int coin = denominations[i];
                if (coin <= totalValue && memo[totalValue - coin] == memo[totalValue] - 1) {
                    result[i]++;
                    totalValue -= coin;
                    break;
                }
            }
        }
    }
}
