#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int T;
string s;

int isSlump(string s, int idx) {
  int flag = 0;
  while (idx < s.size()) {
    char c = s[idx];
    switch (flag) {
      // first
    case 0:
      if (c != 'E' && c != 'D')
        return 0;
      flag = 1;
      break;
    // after E, D
    case 1:
      if (c != 'F')
        return 0;
      flag = 2;
      break;
    case 2:
      // after F
      if (c == 'G')
        return idx;
      else if (c == 'E' || c == 'D')
        flag = 1;
      else if (c != 'F')
        return 0;
    }
    idx++;
  }
  return 0;
}

int isSlimp(string s, int idx) {
  if (idx >= s.size())
    return 0;
  if (s[idx] != 'A')
    return 0;
  idx++;
  if (idx >= s.size())
    return 0;
  if (s[idx] == 'H')
    return idx;
  if (s[idx] == 'B') {
    idx = isSlimp(s, idx + 1);
    if (idx == 0 || idx + 1 >= s.size() || s[idx + 1] != 'C')
      return 0;
    return idx + 1;
  }
  idx = isSlump(s, idx);
  if (idx == 0 || idx + 1 >= s.size() || s[idx + 1] != 'C')
    return 0;
  return idx + 1;
}

void solve() {
  cin >> s;
  int idx = isSlimp(s, 0);
  if (idx == 0 || isSlump(s, idx + 1) != s.size() - 1)
    cout << "NO\n";
  else
    cout << "YES\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;

  cout << "SLURPYS OUTPUT\n";
  while (T--) {
    solve();
  }
  cout << "END OF OUTPUT\n";
}