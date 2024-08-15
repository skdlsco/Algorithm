#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int dp[51][1001];
int N, S, M;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> S >> M;

  dp[0][S] = 1;
  for (int i = 1; i <= N; i++) {
    int V;
    cin >> V;
    for (int m = 0; m <= M; m++) {
      if (!dp[i - 1][m])
        continue;
      int next = m + V;
      if (m - V >= 0)
        dp[i][m - V] = 1;
      if (m + V <= M)
        dp[i][m + V] = 1;
    }
  }
  int ans = -1;
  for (int i = 0; i <= M; i++) {
    if (dp[N][i])
      ans = i;
  }
  cout << ans << "\n";
}