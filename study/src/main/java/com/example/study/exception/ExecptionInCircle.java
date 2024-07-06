package com.example.study.exception;

public class ExecptionInCircle {
    public void test () throws CustomException {
        for(int i = 1; i < 2; i++) {
            try {
                int j = i;
            }
            catch (Exception e) {
                throw new CustomException();
            }

        }
    }
}
