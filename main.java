/*
Alexander Archer
CS275 Data Structures
Homework 2 - Polynomials
10/10/2018
 */


public class main {
    public static void main(String args[]){
        Polynomial fX = new Polynomial();
        fX.assign_coef(-5,0);

        Polynomial gX = new Polynomial(10);
        gX.add_to_coef(-5.25,1);
        gX.add_to_coef(-1,3);

        Polynomial hX = new Polynomial(fX);
        hX.add_to_coef(-6.5,3);
        hX.assign_coef(-4,5);
        hX.add_to_coef(7,1);

        Polynomial wX = new Polynomial();

        System.out.println("Final Functions to be tested:");
        System.out.println("f(x) = "+fX);
        System.out.println("g(x) = "+gX);
        System.out.println("h(x) = "+hX);
        System.out.println("w(x) = "+wX);
        System.out.println();

        System.out.println("Multiplying Polynomials:");
        System.out.println("f(x)*g(x) = "+fX.multiply(gX));
        System.out.println("h(x)*g(x) = "+hX.multiply(gX));
        System.out.println("f(x)*h(x) = "+fX.multiply(hX));
        System.out.println("w(x)*h(x) = "+hX.multiply(wX));
        System.out.println();

        System.out.println("Adding Polynomials:");
        System.out.println("f(x)+g(x) = "+fX.add(gX));
        System.out.println("f(x)+h(x) = "+fX.add(hX));
        System.out.println("g(x)+h(x) = "+gX.add(hX));
        System.out.println("w(x)+g(x) = "+wX.add(gX));
        System.out.println();

        System.out.println("Evaluation at x:");
        System.out.println("f(2.5) = "+fX.eval(2.5));
        System.out.println("g(5) = "+gX.eval(5.0));
        System.out.println("h(-2) = "+hX.eval(-2));
        System.out.println("w(10) = "+wX.eval(10));
        System.out.println();

        System.out.println("Coefficients of functions:");
        System.out.println("Coefficient of f(x) at x^1 = "+fX.coefficient(1));
        System.out.println("Coefficient of g(x) at x^2 = "+gX.coefficient(1));
        System.out.println("Coefficient of h(x) at x^5 = "+hX.coefficient(5));
        System.out.println("Coefficient of w(x) at x^0 = "+hX.coefficient(0));
    }
}
