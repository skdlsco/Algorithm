#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;
typedef long long ll;

int N;

int dp[1001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dp[0] = 0;
  dp[1] = 0;
  dp[2] = 1;
  dp[3] = 0;
  for (int i = 4; i <= N; i++) {
    dp[i] = !dp[i - 1] | !dp[i - 3];
  }
  if (dp[N])
    cout << "SK";
  else
    cout << "CY";
  return 0;
}