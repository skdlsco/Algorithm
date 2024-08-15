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

int dp[5001];
int arr[5001];
int N, K;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }
  dp[0] = 1;
  for (int i = 0; i < N; i++) {
    if (!dp[i])
      continue;
    for (int j = i + 1; j < N; j++) {
      if (dp[i] + (j - i) * (1 + abs(arr[i] - arr[j])) <= K)
        dp[j] = 1;
    }
  }
  if (dp[N - 1])
    cout << "YES";
  else
    cout << "NO";
}