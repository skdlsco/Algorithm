#include <algorithm>
#include <cmath>
#include <deque>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
int N;
int TC = 1;
ll dp[100001][3];

void solve() {
  cin >> dp[0][0] >> dp[0][1] >> dp[0][2];
  dp[0][0] = 1234567890;
  dp[0][2] += dp[0][1];
  for (int i = 1; i < N; i++) {
    for (int j = 0; j < 3; j++) {
      int cur;
      cin >> cur;
      dp[i][j] = dp[i - 1][j];
      if (j > 0) {
        dp[i][j] = min(dp[i][j], dp[i][j - 1]);
        dp[i][j] = min(dp[i][j], dp[i - 1][j - 1]);
      }
      if (j < 2) {
        dp[i][j] = min(dp[i][j], dp[i - 1][j + 1]);
      }
      dp[i][j] += cur;
    }
  }
  cout << TC << ". " << dp[N - 1][1] << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  while (1) {
    cin >> N;
    if (!N) break;
    solve();
    TC++;
  }
}
