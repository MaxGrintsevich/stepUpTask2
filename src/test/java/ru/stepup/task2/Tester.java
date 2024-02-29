package ru.stepup.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class Tester {

    interface Testable{
        double makePower();
        void setBase(int base);
        void setPower(int power);
        int getCounter();
    }

    class Power implements Testable{
        int base =0;
        int power=0;
        int counter = 0;
        public Power(int base, int power){
            this.base = base;
            this.power = power;
        }
        @Override
        @Cache
        public double makePower() {
            counter ++;
            return Math.pow(base, power);
        }

        @Override
        @Mutator
        public void setBase(int base) {
             this.base = base;
        }

        @Override
        @Mutator
        public void setPower(int power) {
            this.power = power;
        }
        public int getCounter(){
            return counter;
        }
    }

    @Test
    public void testSameResults(){
        Testable x = new Power(2, 3);
        Testable cachedX = Cacher.getCachedInstance(x);
        Random r = new Random();
        for (int i=0;i<1000;i++){
            cachedX.setBase(r.nextInt(100));
            cachedX.setPower(r.nextInt(100));
            Assertions.assertEquals(x.makePower(), cachedX.makePower());
        }

    }

    @Test
    public void testCache(){
        Testable x = new Power(2, 3);
        Testable cachedX = Cacher.getCachedInstance(x);
        double tmp = cachedX.makePower();
        // смотрим что кэш сработал и счетчик сработал не увеличился
        int counter = cachedX.getCounter();
        tmp = cachedX.makePower();
        Assertions.assertEquals(counter, cachedX.getCounter());

    }

    @Test
    public void testCacheClear(){
        Testable x = new Power(2, 3);
        Testable cachedX = Cacher.getCachedInstance(x);
        double tmp = cachedX.makePower();
        int counter = cachedX.getCounter();

        // смотрим что кэш сбросился и счетчик увеличился
        cachedX.setBase(1);
        tmp = cachedX.makePower();
        Assertions.assertEquals(++counter, cachedX.getCounter());

        // смотрим что кэш сбросился и счетчик увеличился
        cachedX.setPower(1);
        tmp = cachedX.makePower();
        Assertions.assertEquals(++counter, cachedX.getCounter());

    }



}
