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

int dp[100001];
int MAX_V = 1000000;
int N;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i <= N; i++) {
    dp[i] = MAX_V;
  }
  dp[0] = 0;
  for (int i = 0; i <= N; i++) {
    if (i >= 2)
      dp[i] = min(dp[i], dp[i - 2] + 1);
    if (i >= 5)
      dp[i] = min(dp[i], dp[i - 5] + 1);
  }
  if (dp[N] == MAX_V)
    cout << -1;
  else
    cout << dp[N];
}
