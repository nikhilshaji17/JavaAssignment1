import java.util.Scanner;

public class Anagram 
{

    public static void main(String[] args) 
    {
        Anagram an = new Anagram();
        String originalString, anagramString;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the original string, followed by the anagram string");
        originalString = in.next();
        anagramString = in.next();
        int counter = 0;
        String resultString;
        if (originalString.length() != anagramString.length()) 
        {
            resultString = "Its not an Anagram";
        }
        else
        {
            for (int i = 0; i < originalString.length(); i++) 
            {
                for (int j = 0; j < anagramString.length(); j++) 
                {
                    if (originalString.charAt(i) == anagramString.charAt(j)) 
                    {
                        counter++;
                        break;
                    } 
                    else 
                    {
                        if (j == anagramString.length() - 1) 
                        {
                            counter = 0;
                            break;
                        } 
                        else 
                        {
                         continue;
                        }
                    }
                }
            }
        }
        if (counter == originalString.length()) {
            resultString = "It is an Anagram";
        } else {
            resultString = "It's not an Anagram";
        }

        System.out.println(resultString);
        in.close();
    }
}

    