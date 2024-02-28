package com.example.demo.objectInit;

public class Derived //extends Base
{
    public String whenAmISet = "set when declared";

    public Derived() {
        whenAmISet = "set when constructed";
    }

    //@Override
    void preProcess()
    {
        whenAmISet = "set in preProcess()";
    }
}
