#include <algorithm>
#include <iostream>

using namespace std;

int N, M, dp[10001];
int MOD = 1999;

int main() {
	cin.tie(0);
	cout.tie(0);
	ios_base::sync_with_stdio(false);
	cin >> N >> M;
	dp[0] = 1;
	int temp = 1;
	for (int i = 1; i < N; i++) {
		temp = (temp * 2) % MOD;
	}
	for (int i = 1; i <= M; i++) {
		for (int j = 1; j <= N; j++) {
			if (j > i)
				break;
			if (j == N)
				dp[i] = (dp[i] + dp[i - j] * temp) % MOD;
			else
				dp[i] = (dp[i] + dp[i - j]) % MOD;
		}
	}
	cout << dp[M];
}
