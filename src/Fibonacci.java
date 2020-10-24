import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Fibonacci {

	public static BigInteger fib(int n) {
		if (n < 2) {
			return BigInteger.valueOf(1);
		}

		return fib2(n - 1).add(fib2(n - 2));
	}

	static BigInteger fib2(int n) {
		/* Declare an array to store Fibonacci numbers. */
		BigInteger f[] = new BigInteger[n + 2]; // 1 extra to handle case, n = 0
		int i;

		/* 0th and 1st number of the series are 0 and 1 */
		f[0] = BigInteger.valueOf(1);
		f[1] = BigInteger.valueOf(2);

		for (i = 2; i <= n; i++) {
			/*
			 * Add the previous 2 numbers in the series and store it
			 */

			f[i] = f[i - 1].add(f[i - 2]);

		}

		return f[n];
	}

	public static void main(String[] args) {
		int n = Integer.MAX_VALUE;
		//ab 138090

//		for (int i = 138290; i < Integer.MAX_VALUE; i= i+1000) {
			int i = 219290;
			System.out.println("Durchgang Nr: " + i);
			System.out.println();
			final long timeStart1 = System.currentTimeMillis();
			BigInteger fib1 = fib(i-1);
			System.out.println(fib1.bitLength());
			final long timeEnd1 = System.currentTimeMillis();
			System.out.println("Dauer not Memoized: " + (timeEnd1 - timeStart1) + " Millisekunden");

			final long timeStart2 = System.currentTimeMillis();
			BigInteger fib2 = fib2(i-1);
			final long timeEnd2 = System.currentTimeMillis();
			System.out.println("Dauer Memoized: " + (timeEnd2 - timeStart2) + " Millisekunden");

			long zeit = ((timeEnd1 - timeStart1) - (timeEnd2 - timeStart2));
			System.out.println("Memoized ist " + zeit + " Millisekunden schneller");
			System.out.println();
			System.out.println();
//		}
	}
}
