#include <algorithm>
#include <iostream>

using namespace std;

int N;
int dp[32768][5];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  dp[0][0] = 1;
  for (int i = 1; i * i < 32768; i++) {
    for (int j = 1; j < 32768; j++) {
      if (j - i * i >= 0) {
        for (int k = 1; k <= 4; k++) {
          dp[j][k] += dp[j - i * i][k - 1];
        }
      }
    }
  }
  while (true) {
    cin >> N;
    if (N == 0)
      break;
    int ans = 0;
    for (int i = 1; i <= 4; i++) {
      ans += dp[N][i];
    }
    cout << ans << "\n";
  }
  return 0;
}