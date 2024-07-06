package com.example.study;

import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolFabonacciTest {
        public static void main(String[] args) throws Exception {

            // fork/join:
            /*Fibonacci fibonacci = new Fibonacci(40);
            long startTime = System.currentTimeMillis();
            Integer result = ForkJoinPool.commonPool().invoke(fibonacci);
            long endTime = System.currentTimeMillis();
            System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");*/
        }


}

class Fibonacci extends RecursiveTask<Integer>
{
    final int n;
    Fibonacci(int n)
    {
        this.n = n;
    }
    @Override
    public Integer compute()
    {
        if(n <= 1) return n;
        Fibonacci f1 = new Fibonacci(n - 1);
//        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
//        f2.fork();
        invokeAll(f1, f2);
//        return f2.compute() + f1.join();
        return f2.join() + f1.join();
//        return f2.compute() + f1.compute();
    }
}
