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

int N, L;
int ans;
int dx[] = {1, -1};

int calcPos(int T, int l, int d) {
  if (L == l)
    return 0;
  int move = T % (L - l);
  if ((ans / (L - l)) % 2)
    d = !d;
  int pos = dx[d] * move;
  if (d)
    pos += L - l;
  return pos;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> L;

  N--;
  int l, d;
  int prevL, prevD;
  cin >> prevL >> prevD;
  while (N--) {
    cin >> l >> d;
    while (1) {
      int prevS = calcPos(ans, prevL, prevD);
      int prevE = prevS + prevL;
      int curS = calcPos(ans, l, d);
      int curE = curS + l;
      if ((prevS <= curS && curS <= prevE) || (prevS <= curE && curE <= prevE))
        break;
      if ((curS <= prevS && prevS <= curE) || (curS <= prevE && prevE <= curE))
        break;
      ans++;
    }
    prevL = l;
    prevD = d;
  }
  cout << ans;
}
