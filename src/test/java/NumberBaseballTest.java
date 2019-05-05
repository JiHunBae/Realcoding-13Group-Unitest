
import domain.BaseballNumber;
import org.junit.Test;
import service.NumberBaseball;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NumberBaseballTest {

    /*
     * 1 ~ 2 정태용
     * 3 ~ 4 강영균
     * 5 ~ 7 김석호
     * 8 ~ 10 배지훈
     */

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
        for (int i = 0; i < checkNum.length() && !checkRepetition; ++i) {
            for (int j = i + 1; j < checkNum.length(); ++j) {
                if (checkNum.charAt(i) == checkNum.charAt(j)) {
                    checkRepetition = true;
                    break;
                }
            }
        }
        assertThat(checkRepetition, is(false));
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
        for (int i = 0; i < solution.length(); ++i)
            stringBuilder.append(solution.charAt(i));

        String re = stringBuilder.toString();
        numberBaseball.inputBaseballNum(re);
        BaseballNumber baseballNum = numberBaseball.getInputLog().get(0);
        assertThat(baseballNum.getStrike(), is(4));
    }

    // 강영균 구현 부분 끝
    // 김석호 구현 부분 시작점
    // 5. Ball값을 카운팅하는 함수가 제대로 작동하여 값이 올바르게 반환되는지 확인
    @Test
    public void checkingCorrectActionOfBall() {
        StringBuilder stringBuilder = new StringBuilder();
        NumberBaseball numberBaseball = new NumberBaseball();
        String solution = numberBaseball.solutionNumber();

        // Strike값이 4인 경우 Ball값이 0임을 확인
        for (int i = 0; i < solution.length(); ++i)
            stringBuilder.append(solution.charAt(i));

        String re = stringBuilder.toString();
        numberBaseball.inputBaseballNum(re);
        BaseballNumber baseballNum = numberBaseball.getInputLog().get(0);
        assertThat(baseballNum.getBall(), is(0));

        stringBuilder.setLength(0); // StringBuilder 초기화

        // 정답에 있는 숫자 4개가 모두 존재하나 위치가 모두 달라 Ball값이 4인 경우

        for (int i = 1; i < solution.length(); ++i)
            stringBuilder.append(solution.charAt(i));

        stringBuilder.append(solution.charAt(0));
        re = stringBuilder.toString();
        numberBaseball.inputBaseballNum(re);
        baseballNum = numberBaseball.getInputLog().get(1);
        assertThat(baseballNum.getBall(), is(4));
    }

    // 6.SolutionNumber 실행시 반드시 1234를 반환하게하고 제대로 작동하는지 확인
    @Test
    public void whenDoSolutionNumberMustReturnOneToFour() {
        NumberBaseball numberBaseball = mock(NumberBaseball.class);

        when(numberBaseball.solutionNumber()).thenReturn("1234");
        assertThat(numberBaseball.solutionNumber(), is("1234"));
    }

    // 7. NotNumberString 값을 inputBaseball함수의 인자 값으로 넣어주고 실행시 Exception 발생
    @Test(expected = Exception.class)
    public void ifSetNotNumberStringOccurException() {
        NumberBaseball numberBaseball = mock(NumberBaseball.class);

        doThrow(new Exception()).when(numberBaseball).inputBaseballNum(eq("NotNumberString"));
        numberBaseball.inputBaseballNum("NotNumberString");
    }
    // 김석호 구현 부분 끝

    // 배지훈 구현 부분 시작점
    // 8.inputBaseballNum함수에 1234 문자열을 인자 값으로 주고 실행하였는지 확인
    @Test
    public void checkDoInputBaseballNumParameterOneToFour() {
        NumberBaseball numberBaseball = mock(NumberBaseball.class);
        numberBaseball.inputBaseballNum("1234");
        verify(numberBaseball, times(1)).inputBaseballNum("1234");
    }

    // 9.시도한 입력횟수를 확인하는 함수를 호출했는지 확인
    @Test
    public void checkDoTryCount() {
        NumberBaseball numberBaseball =mock(NumberBaseball.class);
        numberBaseball.inputBaseballNum("5432");
        System.out.println(numberBaseball.tryNum());
        numberBaseball.inputBaseballNum("6837");
        System.out.println(numberBaseball.tryNum());
        verify(numberBaseball, times(2)).tryNum();
    }

    // 10.정답을 이미 맞춘 경우 false를 제대로 반환하는지 확인
    @Test
    public void checkInputChanceLimit() {
        NumberBaseball numberBaseball = new NumberBaseball();
        String solutionNumber = numberBaseball.solutionNumber();
        numberBaseball.inputBaseballNum(solutionNumber); // 정답입력
        assertThat(numberBaseball.isCorrectAnswer(), is(true));
    }
    // 배지훈 구현 부분 끝
}
