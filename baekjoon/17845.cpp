#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int N, M;
int dp[10001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 0; i < M; i++) {
    int I, T;
    cin >> I >> T;
    for (int j = N; j >= T; j--) {
      dp[j] = max(dp[j], dp[j - T] + I);
    }
  }
  cout << dp[N];
  return 0;
}
