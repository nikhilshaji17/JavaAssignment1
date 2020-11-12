/*Write a program to create a simple counting thread. It will count to 100, pausing one
second between each number. Also, in keeping with counting theme, it will output a string
every ten number. */


public class Solution1 extends Thread 
{
    public static void main(String[] args)
    {
        Solution1 c = new Solution1();
        c.start();
    }

    public void run()
    {
        for(int i=1;i<=100;i++)
        {
            System.out.println(i);
            if(i%10 == 0)
            {
                System.out.println("Ten numbers printed.");
            }
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

}