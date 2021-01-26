import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReversePolish
{
    public static void main(String[] args)
    {  
        Scanner scan;
        try
        {
            scan = new Scanner(new File("in.txt")); //Call in.txt file
            int len = scan.nextInt(); //Call first number of lines to be scanned from in.txt
            scan.nextLine(); //Start scan cursor at new line
            //Go through every line and solves equation
            for(int i = 0; i < len; i++)
            {
                String str = scan.nextLine();
                System.out.println(i + 1 + ". " + eval(str)); //Print out answer
            }
        }
        //Run if there is no such "in.txt" file
        catch (FileNotFoundException e)
        {
            System.out.println("in.txt not found, using console input...");
            scan = new Scanner(System.in); //Reads scanner console input
            System.out.println(eval(scan.nextLine()));
        }
    }

    public static double eval(String s) throws ArithmeticException
    {
        //Split String s into array of numbers and operations, assuming each element is separated by space
        String[] elements = s.split(" ");
        ArrayStack<Double> a = new ArrayStack<Double>(); //Create new ArrayStack to hold numbers
        double n1, n2; //Used to record top 2 numbers in stack
        for(int i = 0; i < elements.length; i++)
        {
            try
            {
                //Push an element (assuming it is a number) into stack of numbers
                a.push(Double.parseDouble(elements[i]));
            }
            //Current element is not a number, meaning it is an operator
            catch(Exception E)
            {
                //Record top 2 numbers and remove them from stack
                n1 = a.pop();
                n2 = a.pop();
                switch(elements[i])
                {
                    case "+":
                        a.push(n1 + n2); //Push sum
                        break;
                    case "-":
                        a.push(n2 - n1); //Push difference
                        break;
                    case "*":
                        a.push(n1 * n2); //Push product
                        break;
                    case "/":
                        if(n1 == 0) //Throw Divide by 0 error if divisor is 0
                        {
                            throw new ArithmeticException("Divide by 0 Error at Problem " + i);
                        }
                        a.push(n2 / n1); //Push quotient
                        break;
                    case "^":
                        a.push(Math.pow(n2, n1)); //Push exponent solution
                        break;
                    case "%":
                        if(n1 == 0) //Throw Divide by 0 error if divisor is 0
                        {
                            throw new ArithmeticException("Divide by 0 Error at Problem " + i);
                        }
                        a.push(n2 % n1); //Push modulo solution
                        break;
                }
            }
        }
        return a.peek(); //Return only remaining number in stack, the solution
    }
}