public class Numbr {
    private boolean isDouble = false;
    private int intNumber = 0;
    private double doubleNumber = 0.0;
    private char operator;
    private boolean isOperator = false;

    public Numbr(double x){
        doubleNumber = x;
        isDouble = true;
    }

    public Numbr(int x){
        intNumber = x;
    }

    public Numbr(char x){
        operator = x;
        isOperator = true;
    }

    public boolean getDoubleBoolean(){
        return isDouble;
    }

    public int getIntNumber(){
        return intNumber;
    }

    public double getDoubleNumber(){
        return doubleNumber;
    }

    public Character getOperator(){ return operator; }

    public boolean getOperatorBoolean(){ return isOperator; }

}
