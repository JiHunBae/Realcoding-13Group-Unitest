package service;

import domain.BaseballNumber;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class NumberBaseball {
    private int[] number;
    private List<BaseballNumber> inputLog = new ArrayList<BaseballNumber>(); // 지금까지 받은 입력값 정보
    private static boolean correctAnswer = false;

    public static boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public List<BaseballNumber> getInputLog() {
        return inputLog;
    }

    public NumberBaseball() { // 생성자
        initRanNums();
    }

    private void initRanNums(){ // 초기 number 설정 메소드
        int ranNum; // 랜덤 추출 숫자 저장 변수
        Random random = new Random();
        boolean[] chkOverlap = new boolean[10]; // 숫자 중복 체크 변수
        number = new int[4];

        for(int i = 0; i < 4; i++){
            do{
                ranNum = random.nextInt(10); // 0 ~ 9 까지의 랜덤한 수 추출
            }
            while(chkOverlap[ranNum]); // 중복이 아닐때 까지
            chkOverlap[ranNum] = true;
            number[i] = ranNum; // 추출한 랜덤값 부여
        }
    }

    public void inputBaseballNum(String input) {
        int[] arrInput = new int[4];
        int strike = 0, ball = 0;

        if(input.length() != 4) // 4개의 범위를 벗어날 때 예외처리
            throw new IndexOutOfBoundsException("Input Only 4 Numbers");

        for(int i = 0; i < 4; i++){
            arrInput[i] = Integer.parseInt(input.substring(i, i + 1));
        }

        strike = chkStrike(arrInput);
        if(strike == 4)
            correctAnswer = true;

        ball = chkBall(arrInput);

        inputLog.add(new BaseballNumber(number, strike, ball));
    }

    private int chkStrike(int[] input) { // strike 확인 메소드
        int strikeCnt = 0; // strike 카운팅 변수

        for(int i = 0; i < 4; i++){
            if(input[i] == number[i]) // strike check
                strikeCnt++;
        }

        return strikeCnt;
    }

    private int chkBall(int[] input) { // ball 확인 메소드
        int ballCnt = 0; // ball 카운팅 변수

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){ // ball check
                if((input[i] == number[j]) && (i != j))
                    ballCnt++;
            }
        }

        return ballCnt;
    }

    public int tryNum(){ // 지금이 몇번째 시도인지 확인하는 메소드
        return inputLog.size();
    } // 시도한 입력 횟수가 몇번째인지 확인
    public String solutionNumber() {
       StringBuilder stringBuilder = new StringBuilder();
       int[] numValue = this.number;
       for(int i = 0; i < this.number.length; ++i)
           stringBuilder.append(numValue[i]);

       String solutionNum = stringBuilder.toString();
       return solutionNum;
    }


}