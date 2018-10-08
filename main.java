public class main {
    public static void main(String args[]){
        Polynomial fX = new Polynomial();
        fX.assign_coef(5,1);
        //fX.assign_coef(2,2);
        //fX.assign_coef(3,3);
        System.out.println("f(x) = "+fX);
        //System.out.print("f(x) + (6x^2)");
        //fX.add_to_coef(6,2);
        //System.out.println(" = "+fX);
        System.out.println();

        Polynomial gX = new Polynomial(10);
        System.out.println("g(x) = "+gX);
        System.out.print("g(x) + (-2x^3-5x) = ");
        gX.add_to_coef(-5,1);
        gX.add_to_coef(-2,3);
        System.out.println(gX);
        System.out.println();

        Polynomial hX = new Polynomial(fX);
        System.out.println("h(x) = f(x)");
        System.out.println("h(x) = "+hX);
        System.out.print("h(x) - 6x^3 = ");
        hX.add_to_coef(-6,3);
        System.out.println(hX);
        System.out.println("Assign ax^5, a = -4");
        hX.assign_coef(-4,5);
        System.out.println("h(x) = "+hX);
        System.out.println();

        System.out.println("Final Functions:");
        System.out.println("f(x) = "+fX);
        System.out.println("g(x) = "+gX);
        System.out.println("h(x) = "+hX);
        System.out.println();

        System.out.println("Multiplying Polynomials:");
        System.out.println("f(x)*g(x) = "+fX.multiply(gX));
        System.out.println("h(x)*g(x) = "+hX.multiply(gX));
        System.out.println("f(x)*h(x) = "+fX.multiply(hX));
        System.out.println();

        System.out.println("Adding Polynomials:");
        System.out.println("f(x)+g(x) = "+fX.add(gX));
        System.out.println("f(x)+h(x) = "+fX.add(hX));
        System.out.println("g(x)+h(x) = "+gX.add(hX));
        System.out.println();

        System.out.println("Evaluation at x:");
        System.out.println("f(2) = "+fX.eval(2.0));
        System.out.println("g(5) = "+gX.eval(5.0));
        System.out.println("h(-2) = "+hX.eval(-2));
        System.out.println();

        System.out.println("Printing coefficients of functions");
        System.out.println("Coefficient of f(x) at x^3 = "+fX.coefficient(1));
        System.out.println("Coefficient of g(x) at x^2 = "+gX.coefficient(3));
        System.out.println("Coefficient of h(x) at x^0 = "+hX.coefficient(5));


    }
}
