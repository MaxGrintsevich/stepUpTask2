package ru.stepup.task2;

class Fraction implements Fractionable{
    private int num;
    private int denum;

    @Override
    @Cache
    public double doubleValue() {
        System.out.println("invoke double value");
        return (double) num/denum;
    }

    @Override
    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }
}