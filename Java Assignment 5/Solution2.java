/* Write a program that uses multiple threads to find the integer in the range 1 to 10000 that has the
largest number of divisors but for the range 1 to 100000 (or less, if you don't have a fast computer).At
the end of the program, output the elapsed. */


// Only checking halfway due to slow laptop

public class Solution2 extends Thread
{
    static int maxDiv = 0, num = 0;
    int start;
    public Solution2(int start)
    {
        this.start = start;
    }
    public static void main(String[] args)
    {
        Solution2 thread1 = new Solution2(1), thread2 = new Solution2(10001), thread3 = new Solution2(20001), thread4 = new Solution2(30001), thread5 = new Solution2(40001);
        long startTimer = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try
        {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }
        catch(Exception e){System.out.println("Error");}
        long stopTimer = System.currentTimeMillis();
        System.out.println(num + " has the maximum number of divisors with: " + maxDiv);
        System.out.println("Total time elapsed: " + (stopTimer-startTimer));
    }

    public void run()
    {
        int noOfDivisors=0;
        for(int i=start; i<this.start+10000; i++)
        {
            noOfDivisors=0;
            for(int j=1; j<=i; j++)
            {
                if(i%j==0)
                {
                    noOfDivisors+=1;
                }
            }
            if(noOfDivisors>maxDiv)
            {
                synchronized(this)
                {
                    maxDiv = noOfDivisors;
                    num = i;
                }
            }
        }
    }
}