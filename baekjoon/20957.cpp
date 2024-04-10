#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

ll T, N;
ll MOD = 1000000007;
ll dp[10001][7][7];

void solve() {
  cin >> N;
  cout << dp[N][0][0] << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;

  for (int i = 1; i <= 9; i++) {
    dp[1][i % 7][i % 7]++;
  }
  for (int i = 1; i < 10000; i++) {
    for (int j = 0; j < 7; j++) {
      for (int k = 0; k < 7; k++) {
        for (int num = 0; num < 10; num++) {
          dp[i + 1][(j + num) % 7][(k * num) % 7] += dp[i][j][k];
          dp[i + 1][(j + num) % 7][(k * num) % 7] %= MOD;
        }
      }
    }
  }
  while (T--) {
    solve();
  }
  return 0;
}
