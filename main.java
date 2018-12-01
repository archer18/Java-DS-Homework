import java.util.*;

public class main {
    public static void main(String args[]){

        // TEST CASE HERE
        String expression = "  5*  (9 -12    )/ 2";


        System.out.println("Original Expression: "+expression);

        if( areParenthesesBalanced( expression.toCharArray() )) {

        LinkedList<Numbr> objectExpression = stringExpressionToLinkedListExpression(expression);
        StringBuilder postFixedExpressionStringBuilder = infixToPostfixConversion( objectExpression );
        String postFixedExpressionString = postFixedExpressionStringBuilder.toString();

        System.out.println("Infix Expression formatted: " + printList( objectExpression ));

        System.out.println("Postfix Expression: " + postFixedExpressionString );

        //System.out.println("Evaluation of Expression = " + evaluateExpression( objectExpression ));

        }else{
            // if parentheses are wrong, print the following error
            System.out.println("Expression parentheses are not balanced");
        }
    }

    public static LinkedList<Numbr> stringExpressionToLinkedListExpression(String expression){

        // needs end parentheses to function
        expression="("+expression+")";

        // run through given expression String and mark positions of numbers, operators
        Character temp;
        LinkedList<Integer> positions = new LinkedList<>();
        for(int i=0; i<expression.length(); i++){
            // set temp to current character in String
            temp=expression.charAt(i);

            // if a number is found
            if(Character.isDigit(temp)){
                positions.addLast(i);

                // is a decimal is found
            }else if(temp.equals('.')){
                positions.addLast(-1);

                // if an operator is found
            }else if(precedence(temp)>=1 || temp.equals('(') || temp.equals(')')){
                // -1000 to calc position later, used to differentiate from operands
                positions.addLast(i-1000);
            }
        }

        // prepare items for next loop
        double tempDbl;
        int tempInt;
        Integer tempInteger;
        String tempString;
        LinkedList<Integer> startStop = new LinkedList<>();
        LinkedList<Numbr> listOfNumbers = new LinkedList<>();
        ListIterator<Integer> positionIterator = positions.listIterator(0);

        // run through positions list
        while(positionIterator.hasNext()){
            tempInteger = positionIterator.next();

            // if a number or decimal is found in the current position in list
            if(tempInteger >= -1){

                // add the current position to the startStop list for parsing later
                startStop.addLast(tempInteger);

                // if an operator is encountered (x < -1), parse current position range
            }else{

                // make sure startStart has items before continuing
                if(!startStop.isEmpty()){

                    // if a -1 is found within startStop, then it is a double
                    if (startStop.contains(-1)) {

                        //if the startStop list is only 1 item in length, then parse 1 character
                        if (startStop.size() == 1) {
                            // temporarily store subString extracted from expression String
                            tempString = expression.substring(startStop.peekFirst(), startStop.peekFirst() + 1);
                            // temporarily store the double parsed from the temporary String
                            tempDbl = Double.parseDouble(tempString);
                            // add number to the end new list
                            listOfNumbers.addLast(new Numbr(tempDbl));

                            // else, the startStop list is contains more than 1 item, parse from range specified
                        } else {
                            // similar logic as previous segments
                            tempString = expression.substring(startStop.peekFirst(), startStop.peekLast() + 1);
                            tempDbl = Double.parseDouble(tempString);
                            listOfNumbers.addLast(new Numbr(tempDbl));
                        }

                    // else it is a integer
                    }else{

                        //if the startStop list is only 1 item in length, then parse 1 character
                        if (startStop.size() == 1) {
                            // similar logic as previous segments
                            tempString = expression.substring(startStop.peekFirst(), startStop.peekFirst()+1);
                            tempInt = Integer.parseInt(tempString);
                            listOfNumbers.addLast(new Numbr(tempInt));

                            // else, the startStop list is contains more than 1 item, parse from range specified
                        } else {
                            // similar logic as previous segments
                            tempString = expression.substring(startStop.peekFirst(), startStop.peekLast()+1);
                            tempInt = Integer.parseInt(tempString);
                            listOfNumbers.addLast(new Numbr(tempInt));
                        }
                    }
                }

                // when the operator is encountered, add it after the numbers have been added to the new list
                listOfNumbers.addLast(new Numbr(expression.charAt(tempInteger+1000)));

                // clear out the startStop list once a number has been added to the list
                startStop.clear();
            }
        }
        return listOfNumbers;
    }

    public static StringBuilder infixToPostfixConversion(LinkedList<Numbr> listOfNumbers){

        Stack<Character> operators = new Stack<>();
        StringBuilder ans = new StringBuilder();
        Numbr tempNumber;
        Character temp;

        // create iterator to traverse linked list
        ListIterator listIterator = listOfNumbers.listIterator(0);
        // convert to POSTFIX logic WITH LINKED LIST
        while(listIterator.hasNext()){
            tempNumber = (Numbr) listIterator.next();

            if(tempNumber!=null){

                // if tempNumber contains a number
                if (!tempNumber.getOperatorBoolean()){

                    // if tempNumber has a double stored
                    if(tempNumber.getDoubleBoolean()){
                        ans.append(tempNumber.getDoubleNumber());
                        ans.append(" ");

                        // if tempNumber has an int stored
                    }else{
                        ans.append(tempNumber.getIntNumber());
                        ans.append(" ");
                    }

                    // If tempNumber has a '(' stored, push it to the stack.
                }else if (tempNumber.getOperator()=='(') {
                    operators.push(tempNumber.getOperator());

                    //  If tempNumber has a ')' stored, pop and output from the stack until an '(' is encountered.
                }else if (tempNumber.getOperator()==')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        temp=operators.pop();
                        ans.append(temp);
                        ans.append(" ");
                    }

                    if (!operators.isEmpty() && operators.peek() != '(') {
                        throw new IllegalArgumentException("Invalid Expression"); // invalid expression
                    } else {
                        operators.pop();
                    }

                    // an operator is encountered
                }else{
                    while (!operators.isEmpty() && precedence(tempNumber.getOperator()) <= precedence(operators.peek())) {
                        ans.append(operators.pop());
                        ans.append(" ");
                    }
                    operators.push(tempNumber.getOperator());
                }
            }
        }

        // pop all the operators from the stack
        while (!operators.isEmpty()) {
            ans.append(operators.pop());
            ans.append(" ");
        }

        return ans;
    }

    public static double evaluateExpression(LinkedList<Numbr> expression) {
        Stack<Double>  values =new Stack<>();

        Numbr tempNumbr;
        ListIterator<Numbr> expressionList = expression.listIterator(0);
        int counter = 0;
        while (expressionList.hasNext()) {
            System.out.println("loop "+counter);

            tempNumbr = expressionList.next();
            System.out.println("found next object @ "+tempNumbr);

            if( !tempNumbr.getOperatorBoolean() ){
                System.out.println("found number");
                if( tempNumbr.getDoubleBoolean() ){
                    values.push( tempNumbr.getDoubleNumber() );
                    System.out.println("pushed double");
                }else{
                    values.push( (double) tempNumbr.getIntNumber() );
                    System.out.println("pushed integer");
                }
            }else if(precedence(tempNumbr.getOperator())>0 || tempNumbr.getOperator()==')'){
                System.out.println("before evaluation of operator");
                values.push( operatorEvaluation( tempNumbr.getOperator(), values.pop(), values.pop() ));
            }
            counter++;
        }
        return values.pop();
    }

    public static StringBuilder printList(LinkedList<Numbr> objectExpression){

        StringBuilder ans = new StringBuilder();

        if(objectExpression.peekFirst().getOperator()=='(' && objectExpression.peekLast().getOperator()==')'){
            objectExpression.pollFirst();
            objectExpression.pollLast();
        }

        ListIterator printing = objectExpression.listIterator(0);
        Numbr temporary;

        while(printing.hasNext()){
            temporary = (Numbr) printing.next();
            if(temporary.getOperatorBoolean()){
                ans.append(temporary.getOperator());
                ans.append(" ");

            }else if(temporary.getDoubleBoolean()){
                ans.append(temporary.getDoubleNumber());
                ans.append(" ");

            }else{
                ans.append(temporary.getIntNumber());
                ans.append(" ");
            }
        }
        return ans;
    }

    private static int precedence(Character c){

        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static double operatorEvaluation(Character c, double a, double b){
        switch (c) {
            case '+':
                return (a+b);
            case '-':
                return (a-b);
            case '*':
                return (a*b);
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return (a/b);
            case '^':
                return (Math.pow(a,b));
            default:
                return 0;
        }
    }

    private static boolean areParenthesesBalanced(char exp[])
    {
        Stack<Character> st = new Stack<>();

        for(int i=0;i<exp.length;i++){

            if (exp[i] == '{' || exp[i] == '(' || exp[i] == '['){
                st.push(exp[i]);
            }

            if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']'){
                if (st.isEmpty()){
                    return false;
                }
                else if ( !isMatchingPair(st.pop(), exp[i]) ){
                    return false;
                }
            }
        }

       /* If there is something left in expression
          then there is a starting parenthesis without
          a closing parenthesis */

        if (st.isEmpty())
            return true; /*balanced*/
        else
        {   /*not balanced*/
            return false;
        }
    }

    private static boolean isMatchingPair(char character1, char character2)
    {
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }

}
