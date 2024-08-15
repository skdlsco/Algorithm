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
ll MOD = 16769023;
int dp[100001][2];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dp[0][0] = 1;
  for (int i = 1; i <= N; i++) {
    dp[i][1] = (dp[i - 1][0] * 2) % MOD;
    dp[i][0] = dp[i - 1][1];
  }
  cout << (dp[N][1] + dp[N][0]) % MOD;
}
