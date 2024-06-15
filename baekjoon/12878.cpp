#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

ll N;

ll MOD = 10007;
ll dp[1001][2][2];

ll pow2(ll M) {
  if (M == 0)
    return 1;
  if (M == 1)
    return 2;
  ll x = pow2(M / 2);
  return (x * x * (M % 2 + 1)) % MOD;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  cout << (pow2(2 * (N - 1)) + pow2(N - 1)) % MOD;
  return 0;
}