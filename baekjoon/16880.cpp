#include <algorithm>
#include <iostream>
using namespace std;
typedef long long ll;

int N;
int x, y;
char c;

int palace() { return ((x + y) % 3) + ((x / 3) ^ (y / 3)) * 3; }

int rook() { return (y) ^ (x); }

int bishop() { return min(y, x); }

int king() {
  int map[2][2] = {{0, 3}, {1, 2}};
  if ((y / 2) ^ (x / 2)) {
    if (y > x)
      return map[y % 2][x % 2];
    else
      return map[x % 2][y % 2];
  } else {
    return (y % 2) + (x % 2);
  }
}

int knight() {
  int map[3][3] = {{0, 1, 1}, {1, 0, 1}, {1, 1, 1}};
  if (!((y - 1) / 3 ^ (x - 1) / 3) && x > 0 && y > 0) {
    return map[y % 3][x % 3];
  } else {
    return min(y, x) % 3;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  int ans = 0;
  while (N--) {
    int v;
    cin >> x >> y >> c;
    if (c == 'R')
      v = rook();
    else if (c == 'B')
      v = bishop();
    else if (c == 'K')
      v = king();
    else if (c == 'N')
      v = knight();
    else
      v = palace();
    ans ^= v;
  }
  if (ans)
    cout << "koosaga";
  else
    cout << "cubelover";
  return 0;
}
