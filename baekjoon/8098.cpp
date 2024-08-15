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
vector<int> lecture[30001];
int dp[30001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    int s, e;
    cin >> s >> e;
    lecture[e].push_back(s);
  }
  for (int i = 1; i <= 30000; i++) {
    dp[i] = dp[i - 1];
    for (int s : lecture[i]) {
      dp[i] = max(dp[i], dp[s] + i - s);
    }
  }
  cout << dp[30000] << "\n";
}
