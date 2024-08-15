#include <algorithm>
#include <deque>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<ll, ll> pll;

struct Node {
  int x, y, i;
};

int w, h;
int map[1001][1001];
int ans[1001][1001];
int visited[1001][1001];
int visited2[1001][1001];
int sx, sy;
queue<Node> q;
string str;

int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};
char dir[] = "NWSE";

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> w >> h;
  for (int y = 0; y < h; y++) {
    for (int x = 0; x < w; x++) {
      char c;
      cin >> c;
      if (c == 'S') {
        sx = x;
        sy = y;
      }
      map[y][x] = c == '.';
    }
  }

  cin >> str;

  q.push({sx, sy, 0});
  while (!q.empty()) {
    Node cur = q.front();
    q.pop();
    for (int d = 0; d < 4; d++) {
      int ny = cur.y + dy[d];
      int nx = cur.x + dx[d];
      if (ny < 0 || ny >= h || nx < 0 || nx >= w || !map[ny][nx] ||
          visited[ny][nx])
        continue;
      visited[ny][nx] = cur.i + 1;
      q.push({nx, ny, cur.i + 1});
    }
  }

  q.push({sx, sy, 0});
  visited2[sy][sx] = 1;
  while (!q.empty()) {
    Node cur = q.front();
    q.pop();
    if (cur.i == str.length()) {
      ans[cur.y][cur.x] = 1;
      continue;
    }
    for (int d = 0; d < 4; d++) {
      if (dir[d] == str[cur.i]) continue;
      int ny = cur.y + dy[d];
      int nx = cur.x + dx[d];
      if (ny < 0 || ny >= h || nx < 0 || nx >= w || !map[ny][nx] ||
          visited[ny][nx] < cur.i || visited2[ny][nx])
        continue;
      visited2[ny][nx] = 1;
      q.push({nx, ny, cur.i + 1});
    }
  }

  for (int y = 0; y < h; y++) {
    for (int x = 0; x < w; x++) {
      if (y == sy && x == sx)
        cout << 'S';
      else if (ans[y][x])
        cout << '!';
      else if (!map[y][x])
        cout << '#';
      else
        cout << '.';
    }
    cout << "\n";
  }
  return 0;
}
