#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int N;
int map[50][50];
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

int f(int y, int x, int py, int px, int n) {
  int ans = 0;
  if (n == N - 1) {
    for (int i = 0; i < 4; i++) {
      int ny = y + dy[i];
      int nx = x + dx[i];
      if (ny == py && nx == px)
        continue;
      if (y < ny) {
        ans += ((y % 2) ^ (x % 2)) & map[ny][nx];
      } else if (y > ny) {
        ans += !((y % 2) ^ (x % 2)) & map[ny][nx];
      } else {
        ans += map[ny][nx];
      }
    }
    return ans;
  }
  for (int i = 0; i < 4; i++) {
    int ny = y + dy[i];
    int nx = x + dx[i];
    if (y < ny && !(y % 2) ^ (x % 2))
      continue;
    if (y > ny && (y % 2) ^ (x % 2))
      continue;
    if (map[ny][nx])
      continue;
    map[ny][nx] = 1;
    ans += f(ny, nx, y, x, n + 1);
    map[ny][nx] = 0;
  }
  return ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  map[25][25] = 1;
  map[24][25] = 1;
  cout << f(24, 25, 25, 25, 0);
  return 0;
}
