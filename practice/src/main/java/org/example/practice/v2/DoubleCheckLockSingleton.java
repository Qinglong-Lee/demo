package org.example.practice.v2;

public class DoubleCheckLockSingleton {
    private static volatile DoubleCheckLockSingleton singletonInstance;

    public static DoubleCheckLockSingleton getSingleton() {
        if(singletonInstance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if(singletonInstance == null) {
                    singletonInstance = new DoubleCheckLockSingleton();
                }
            }
        }

        return singletonInstance;
    }
}
