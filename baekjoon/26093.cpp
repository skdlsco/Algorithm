#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

int N, M;
int dp[101][10001], ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    int first, second;
    first = dp[i - 1][0] > dp[i - 1][1] ? 0 : 1;
    second = dp[i - 1][0] > dp[i - 1][1] ? 1 : 0;
    for (int j = 2; j < M; j++) {
      if (dp[i - 1][first] < dp[i - 1][j]) {
        second = first;
        first = j;
      } else if (dp[i - 1][second] < dp[i - 1][j]) {
        second = j;
      }
    }
    for (int j = 0; j < M; j++) {
      int v;
      cin >> v;
      dp[i][j] = j != first ? (dp[i - 1][first] + v) : (dp[i - 1][second] + v);
    }
  }
  for (int i = 0; i < M; i++) {
    ans = max(ans, dp[N][i]);
  }
  cout << ans;
  return 0;
}
