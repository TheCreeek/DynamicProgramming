import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RodCutting {

	static int[] priceList = new int[50];

	public RodCutting() {

	}

	public static int CutRod(int[] priceList2, int n) {
		if (n == 0)
			return 0;

		int q = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {

			int p1 = 0;

			if (i >= 10) {
				p1 = priceList2[9];
			} else
				p1 = priceList2[i];

			q = Math.max(q, p1 + CutRod(priceList2, n - i - 1));
		}
		return q;
	}

	public static int memoizedCutRod(int[] prices, int n) {
		int[] revs = new int[n + 1];// revs[i] corresponds to the maximum revenues of length i. We define revs[0] =
									// 0.
		for (int i = 0; i < revs.length; i++) {
			revs[i] = -1;// we use -1 here to indicate a state that the revs is not cached yet instead of
							// negative infinity in the book because revenue is always nonnegative.
		}
		return memoizedCutRodAux(prices, n, revs);
	}

	private static int memoizedCutRodAux(int[] prices, int n, int[] revs) {
		if (revs[n] >= 0) {
			return revs[n];
		}
		int max = Integer.MIN_VALUE;
		if (n == 0) {
			max = 0;
		} else {
			for (int i = 0; i < n; i++) {
				max = Math.max(max, prices[i] + memoizedCutRodAux(prices, n - i - 1, revs));
			}
		}
		revs[n] = max;
		return max;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < priceList.length; j++) {
				priceList[j] = ThreadLocalRandom.current().nextInt(100);
			}
		}

		// final long timeStart = System.currentTimeMillis();
		// System.out.println(CutRod(priceList, 30));
		// final long timeEnd = System.currentTimeMillis();
		// System.out.println("Dauer CutRod: " + (timeEnd - timeStart) + "
		// Millisekunden");

		final long timeStart2 = System.currentTimeMillis();
		System.out.println(memoizedCutRod(priceList, 50));
		final long timeEnd2 = System.currentTimeMillis();
		System.out.println("Dauer Memoized: " + (timeEnd2 - timeStart2) + " Millisekunden");

	}
}
