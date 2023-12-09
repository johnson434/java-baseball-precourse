package baseball;


import camp.nextstep.edu.missionutils.Console;


public class Application {
    public static void main(String[] args) {

        while (true) {
            // 입력은 3자리수
            BaseballGame bg = new BaseballGame();
            bg.startBaseballGame();

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            if (getInputForRetry() == 2) {
                System.out.println("게임 종료");
                break;
            }
        }
    }

    private static int getInputForRetry() {
        String input;
        int inputNum;
        input = Console.readLine();

        try {
            inputNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Retry 값은 '1' 또는 '2'입니다.");
        }

        if (inputNum != 1 && inputNum != 2) {
            throw new IllegalArgumentException("새로 시작 입력 값이 '1' 또는 '2'가 아닙니다.");
        }
        return inputNum;
    }
}