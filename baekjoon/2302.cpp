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

int N, M;
int staticSeat[41];
int dp[41];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  while (M--) {
    int num;
    cin >> num;
    staticSeat[num] = 1;
  }
  dp[0] = 1;
  dp[1] = 1;
  for (int i = 2; i <= N; i++) {
    if (!staticSeat[i] && !staticSeat[i - 1])
      dp[i] += dp[i - 2];
    dp[i] += dp[i - 1];
  }
  cout << dp[N];
}
