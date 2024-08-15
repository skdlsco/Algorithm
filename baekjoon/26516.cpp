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

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  while (1) {
    string s;
    cin >> s;
    if (s == "0")
      break;
    int targetIdx = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s[i] > s[targetIdx])
        targetIdx = i;
    }
    if ((s[targetIdx] - '0') % 2) {
      s[targetIdx] = '0';
    } else {
      s[targetIdx] = '0' + ((s[targetIdx] - '0' + 4) % 10);
    }
    char prev = '0';
    for (char c : s) {
      if (prev == '0' && c == '0')
        continue;
      cout << c;
      prev = c;
    }
    cout << "\n";
  }
}
