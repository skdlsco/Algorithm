#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
ll dp[31][31];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  while (true) {
    cin >> N;
    if (!N)
      break;
    for (int i = 0; i <= 30; i++) {
      for (int j = 0; j <= 30; j++) {
        dp[i][j] = 0;
      }
    }
    dp[N][0] = 1;
    for (int i = N; i >= 0; i--) {
      for (int j = N; j >= 0; j--) {
        if (i > 0 && j < N)
          dp[i - 1][j + 1] += dp[i][j];
        if (j > 0)
          dp[i][j - 1] += dp[i][j];
      }
    }
    cout << dp[0][0] << "\n";
  }
  return 0;
}
