#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

struct Coin {
  int v, c;
};

bool operator<(Coin a, Coin b) {
  return a.c > b.c;
}

int T, S, N;
int caseCnt = 1;
ll dp[5001];
vector<Coin> arr;
void solve() {
  cin >> S >> N;

  fill(dp, dp + S + 1, 0);
  dp[0] = 1;
  arr.clear();

  while (N--) {
    int V, C;
    cin >> V >> C;
    arr.push_back({V, C});
  }
  sort(arr.begin(), arr.end());
  ll ans = 0;
  for (Coin coin : arr) {
    for (int i = S; i >= coin.v; i--) {
      dp[i] += dp[i - coin.v];
      if (S % i == 0 && S / i <= coin.c) {
        ans += dp[i - coin.v];
      }
    }
  }
  cout << "Case " << caseCnt++ << ": " << ans << "\n";
}
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;

  while (T--) {
    solve();
  }
}