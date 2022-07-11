package com.polarisdigitech.backendchallenge.algorithms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CuttingRodProblem {
    static int getValue(int [] values, int length){
        log.info("Current length =="+length);
        if(length <= 0)//recursive part
            return 0;
        int tempMax = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++){
            log.info("Current i =="+i);
            tempMax = Math.max(tempMax, values[i] + getValue(values,length-i-1));
            log.info("Current TempMax =="+tempMax);
        }
        return tempMax;
    }
    public void testCuttingRodDpProblem(){
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;
        log.info("Max Rod Value =="+getValue(values,values.length));
        String concat = "me".concat("2u");
        if(2 < 5 && 3>2)
            System.out.println("Good oo!");
        concat.equals("2u");
        "".length();
        System.out.println("conCat result =="+concat);
        log.info("IndexOf case-nsensitive  'my'== "+"My oh my!".indexOf("my"));
    }

}
