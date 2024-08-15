#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

ll dp[501][501];
ll pre[501][501];
int N, E;
ll MOD = 1000000007;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> E;
  dp[1][0] = 1;
  for (int i = 2; i <= N; i++) {
    for (int j = 0; j <= i - 1; j++) {
      if (j > 0)
        dp[i][j] += dp[i - 1][j - 1];
      for (int k = 1; k <= (i - 1); k++) {
        ll A = dp[k][j];
        ll B = pre[k][j + 1];
        ll C = dp[i - 1 - k][j];
        ll D = pre[i - 1 - k][j + 1];

        if (i - 1 - k == k) {
          dp[i][j] += A * B * 2;
          dp[i][j] += A * A;
        } else {
          dp[i][j] += A * (C + D);
          dp[i][j] += B * C;
        }
        dp[i][j] %= MOD;
      }
      dp[i][j] %= MOD;
    }
    for (int j = i - 1; j >= 0; j--) {
      pre[i][j] = (pre[i][j + 1] + dp[i][j]) % MOD;
    }
  }
  cout << dp[N][E];
}
