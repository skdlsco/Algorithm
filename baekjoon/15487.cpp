#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
int arr[1000001];
int dp[5][1000001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }

  int maxV = -1234567890;
  for (int i = 1; i <= N; i++) {
    maxV = max(maxV, -arr[i]);
    dp[1][i] = maxV;
  }
  maxV = -1234567890;
  for (int i = 2; i <= N; i++) {
    maxV = max(maxV, dp[1][i - 1] + arr[i]);
    dp[2][i] = maxV;
  }
  maxV = -1234567890;
  for (int i = 3; i <= N; i++) {
    maxV = max(maxV, dp[2][i - 1] - arr[i]);
    dp[3][i] = maxV;
  }
  maxV = -1234567890;
  for (int i = 4; i <= N; i++) {
    maxV = max(maxV, dp[3][i - 1] + arr[i]);
    dp[4][i] = maxV;
  }
  cout << maxV;
}
