public class debuging_1_3_sum {
	public static void main(String[] args) {
		int[] A = { 16, 4, 2, 7, 11, 1};
		
		int n = 6;
		for(int b = 1; b < 150; b++) {
			System.out.println(one_three_sum(A, b, n) + " " + b);
		}
		int sum = 0;
		for(int i = 0; i < n; i++) {
			sum += A[i]*3;
		}
		System.out.println(sum);
	}

	public static boolean one_three_sum(int[] A, int b, int n) {

		boolean[][] dp = new boolean[n + 1][b + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= b; j++) {
				if (i == 0) {
					if (j == 0) {
						dp[0][0] = true;
					} else {
						dp[0][j] = false;
					}
				} else {
					if (j >= 3 * A[i - 1]) {
						dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]] || dp[i - 1][j - 3 * A[i - 1]];
					} else if (j >= A[i - 1]) {
						dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
					} else {
						dp[i][j] = dp[i - 1][j];
					}

				}
			}
		}

		return dp[n][b];
	}
}
