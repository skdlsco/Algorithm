#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
typedef long long ll;

ll N, M, T, dp[1001][1001];
ll MOD = 1000000009;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  dp[0][0] = 1;
  for (int i = 1; i <= 1000; i++) {
    for (int j = 1; j <= 1000; j++) {
      for (int k = 1; k <= 3; k++) {
        if (i < k)
          continue;
        dp[i][j] = (dp[i][j] + dp[i - k][j - 1]) % MOD;
      }
    }
  }
  while (T--) {
    cin >> N >> M;
    cout << dp[N][M] << "\n";
  }
  return 0;
}
