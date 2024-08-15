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

int N;
int dp[101];
int cost[21];
int value[21];
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> cost[i];
  }
  for (int i = 0; i < N; i++) {
    cin >> value[i];
  }

  for (int i = 0; i < N; i++) {
    int c = cost[i];
    int v = value[i];
    for (int j = 100; j > c; j--) {
      dp[j] = max(dp[j], dp[j - c] + v);
      ans = max(ans, dp[j]);
    }
  }
  cout << ans << "\n";
}
