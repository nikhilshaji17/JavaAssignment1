import java.util.*;

public class CountingSort
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int initialArray[] = new int[21];
        int indexArray[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int counterArray[] = new int[21];
        int sortedArray[] = new int[21];

        System.out.println("Enter 21 elements in the new array, no value should greater lesser than 0 or greater than 20: ");
        for(int i=0; i < 21 ; i++)
        {
            int x = input.nextInt();
            if (x>20 || x<0)
            {
                System.out.println("Invalid input, exiting");
                System.exit(0);
            }
            initialArray[i] = x;
            counterArray[i] = 0;
        }
        for(int i=0; i<21; i++)
        {
            int x = initialArray[i];
            counterArray[x] = counterArray[x]+1;
        }
        int j = 0;
        while(j<21)
        {
            for(int i = 0; i<21; i++)
            {
                if(counterArray[i]==0)
                {
                    continue;
                }
                else
                {
                    while(counterArray[i]>0)
                    {
                        sortedArray[j] = i;
                        counterArray[i]= counterArray[i]-1;
                        j = j+1;

                    }
                }
        
            }
        }
        System.out.println("The sorted array is: ");
        for(int i = 0; i<21; i++)
        {
            System.out.println(sortedArray[i]);
        }

    }
}