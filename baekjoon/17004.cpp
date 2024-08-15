#include <algorithm>
#include <cmath>
#include <deque>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

string s, t;
ll nextIdx[100001][26];
ll ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> s >> t;
  for (int i = 0; i < 26; i++) {
    nextIdx[s.length() - 1][i] = -1;
  }
  for (int i = s.length() - 2; i >= 0; i--) {
    for (int j = 0; j < 26; j++) {
      nextIdx[i][j] = nextIdx[i + 1][j];
      if (s[i + 1] == 'a' + j)
        nextIdx[i][j] = i + 1;
    }
  }

  for (int start = 0; start < s.length(); start++) {
    int cur = start;
    ll right = 0;
    int valid = 1;
    if (s[start] != t[0] && nextIdx[cur][t[0] - 'a'] == -1)
      continue;
    else if (s[start] != t[0])
      cur = nextIdx[cur][t[0] - 'a'];

    for (int i = 1; i < t.length(); i++) {
      if (nextIdx[cur][t[i] - 'a'] == -1) {
        valid = 0;
        break;
      }

      cur = nextIdx[cur][t[i] - 'a'];
    }
    if (!valid)
      continue;
    right = s.length() - cur;
    ans += right;
  }
  cout << ans;
}