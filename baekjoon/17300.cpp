#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int L;
int checked[9];
int dx[] = {1, -1, 0, 0, 1, -1, 1, -1};
int dy[] = {0, 0, 1, -1, 1, -1, -1, 1};
int prv = -1;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> L;
  while (L--) {
    int cur;
    cin >> cur;
    cur--;
    int movable = 1;

    if (prv == -1) {
      checked[cur] = 1;
      prv = cur;
      continue;
    }
    int cx = cur % 3;
    int cy = cur / 3;
    int px = prv % 3;
    int py = prv / 3;
    for (int d = 0; d < 8; d++) {
      int nx = px + dx[d] * 2;
      int ny = py + dy[d] * 2;
      if (nx == cx && ny == cy) {
        int temp = (px + dx[d]) + (py + dy[d]) * 3;
        if (!checked[temp])
          movable = 0;
      }
    }
    if (checked[cur])
      movable = 0;
    checked[cur] = 1;
    if (!movable) {
      cout << "NO";
      return 0;
    }
    prv = cur;
  }
  cout << "YES";
  return 0;
}
