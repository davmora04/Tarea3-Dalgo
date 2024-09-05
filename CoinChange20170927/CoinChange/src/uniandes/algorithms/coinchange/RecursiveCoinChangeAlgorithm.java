package uniandes.algorithms.coinchange;

public class RecursiveCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        int[] result = new int[denominations.length];
        return recursiveCoinChange(totalValue, denominations, result);
    }

    private int[] recursiveCoinChange(int totalValue, int[] denominations, int[] result) {
        if (totalValue == 0) {
            return result;
        }
        int minCoins = Integer.MAX_VALUE;
        int bestCoin = -1;
        for (int i = 0; i < denominations.length; i++) {
            if (denominations[i] <= totalValue) {
                int[] tempResult = result.clone();
                tempResult[i]++;
                int[] subResult = recursiveCoinChange(totalValue - denominations[i], denominations, tempResult);
                int totalCoins = 0;
                for (int coin : subResult) {
                    totalCoins += coin;
                }
                if (totalCoins < minCoins) {
                    minCoins = totalCoins;
                    bestCoin = i;
                    result = subResult;
                }
            }
        }
        return result;
    }
}
