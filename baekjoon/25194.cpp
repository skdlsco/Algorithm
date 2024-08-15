#include <algorithm>
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
int dp[7];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dp[0] = 1;
  while (N--) {
    int num;
    cin >> num;
    int next[7];
    for (int i = 0; i < 7; i++) {
      next[i] = 0;
    }
    for (int i = 0; i < 7; i++) {
      next[(i + num) % 7] |= dp[i];
    }
    for (int i = 0; i < 7; i++) {
      dp[i] |= next[i];
    }
  }
  if (dp[4])
    cout << "YES";
  else
    cout << "NO";
  return 0;
}