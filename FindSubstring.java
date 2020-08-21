import java.util.Scanner;

public class FindSubstring 
{

    public static void main(String[] args) 
    {
        String mainString;
        String substring;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the original string, followed by the substring you are searching for");
        mainString = in.next();
        substring = in.next();
        int finalCount = numberOfOccurences(mainString, substring);
        System.out.println("The number of times your substring appears is: " + finalCount);
        in.close();
    }

    public static int numberOfOccurences(String mainString , String substring)
    {
        int counter1 = 0, counter2 = 0;
        for (int i = 0; i < mainString.length(); i++) 
        {
            for (int j = 0; j < substring.length(); j++) 
            {
                if (mainString.charAt(i) == substring.charAt(j)) 
                {
                    counter1++;
                    break;
                } 
                else 
                {
                    if (j == substring.length() - 1) 
                    {
                        counter1 = 0;
                        break;
                    } 
                    else 
                    {
                        continue;
                    }
                }
            }
            if (counter1 == substring.length()) 
            {
                counter1 = 0;
                counter2++;
            }
        }
        return counter2;
    }

}