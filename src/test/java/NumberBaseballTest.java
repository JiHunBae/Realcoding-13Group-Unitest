import domain.BaseballNumber;
import org.junit.Test;
import service.NumberBaseball;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NumberBaseballTest {
    // 정태용 구현 부분 시작점
    // 1. 객체에 입력값이 잘 들어가는지 확인(숫자 4개 생성 확인)
    @Test
    public void checkInputNumber() {
        NumberBaseball numberBaseball = new NumberBaseball();
        assertThat(numberBaseball.solutionNumber().length(), is(4)); // 4자리의 숫자가 잘 들어갔는지 확인
    }

    // 2. 정답 숫자가 중복되는 값이 없음을 확인
    @Test
    public void checkNumberIsRepetition() {
        NumberBaseball numberBaseball = new NumberBaseball();
        String checkNum = numberBaseball.solutionNumber();
        boolean checkRepetition = false;
        for(int i = 0; i < checkNum.length() && !checkRepetition; ++i) {
            for(int j = i+1; j < checkNum.length(); ++j) {
                if(checkNum.charAt(i) == checkNum.charAt(j)) {
                    checkRepetition = true;
                    break;
                }
            }
        }
        assertThat(checkRepetition,  is(false));
    }
    // 정태용 구현 부분 끝
    // 강영균 구현 부분 시작점
    // 3. 4개의 숫자를 입력하지 않은 경우 inputBaseballNum 함수에서 IndexOutOfBoundsException 발생시킴을 확인
    @Test(expected = IndexOutOfBoundsException.class)
    public void checkInputLengthIsFour() {
        // 4개의 숫자를 입력하지 않으면 inputBaseballNum 함수에서 IndexOutOfBoundsException을 발생시키는 것을 이용
        NumberBaseball numberBaseball = new NumberBaseball();
        numberBaseball.inputBaseballNum("123");
    }

    // 4. Strike값을 카운팅하는 함수가 제대로 작동하여 값이 올바르게 반환되는지 확인
    @Test
    public void checkingCorrectActionOfStrike() {
        StringBuilder stringBuilder = new StringBuilder();
        NumberBaseball numberBaseball = new NumberBaseball();
        String solution = numberBaseball.solutionNumber();
        for(int i = 0; i < solution.length(); ++i)
            stringBuilder.append(solution.charAt(i));

        String re = stringBuilder.toString();
        numberBaseball.inputBaseballNum(re);
        BaseballNumber baseballNum = numberBaseball.getInputLog().get(0);
        assertThat(baseballNum.getStrike(), is(4));
    }
    // 강영균 구현 부분 끝
