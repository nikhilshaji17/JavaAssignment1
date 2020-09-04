import java.util.*;

public class lexicographicStrings
{
    public static void main(String[] args)
    {   int i = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first string: ");
        String s1 = input.nextLine();
        System.out.println("Enter the second string: ");
        String s2 = input.nextLine();
        for(i=0; i < s1.length() && i < s2.length(); i++ )
        {
            if ((int)s1.charAt(i) == (int)s2.charAt(i))
            {
                continue;
            }
            else
            {
                int x = (int)s1.charAt(i) - (int)s2.charAt(i);
                System.out.print("They are not equal, the differences is: ");
                System.out.print(x);
                System.exit(0);
            }
        }        
            
        if (s1.length()!=s2.length())
        {
                int y = s1.length() - s2.length();
                System.out.print("They are not equal, the difference is: ");
                System.out.print(y);
        }
        else
        {
                int z = 0;
                System.out.print("These strings are equal, difference is: ");
                System.out.print(z);
        }
    }
}