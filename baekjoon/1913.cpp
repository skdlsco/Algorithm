#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, M;

int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};
int arr[1000][1000];
int targetY, targetX;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  int x = N / 2, y = N / 2;
  int len = 1;
  int d = 0;
  int num = 1;

  while (true) {
    for (int i = 0; i < len; i++) {
      if (num == M) {
        targetX = x;
        targetY = y;
      }
      arr[y][x] = num++;
      if (x == 0 && y == 0)
        break;
      x += dx[d];
      y += dy[d];
    }
    if (d % 2)
      len++;
    d = (d + 1) % 4;
    if (x == 0 && y == 0)
      break;
  }
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cout << arr[i][j] << " ";
    }
    cout << "\n";
  }
  cout << targetY + 1 << " " << targetX + 1 << "\n";
  return 0;
}
