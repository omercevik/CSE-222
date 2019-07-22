import java.io.*;
import java.util.StringTokenizer;

/**
 * CSE 222 Data Structures and Algorithms
 * Homework 03
 * Part 2
 * 161044004
 * @author Omer Cevik
 */
public class MainPart2
{
    /**
     * Sinus function.
     * @param x Calculate sin x.
     * @return Returns sin x.
     */
    private static double sin(double x)
    {
        x = x % (2 * 3.14159265359);

        double term = 1.0;
        double sum  = 0.0;

        for (int i = 1; term != 0.0; ++i)
        {
            term *= (x / i);

            if (i % 4 == 1)
            {
                sum += term;
            }
            if (i % 4 == 3)
            {
                sum -= term;
            }
        }
        return sum;
    }

    /**
     * Abs function.
     * @param x Calculate abs x.
     * @return Returns abs x.
     */
    private static double abs(double x)
    {
        return x < 0f ? -x : x;
    }

    /**
     * Cos function.
     * @param x Calculate cos x.
     * @return Returns cos x.
     */
    private static double cos(double x)
    {
        x = x % (2 * 3.14159265359);

        double term = 1.0;
        double sum  = 1.0;

        for (int i = 1; term != 0.0; i++)
        {
            term *= (x / i);

            if (i % 4 == 0)
            {
                sum += term;
            }
            if (i % 4 == 2)
            {
                sum -= term;
            }
        }
        return sum;
    }

    /**
     *
     * @param Op Checks if char is a operator.
     * @return Returns true if operator.
     */
    private static boolean isOperator(char Op)
    {
        return Op == '+' || Op == '-' || Op == '*' || Op == '/' || Op == '(' || Op == ')';
    }

    /**
     *
     * @param Space Checks if there is a space.
     * @return Returns true if space.
     */
    private static boolean isSpace(char Space)
    {
        return Space == ' ';
    }

    /**
     *
     * @param InfixPoly Input the poly infix mode.
     * @return Returns postfix mode.
     */
    private static String InfixToPostfix(String InfixPoly)
    {
        Stack<String> OperatorsStack = new Stack();
        StringTokenizer InfixTokens = new StringTokenizer(InfixPoly,"+-*/() ",true);
        StringBuffer PostfixString = new StringBuffer(InfixPoly.length());

        while (InfixTokens.hasMoreTokens())
        {
            String token = InfixTokens.nextToken();
            char tokenChar = token.charAt(0);
            if ( ((token.length() == 1) && isOperator(tokenChar)) ||
                    ((token.equals("cos") || token.equals("sin") || token.equals("abs"))))
            {
                while (!OperatorsStack.isEmpty() && !PrecedenceCheck(OperatorsStack.peek().charAt(0), tokenChar))
                {
                    PostfixString.append(" ").append(OperatorsStack.pop());
                }

                if ( tokenChar == ')')
                {
                    String OperatorString = "";
                    OperatorString += OperatorsStack.pop();
                    while (OperatorString.charAt(0)!='(')
                    {
                        PostfixString.append(" ").append(OperatorString);
                        OperatorString = "";
                        OperatorString += OperatorsStack.pop();
                    }
                }
                else
                {
                    OperatorsStack.push(token);
                }
            }
            else if ( (token.length() == 1) && isSpace(tokenChar) ) {}
            else {
                PostfixString.append(" ").append(token);
            }
        }
        while (!OperatorsStack.isEmpty())
        {
            PostfixString.append(" ").append(OperatorsStack.pop());
        }

        return PostfixString.toString();
    }

    /**
     *
     * @param FirstOp Precedence for first operand.
     * @param SecondOp Precedence for second operand.
     * @return Returns precedence of operands.
     */
    private static boolean PrecedenceCheck(char FirstOp, char SecondOp)
    {
        switch (FirstOp)
        {
            case '+':
            case '-': return !(SecondOp == '+' || SecondOp == '-') ;

            case '*':
            case '/': return SecondOp == '(';

            case '(': return true;

            default: return false;
        }
    }

    /**
     *
     * @param PostfixExpression Gets postfix expression.
     * @return Returns result of expression.
     */
    public static double CalculateExpression(String PostfixExpression)
    {
        Stack<Double> EvaluteStack = new Stack();
        double FirstOperand, SecondOperand, Result;
        StringTokenizer PostfixTokenizer = new StringTokenizer(PostfixExpression);

        while (PostfixTokenizer.hasMoreTokens())
        {
            String token = PostfixTokenizer.nextToken();
            char tokenChar = token.charAt(0);
            if (isOperator(tokenChar))
            {
                SecondOperand = EvaluteStack.pop();
                FirstOperand = EvaluteStack.pop();
                Result = CalculateTheOperation(token.charAt(0), FirstOperand, SecondOperand);
                EvaluteStack.push(Result);
            }
            else if (token.equals("sin"))
            {
                token = PostfixTokenizer.nextToken();
                FirstOperand = Double.parseDouble(token);
                Result = sin(FirstOperand);
                EvaluteStack.push(Result);
            }
            else if (token.equals("cos"))
            {
                token = PostfixTokenizer.nextToken();
                FirstOperand = Double.parseDouble(token);
                Result = cos(FirstOperand);
                EvaluteStack.push(Result);
            }
            else if (token.equals("abs"))
            {
                token = PostfixTokenizer.nextToken();
                FirstOperand = Double.parseDouble(token);
                Result = abs(FirstOperand);
                EvaluteStack.push(Result);
            }
            else
            {
                EvaluteStack.push(Double.parseDouble(token));
            }
        }

        Result = EvaluteStack.pop();
        return Result;
    }

    /**
     *
     * @param Op Operation character.
     * @param FirstOperand Left operand.
     * @param SecondOperand Right operand.
     * @return Returns result of operation.
     */
    private static double CalculateTheOperation(char Op, double FirstOperand, double SecondOperand)
    {
        double Result = 0f;

        switch (Op)
        {
            case '+' :
                Result = FirstOperand + SecondOperand;
                break;
            case '-' :
                Result = FirstOperand - SecondOperand;
                break;
            case '*' :
                Result = FirstOperand * SecondOperand;
                break;
            case '/' :
                Result = FirstOperand / SecondOperand;
        }

        return Result;
    }

    /**
     * Main function of Part 2.
     * @param args Not used.
     * @throws Exception About file.
     */
    public static void main(String[] args) throws Exception
    {
        String InputLine;
        Stack<String> InputLineStack = new Stack();
        BufferedReader buffer = new BufferedReader(new FileReader("test_file_part2.txt"));

        while ((InputLine = buffer.readLine()) != null)
        {
            System.out.println(InputLine);
            InputLineStack.push(InputLine);
        }
        buffer.close();

        String Expression = InputLineStack.pop();
        InputLineStack.pop();

        while(!InputLineStack.isEmpty())
        {
            StringTokenizer inputs = new StringTokenizer(InputLineStack.peek(),"=");
            String variable = inputs.nextToken();
            String literal = inputs.nextToken();
            Expression = Expression.replace(variable, literal);
            InputLineStack.pop();
        }

        System.out.println(Expression);
        System.out.println(InfixToPostfix(Expression));
        System.out.println(CalculateExpression(InfixToPostfix(Expression)));
    }
}