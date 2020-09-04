import java.util.*;

public class quest4 
{
    public static void main(String[] args)
    {
        int i = 1;
        int x = 0;
        int sum = 0;
        int maximum = Integer.MAX_VALUE;
        while (sum <= maximum)
        {
            x = i*i;
            sum = sum + x;
            i = i+1;
        }
        System.out.println("The maximum value is: "+ sum);
        System.out.println("The max value of i which gives this sum is: " + i);
    }
    
}
