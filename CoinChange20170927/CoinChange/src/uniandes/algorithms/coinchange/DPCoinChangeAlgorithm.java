package uniandes.algorithms.coinchange;

import java.util.Arrays;

public class DPCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        int[] dp = new int[totalValue + 1];
        int[] coinUsed = new int[totalValue + 1];
        Arrays.fill(dp, totalValue + 1);
        dp[0] = 0;

        for (int i = 1; i <= totalValue; i++) {
            for (int j = 0; j < denominations.length; j++) {
                if (denominations[j] <= i) {
                    if (dp[i - denominations[j]] + 1 < dp[i]) {
                        dp[i] = dp[i - denominations[j]] + 1;
                        coinUsed[i] = j;
                    }
                }
            }
        }

        int[] result = new int[denominations.length];
        while (totalValue > 0) {
            int coinIndex = coinUsed[totalValue];
            result[coinIndex]++;
            totalValue -= denominations[coinIndex];
        }
        return result;
    }
}
