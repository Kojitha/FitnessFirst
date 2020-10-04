package com.example.tdumy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {

    @Test
    public void test(){
        String actualValue = ModelWater.covertToLiters("1000");

        double actual = Double.parseDouble(actualValue);

        assertEquals(1,actual,1e-15);
    }


}
