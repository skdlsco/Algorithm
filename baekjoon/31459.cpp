#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

int N, M, T;
int X, Y, x, y;
int map[50][50];
void solve() {
  cin >> X >> Y >> x >> y;

  for (int i = 0; i < Y; i++) {
    for (int j = 0; j < X; j++) {
      map[i][j] = 0;
    }
  }
  int cnt = 0;
  for (int i = 0; i < Y; i++) {
    for (int j = 0; j < X; j++) {
      if (!map[i][j]) {
        if (i + y < Y && j + x < X)
          map[i + y][j + x] = 1;
        cnt++;
      }
    }
  }
  cout << cnt << "\n";
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