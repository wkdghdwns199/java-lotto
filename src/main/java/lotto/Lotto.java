package lotto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Lotto {
    private final int LOTTERY_NUMBER_LIMIT = 6;
    private final List<Integer> numbers;
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }
    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTERY_NUMBER_LIMIT) {
            System.out.println("[ERROR] 당첨 번호는 정확히 6개를 입력하셔야 합니다.");
            throw new IllegalArgumentException();
        }
        Iterator itrForNumbers = numbers.iterator();
        int[] arrayForCheckOverLap = new int[46];
        while (itrForNumbers.hasNext()){
            int checkIndex = (int)itrForNumbers.next();
            if (arrayForCheckOverLap[checkIndex] == 1){
                System.out.println("[ERROR] 당첨 번호는 중복이 없어야 합니다.");
                throw new IllegalArgumentException();
            }
            arrayForCheckOverLap[checkIndex] = 1;
        }
    }
    public ArrayList<BigInteger> checkLottoWinnings(ArrayList<ArrayList<Integer>> lotteryBundleArray, int bonusWinningNumber) {
        ArrayList<BigInteger> countWinningNumbersCaseArray = new ArrayList<>();
        BigInteger add1 = new BigInteger("1");
        final int SET_TO_INDEX_VALUE = 3;
        for (int index = 0; index < 5; index++){
            countWinningNumbersCaseArray.add(new BigInteger("0"));
        }
        for (ArrayList<Integer> lottery : lotteryBundleArray){
            int settedIndex = countWinningNumbers(lottery,bonusWinningNumber)-SET_TO_INDEX_VALUE;
            if (settedIndex >= 0) {
                countWinningNumbersCaseArray.set(settedIndex, countWinningNumbersCaseArray.get(settedIndex).add(add1));
                System.out.println(countWinningNumbersCaseArray.get(settedIndex));
            }
        }
//        for (int index = 0; index < countWinningNumbersCaseArray.size(); index++){
//            System.out.println(countWinningNumbersCaseArray.get(index));
//        }
        return countWinningNumbersCaseArray;
    }

    private int countWinningNumbers(ArrayList<Integer> lottery, int bonusWinningNumber){
        int countOfWinningNumbers = 0;
        for (int index = 0; index < lottery.size(); index++){
            if (numbers.contains(lottery.get(index))){
                countOfWinningNumbers++;
            }
        }
        if (lottery.contains(bonusWinningNumber)) {
            countOfWinningNumbers+=2;
        }
        return countOfWinningNumbers;
    }

    public void printLottoWinningsResult(ArrayList<BigInteger> resultArray, int buyingAmount){
        BigInteger sumOfWinAmount = new BigInteger("0");
        System.out.println("\n당첨 통계\n---");
        sumOfWinAmount = sumOfWinAmount.add(printCase3Correct(resultArray.get(0)));
        sumOfWinAmount = sumOfWinAmount.add(printCase4Correct(resultArray.get(1)));
        sumOfWinAmount = sumOfWinAmount.add(printCase5Correct(resultArray.get(2)));
        sumOfWinAmount = sumOfWinAmount.add(printCase5BonusCorrect(resultArray.get(4)));
        sumOfWinAmount = sumOfWinAmount.add(printCase6Correct(resultArray.get(3)));
        printPercentOfProfit(sumOfWinAmount, Integer.toString(buyingAmount));
    }

    private BigInteger printCase3Correct(BigInteger countForCase) {
        BigInteger tempInteger = new BigInteger("5000");
        System.out.println("3개 일치 (5,000원) - " + countForCase + "개");
        return tempInteger.multiply(countForCase);
    }
    private BigInteger printCase4Correct(BigInteger countForCase) {
        BigInteger tempInteger = new BigInteger("50000");
        System.out.println("4개 일치 (50,000원) - " + countForCase + "개");
        return tempInteger.multiply(countForCase);
    }
    private BigInteger printCase5Correct(BigInteger countForCase) {
        BigInteger tempInteger = new BigInteger("1500000");
        System.out.println("5개 일치 (1,500,000원) - " + countForCase + "개");
        return tempInteger.multiply(countForCase);
    }
    private BigInteger printCase5BonusCorrect(BigInteger countForCase) {
        BigInteger tempInteger = new BigInteger("30000000");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + countForCase + "개");
        return tempInteger.multiply(countForCase);
    }
    private BigInteger printCase6Correct(BigInteger countForCase) {
        BigInteger tempInteger = new BigInteger("2000000000");
        System.out.println("6개 일치 (2,000,000,000원) - " + countForCase + "개");
        return tempInteger.multiply(countForCase);
    }
    private void printPercentOfProfit(BigInteger sumOfWinAmount, String buyingAmountString) {
        BigInteger buyingAmount = new BigInteger(buyingAmountString);

        double percentOfProfit = sumOfWinAmount.doubleValue()/buyingAmount.doubleValue()*100;
        percentOfProfit = Math.round(percentOfProfit * 100)/100.0;

        System.out.println("총 수익률은 "+percentOfProfit+"%입니다.");
    }
}
