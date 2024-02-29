package ru.stepup.task2;

public class Start {

    public static void main(String[] args) throws NoSuchMethodException {
        Fractionable f = new Fraction();
        f.setDenum(2);
        f.setNum(1);
        Fractionable ff = Cacher.getCachedInstance(f);
        System.out.println(ff.doubleValue());
        System.out.println(ff.doubleValue());
        System.out.println(ff.doubleValue());
        ff.setDenum(23);
        System.out.println(ff.doubleValue());
        System.out.println(ff.doubleValue());

    }
}





