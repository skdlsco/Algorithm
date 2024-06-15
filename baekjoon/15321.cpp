#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int arr[501];
int power[501][501];
int dp[501][501];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
    power[i][i] = arr[i];
  }

  for (int i = 1; i < N; i++) {
    for (int s = 1; s <= N - i; s++) {
      power[s][s + i] = max(power[s + 1][s + i], power[s][s + i - 1]);
    }
  }

  for (int i = 1; i < N; i++) {
    for (int s = 1; s <= N - i; s++) {
      int e = s + i;
      dp[s][e] = 123456789;
      for (int m = s; m < s + i; m++) {
        dp[s][e] = min(dp[s][e], dp[s][m] + dp[m + 1][e] + abs(power[s][m] - power[m + 1][e]));
      }
    }
  }
  cout << dp[1][N];
  return 0;
}
