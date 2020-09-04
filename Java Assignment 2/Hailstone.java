import java.util.Scanner;

public class Hailstone
{
    public static void main(String[] args) 
    {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the number whos sequence you would like to find");
    try 
    {
    int number = input.nextInt();
        if (number <= 0) 
        {
            System.out.println("The number entered should be greater than or equal to 1");
        } 
        else 
        {
            System.out.println("Hailstone Sequence of " + number + " is");
            while (number != 1) 
            {
                if (number % 2 == 0) 
                {
                    number = number / 2;
                } 
                else 
                {
                    number = (number * 3) + 1;        
                }
                System.out.println(number);
            }
        }
    } 
    catch (Exception e) 
    {
        System.out.println("Value entered should be an integer greater than or equal to 0");
    }
    input.close();
    }
}