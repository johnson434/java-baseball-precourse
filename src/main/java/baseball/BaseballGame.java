package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class BaseballGame {
    private final int lengthOfInput;
    private final int randomNum;
    private final int[] digitCountOfRandom;

    public BaseballGame() {
        lengthOfInput = 3;
        digitCountOfRandom = new int[10];

        int cntOfNeededDigits = lengthOfInput;
        int sumOfDigits = 0;
        while (cntOfNeededDigits > 0) {
            int temp = Randoms.pickNumberInRange(1, 9);
            if (digitCountOfRandom[temp] != 0) {
                continue;
            }

            digitCountOfRandom[temp]++;
            sumOfDigits = sumOfDigits * 10 + temp;
            cntOfNeededDigits--;
        }
        this.randomNum = sumOfDigits;
    }

    public void startBaseballGame() {
        while (true) {
            int inputNum;
            int[] cntOfNumOfInputNum = new int[10];
            System.out.println("숫자를 입력해주세요 : ");
            inputNum = getNumbers();

            int index = inputNum;
            for (int i = 0; i < lengthOfInput; i++) {
                cntOfNumOfInputNum[index % 10]++;
                index /= 10;
            }

            int strikeCnt = getStrikeCnt(inputNum);
            int cntOfEqualDigits = getEqualNumCnt(cntOfNumOfInputNum);
            int ballCnt = cntOfEqualDigits - strikeCnt;

            StringBuilder resultMessage = new StringBuilder();

            if (strikeCnt == lengthOfInput) {
                resultMessage.append("3스트라이크");
                System.out.println(resultMessage);
                return;
            }
            if (ballCnt == 0) {
                resultMessage.append("낫싱");
            } else {
                resultMessage.append(ballCnt).append("볼 ").append(strikeCnt).append("스트라이크");
            }
            System.out.println(resultMessage);
        }
    }

    private int getEqualNumCnt(int[] cntOfNumOfInputNum) {
        int ballCnt = 0;
        for (int i = 0; i < digitCountOfRandom.length; i++) {
            ballCnt += Math.min(digitCountOfRandom[i], cntOfNumOfInputNum[i]);
        }
        return ballCnt;
    }

    private int getStrikeCnt(int inputNum) {
        int strikeCnt = 0;
        int numForCheckDigits = randomNum;

        do {
            if (numForCheckDigits % 10 == inputNum % 10) {
                strikeCnt++;
            }
            numForCheckDigits /= 10;
            inputNum /= 10;
        } while (inputNum != 0);

        return strikeCnt;
    }

    public int getNumbers()  {
        String input = Console.readLine();
        int inputNumber = Integer.parseInt(input);
        if (inputNumber > 999) {
            throw new IllegalArgumentException("숫자의 값이 3자리가 아닙니다.");
        }
        return inputNumber;
    }
}