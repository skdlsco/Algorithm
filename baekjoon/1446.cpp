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
int N, D;

vector<pii> path[10001];
int dp[10001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> D;
  while (N--) {
    int s, e, d;
    cin >> s >> e >> d;
    path[s].push_back({e, d});
  }
  for (int i = 1; i <= D; i++) {
    dp[i] = i;
  }

  for (int i = 0; i <= D; i++) {
    if (i > 0) dp[i] = min(dp[i], dp[i - 1] + 1);
    for (pii data : path[i])
      dp[data.first] = min(dp[data.first], dp[i] + data.second);
  }
  cout << dp[D];
}
