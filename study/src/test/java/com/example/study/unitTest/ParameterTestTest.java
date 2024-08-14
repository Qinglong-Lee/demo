package com.example.study.unitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParameterTestTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {{1, 2}, {2, 3}});
    }

    @Parameterized.Parameter
    public int in;

    @Parameterized.Parameter(1)
    public int out;

    @Test
    public void increase() {
        int rst = new ParameterTest().increase(in);
        Assert.assertEquals(out, rst);
    }
}