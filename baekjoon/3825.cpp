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

int T;

set<int> s;

void init() {
  for (int i = 0; i < 142; i++) {
    for (int j = 0; j < 142; j++) {
      int v = i * i + j * j;
      if (1 < v && v < 20000)
        s.insert(v);
    }
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  init();
  while (T--) {
    int m, n;
    cin >> m >> n;
    int target = m * m + n * n;
    int isP = 1;
    for (int num : s) {
      if (target % num == 0 && s.find(target / num) != s.end()) {
        isP = 0;
        break;
      }
    }
    if (isP)
      cout << "P\n";
    else
      cout << "C\n";
  }
}
