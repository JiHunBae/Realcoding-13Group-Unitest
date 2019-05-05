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
}
