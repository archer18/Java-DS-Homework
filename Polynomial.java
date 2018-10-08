import java.util.*;

public class Polynomial{

    private double[] coefficients;
    private int highestPower;

    public Polynomial(){
        this.coefficients= new double[25];
        this.coefficients[0]=0;
        this.highestPower= 0;
    }

    public Polynomial(double a0){
        this.coefficients= new double[25];
        this.coefficients[0]=a0;
        this.highestPower= 0;
    }

    public Polynomial(Polynomial P){
        this.coefficients=P.coefficients.clone();
        this.highestPower=P.highestPower;
    }

    public void add_to_coef(double amount, int exponent){
        if(exponent>this.highestPower){
            this.highestPower=exponent;
        }
        if(exponent>this.coefficients.length){
            this.coefficients = Arrays.copyOf(this.coefficients,exponent+20);
        }
        this.coefficients[exponent] = ( amount+this.coefficients[exponent] );
    }

    public void assign_coef(double coef, int exponent){
        if(exponent>this.highestPower){
            this.highestPower=exponent;
        }
        if(exponent>this.coefficients.length){
            this.coefficients = Arrays.copyOf(this.coefficients,exponent+20);
        }
        this.coefficients[exponent]=coef;
    }

    public double coefficient(int atExponent){
        return this.coefficients[atExponent];
    }

    public double eval(double x){
        double ans=0.0, pow=1.0;
        for(int i=this.highestPower;i>=0;i--){
            ans=ans+(this.coefficients[i]*Math.pow(x,i));
        }
        return ans;
    }

    public Polynomial add(Polynomial P){
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

    public Polynomial multiply(Polynomial P){

        Polynomial ans = new Polynomial();
        ans.coefficients = new double[this.highestPower+P.highestPower+10]; // max exponents + 10 for array size
        ans.highestPower=(this.highestPower+P.highestPower);

        for(int i=this.highestPower; i>=0; i--){
            for(int j=P.highestPower; j>=0; j--){
                ans.coefficients[i+j]=ans.coefficients[i+j]+(this.coefficients[i]*P.coefficients[j]);
            }
        }
        return ans;
    }

    public String toString(){

        String ans = new String();

        if(this.highestPower==0 && this.coefficients[0]==0){   // if 0 is the highest power and the coefficient is 0, print only 0
            ans += "0";

        }else{   // otherwise, print the whole polynomial

            // Block for printing polynomial power^n to power^2
            for (int i = this.highestPower; i > 1; i--) {   // loops through array *starting at the highest power*

                if (this.coefficients[i] == 1.0){   // if coefficient is 1.0...
                    ans += "x^" + i;   // does not print a 1.0 in front of "x^a" to eliminate redundancy

                }else if(this.coefficients[i] == -1.0){   // if coefficient is -1.0...

                    if(this.coefficients[i]==this.coefficients[this.highestPower]){   // if this is the first coefficient...
                        ans += "-";   // prints negative sign for first value only
                    }
                    ans += "x^" + i;   // prints only "x^a" to eliminate redundancy

                }else if(this.coefficients[i] > 0) {
                    ans += this.coefficients[i] + "x^" + i;   // prints number if it's positive and not 1.0

                }else if(this.coefficients[i] < 0) {

                    if(this.coefficients[i]==this.coefficients[this.highestPower]){   // if this is the first coefficient...
                        ans += "-";  // print negative sign for first value only
                    }
                    ans += -this.coefficients[i] + "x^" + i;   // prints number * -1 for nice printing later
                }

                if (this.coefficients[i - 1] > 0) {   // if proceeding number is greater than 0...
                    ans += " + ";   // print pos+ sign

                }else if( this.coefficients[i-1] < 0){   // if proceeding number is less than 0...
                    ans +=  " - ";   // print neg- sign
                }
            }

            // print block for coefficient with power^1
            if(this.coefficients[1] == 1.0){
                ans+= "x";

            }else if(this.coefficients[1] == -1.0){
                ans+= "x";

            }else if(this.coefficients[1]>0 && this.coefficients[1]!=1.0){
                ans+= this.coefficients[1]+"x";

            }else if(this.coefficients[1]<0 && this.coefficients[1]!=-1.0){
                ans+= -this.coefficients[1]+"x";
            }

            // Print block for coefficient with power^0
            if(this.coefficients[0]<0){
                if(ans.isEmpty()){
                    ans+=this.coefficients[0];
                }else{
                    ans+= " - "+ -this.coefficients[0];
                }

            }else if(this.coefficients[0]>0){
                if(ans.isEmpty()){
                    ans+=this.coefficients[0];
                }else{
                    ans+= " + "+this.coefficients[0];
                }
            }

        }
        return ans;
    }
}
