#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

int N, M;

ll arr[100001];
ll dp[100001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }
  dp[0] = 0;
  dp[1] = arr[1];
  for (int i = 2; i <= N; i++) {
    dp[i] = min(dp[i - 1], dp[i - 2]) + arr[i];
  }
  cout << min(dp[N], dp[N - 1]);
  return 0;
}
