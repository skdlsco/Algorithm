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
ll dp[81];
ll ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dp[0] = 0;
  dp[1] = 1;

  for (int i = 2; i <= N; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
  }
  ans = 4;
  for (int i = 2; i <= N; i++) {
    ans += dp[i] * 2;
  }
  cout << ans;
}
