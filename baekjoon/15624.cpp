#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

typedef long long int ll;

int N;
int MOD = 1000000007;
int dp[1000001];

int f(int n) {
  if (dp[n])
    return dp[n];
  if (n == 0)
    return 0;
  if (n == 1)
    return 1;
  return dp[n] = (f(n - 1) + f(n - 2)) % 1000000007;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> N;
  cout << f(N);
  return 0;
}
