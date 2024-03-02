#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

int N, M, T;
ll s, ns;
ll f, nf;
string str;
ll MOD = 1000000007;
void solve() {
  cin >> str;
  s = 0;
  f = 1;

  for (char c : str) {
    ns = 0;
    nf = 0;
    if (c == 'H') {
      ns = f;
      nf = s;
    } else if (c == 'T') {
      ns = s;
      nf = f;
    } else {
      ns = (s + f) % 1000000007;
      nf = (s + f) % 1000000007;
    }
    f = nf;
    s = ns;
  }
  cout << s << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}