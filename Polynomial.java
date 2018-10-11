/*
Alexander Archer
CS275 Data Structures
Homework 2 - Polynomials
10/10/2018
 */

import java.util.*;

public class Polynomial{

    private double[] coefficients;   // the array where coefficients are stored
    private int highestPower;   // variable that keeps track of the highest power within a polynomial

    // creates new polynomial with array size of 25, no coefficients stored, and highest power of 0
    public Polynomial(){
        this.coefficients= new double[25];
        this.coefficients[0]=0;
        this.highestPower= 0;
    }

    // creates new polynomial w/ array size 25, coefficient of a0 at x^0
    public Polynomial(double a0){
        this.coefficients= new double[25];
        this.coefficients[0]=a0;
        this.highestPower= 0;
    }

    // creates new polynomial based on a clone of the passed obj
    public Polynomial(Polynomial P){
        this.coefficients= Arrays.copyOf(P.coefficients,P.highestPower+10);
        this.highestPower=P.highestPower;
    }

    // adds a number to a specified coefficient
    public void add_to_coef(double amount, int exponent){

        // if the exponent passed is greater than the calling exponent, then raise exponent
        if(exponent>this.highestPower){
            this.highestPower=exponent;
        }

        // if the exponent would exceed the array length, create new larger copied array
        if(exponent>this.coefficients.length){
            this.coefficients = Arrays.copyOf(this.coefficients,exponent+20);
        }
        // adds coefficient of passed amount and number in array at specified exponent
        this.coefficients[exponent] = ( amount+this.coefficients[exponent] );
    }

    // assigns new value to specified coefficient
    public void assign_coef(double coef, int exponent){

        // if the exponent passed is greater than the calling exponent, then raise exponent
        if(exponent>this.highestPower){
            this.highestPower=exponent;
        }

        // if the exponent would exceed the array length, create new larger copied array
        if(exponent>this.coefficients.length){
            this.coefficients = Arrays.copyOf(this.coefficients,exponent+20);
        }
        // assigns value to specified coefficient
        this.coefficients[exponent]=coef;
    }

    // returns coefficient value at specified exponent
    public double coefficient(int atExponent){
        if(atExponent>this.highestPower){
            return 0.0;
        }else{
            return this.coefficients[atExponent];
        }
    }

    // evaluates the polynomial when x = x passed value.
    public double eval(double x){
        double ans = 0.0, power = 1.0;

        // adds first term x^0 to ans
        ans+=this.coefficients[0];

        // loops to add (coefficient*x^power)
        for(int i=1; i <= this.highestPower; i++){
            power=power*x;
            ans+=(this.coefficients[i]*power);
        }
        return ans;
    }

    // adds two polynomials together
    public Polynomial add(Polynomial P){

        // checks to see which polynomial has the greatest power to avoid array out of bounds while adding
        if(P.highestPower>this.highestPower){
            Polynomial ans = new Polynomial(P);
            for(int i=this.highestPower; i>=0; i--){
                ans.coefficients[i]=this.coefficients[i]+P.coefficients[i];
            }
            return ans;

        }else{
            Polynomial ans = new Polynomial(this);
            for(int i=P.highestPower;i>=0;i--){
                ans.coefficients[i]+=P.coefficients[i];
            }
            return ans;
        }
    }

    // multiplies two polynomials together
    public Polynomial multiply(Polynomial P){

        // if at least one of the polynomials being multiplied has one term and it's zero
        if( (P.highestPower==0 && P.coefficients[0] ==0) ||
                (this.highestPower==0 && this.coefficients[0]==0)){
            Polynomial ans = new Polynomial();
            return ans;

        // if not, multiply as usual
        }else{
            Polynomial ans = new Polynomial();
            ans.coefficients = new double[this.highestPower+P.highestPower+10];
            ans.highestPower=(this.highestPower+P.highestPower);

            for (int i = this.highestPower; i >= 0; i--) {
                for (int j = P.highestPower; j >= 0; j--) {
                    ans.coefficients[i + j] = ans.coefficients[i + j] + (this.coefficients[i] * P.coefficients[j]);
                }
            }
            return ans;
        }
    }

    // creates more realistic representation of a polynomial when written
    // eliminates redundant information. Example: 1*x is understood as simply x
    public String toString(){

        String ans = new String();

        // if 0 is the highest power and the coefficient is 0, print only 0
        if(this.highestPower==0 && this.coefficients[0]==0){
            ans += "0";

        // otherwise, print the whole polynomial
        }else{

            // Block for printing polynomial power^n to power^2
            for (int i = this.highestPower; i > 1; i--) {   // loops through array *starting at the highest power*

                // if coefficient is 1.0...
                if (this.coefficients[i] == 1.0) {
                    ans += "x^" + i;   // does not print a 1.0 in front of "x^a" to eliminate redundancy

                    // if coefficient is -1.0...
                } else if (this.coefficients[i] == -1.0) {

                    // if this is the first coefficient...
                    if (this.coefficients[i] == this.coefficients[this.highestPower]) {
                        ans += "-";   // prints negative sign for first value only
                    }
                    ans += "x^" + i;   // prints only "x^a" to eliminate redundancy

                    // if coefficient is positive and not 1.0
                } else if (this.coefficients[i] > 0) {

                    // if coefficient double can be represented by an int
                    if ((this.coefficients[i] == Math.floor(this.coefficients[i])) && !Double.isInfinite(this.coefficients[i])) {
                        int num = (int) this.coefficients[i];
                        ans += num + "x^" + i;
                    } else {
                        ans += this.coefficients[i] + "x^" + i;   // prints number if it's positive and not 1.0
                    }

                    // if coefficient is negative and not -1.0
                } else if (this.coefficients[i] < 0) {

                    // if this is the first coefficient
                    if (this.coefficients[i] == this.coefficients[this.highestPower]) {
                        ans += "-";  // print negative sign for first value only
                    }

                    // if coefficient double can be represented by an int
                    if ((-this.coefficients[i] == Math.floor(-this.coefficients[i])) && !Double.isInfinite(-this.coefficients[i])) {
                        int num = (int) -this.coefficients[i];
                        ans += num + "x^" + i;
                    } else {
                        ans += -this.coefficients[i] + "x^" + i;   // prints number * -1 for nice printing later
                    }
                }

                // if subsequent number is not 0, print the correct sign for following term
                if(this.coefficients[i - 1] > 0) {
                   ans += " + ";   // print pos+ sign

                }else if( this.coefficients[i-1] < 0){
                   ans +=  " - ";   // print neg- sign
                }
            }


            // print block for coefficient with power^1
            if(this.coefficients[1] == 1.0 || this.coefficients[1] == -1.0){
                ans+= "x";

            // if coefficient is positive and not 1
            }else if(this.coefficients[1]>0){
                // if coefficient double can be represented by an int
                if((this.coefficients[1] == Math.floor(this.coefficients[1])) && !Double.isInfinite(this.coefficients[1])){
                    int num = (int)this.coefficients[1];
                    ans += num + "x";
                }else{
                    ans += this.coefficients[1] + "x";
                }

            // if coefficient is positive and not 1
            }else if(this.coefficients[1]<0){
                // if coefficient double can be represented by an int
                if((-this.coefficients[1] == Math.floor(-this.coefficients[1])) && !Double.isInfinite(-this.coefficients[1])){
                    int num = (int)-this.coefficients[1];
                    ans += num + "x";
                }else{
                    ans += -this.coefficients[1] + "x";
                }
            }


            // Print block for coefficient with power^0
            // if coefficient is negative
            if(this.coefficients[0]<0){
                if((-this.coefficients[0] == Math.floor(-this.coefficients[0])) && !Double.isInfinite(-this.coefficients[0])){
                    if(ans.isEmpty()){
                        int num = (int)this.coefficients[0];
                        ans += num;
                    }else{
                        int num = (int)this.coefficients[0];
                        ans += " - " + -num;
                    }

                }else{
                    if(ans.isEmpty()){
                        ans += this.coefficients[0];
                    }else{
                        ans += " - " + this.coefficients[0];
                    }
                }

            // if coefficient is positive
            }else if(this.coefficients[0]>0){
                if((this.coefficients[0] == Math.floor(this.coefficients[0])) && !Double.isInfinite(this.coefficients[0])){
                    int num = (int)this.coefficients[0];
                        if(ans.isEmpty()){
                            ans += num;
                        }else{
                            ans += " + " + num;
                        }
                }else{
                    if(ans.isEmpty()){
                        ans += this.coefficients[0];
                    }else{
                        ans += " + " + this.coefficients[0];
                    }
                }
            }
        }
        return ans;
    }
}
