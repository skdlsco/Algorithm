#include <algorithm>
#include <iostream>

using namespace std;
typedef long long ll;

int T, cnt;
const int H = 5;
const int W = 9;
char map[5][9];

int dy[] = {1, -1, 0, 0};
int dx[] = {0, 0, 1, -1};

int solve(int depth) {
  int ans = depth;
  for (int y = 0; y < H; y++) {
    for (int x = 0; x < W; x++) {
      if (map[y][x] == 'o') {
        for (int d = 0; d < 4; d++) {
          int ny = dy[d] + y;
          int nx = dx[d] + x;
          int nny = dy[d] + ny;
          int nnx = dx[d] + nx;
          if (0 <= nny && nny < H && 0 <= nnx && nnx < W) {
            if (map[ny][nx] == 'o' && map[nny][nnx] == '.') {
              map[y][x] = '.';
              map[ny][nx] = '.';
              map[nny][nnx] = 'o';
              ans = max(ans, solve(depth + 1));
              map[y][x] = 'o';
              map[ny][nx] = 'o';
              map[nny][nnx] = '.';
            }
          }
        }
      }
    }
  }
  return ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cnt = 0;
    for (int y = 0; y < H; y++) {
      for (int x = 0; x < W; x++) {
        cin >> map[y][x];
        if (map[y][x] == 'o')
          cnt++;
      }
    }
    int ans = solve(0);
    cout << cnt - ans << " " << ans << "\n";
  }
  return 0;
}