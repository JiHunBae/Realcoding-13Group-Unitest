package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BaseballNumber {
    private int[] number;
    private int strike, ball;

    public int[] getNumber() {
        return number;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
