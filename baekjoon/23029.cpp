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
int dp[100001];
int arr[100001];
int N;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }
  dp[1] = arr[1];
  dp[2] = max(arr[2], arr[1] + arr[2] / 2);
  for (int i = 3; i <= N; i++) {
    dp[i] = dp[i - 1];
    dp[i] = max(dp[i], dp[i - 2] + arr[i]);
    dp[i] = max(dp[i], dp[i - 3] + arr[i - 1] + arr[i] / 2);
  }
  cout << dp[N];
}