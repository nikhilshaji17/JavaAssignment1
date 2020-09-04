import java.util.*;

public class stringSort
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s1 = input.nextLine();
        Character letters[] = new Character[s1.length()];
        for (int i = 0; i < s1.length() ; i++)
        {
            letters[i] = s1.charAt(i);
        }
        char curLetter = 'a';
        for(int j = 0; j < s1.length() ; j++)
        {
            for (int i = 0; i<s1.length()-j-1; i++)
            {
                if (letters[i] >= letters[i+1])
                {
                    curLetter = letters[i+1];
                    letters[i+1] = letters[i];
                    letters[i] = curLetter;
                }
            }
        }
        System.out.println("The sorted array is: ");
        for (int i = 0; i<s1.length(); i++)
        {
            System.out.print(letters[i]);
        }
    }
}