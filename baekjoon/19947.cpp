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

int H, Y;
int dp[11];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> H >> Y;
  dp[0] = H;
  for (int i = 1; i <= Y; i++) {
    dp[i] = max(dp[i], (int)(dp[i - 1] * 1.05));
    if (i >= 3)
      dp[i] = max(dp[i], (int)(dp[i - 3] * 1.20));
    if (i >= 5)
      dp[i] = max(dp[i], (int)(dp[i - 5] * 1.35));
  }
  cout << dp[Y];
}
