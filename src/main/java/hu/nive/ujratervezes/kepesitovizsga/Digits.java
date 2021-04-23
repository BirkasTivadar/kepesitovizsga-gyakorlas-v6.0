package hu.nive.ujratervezes.kepesitovizsga;

public class Digits {

    public int getNumbers() {
        int result = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (Math.abs(i - j) == 5) {
                    result++;
                }
            }
        }
        return result;
    }
}
