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

ll dp[10001];
ll P, Q;
int TC = 1;
int T;

void solve() {
  cin >> P >> Q;
  dp[0] = 0;
  dp[1] = 1;
  for (int i = 2; i <= P; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
    if (i % 32 < 2)
      dp[i] %= Q;
  }
  cout << "Case #" << TC << ": " << dp[P] % Q << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
    TC++;
  }
}
