#include <algorithm>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[] = {1, 1, 0, -1, -1, -1, 0, 1};
int h, w;

struct Data {
  int x;
  int y;
  ll depth;
};

bool operator<(const Data &a, const Data &b) {
  return a.depth > b.depth;
}

ll map[501][501];
ll maxDepth[501][501];
priority_queue<Data> pq;
ll ans;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> h >> w;
  for (int y = 1; y <= h; y++) {
    for (int x = 1; x <= w; x++) {
      cin >> map[y][x];
    }
  }
  int sx, sy;
  cin >> sy >> sx;

  maxDepth[sy][sx] = map[sy][sx];
  pq.push({sx, sy, map[sy][sx]});
  while (!pq.empty()) {
    int cx = pq.top().x;
    int cy = pq.top().y;
    ll curDepth = pq.top().depth;

    if (curDepth <= maxDepth[cy][cx])
      ans += -curDepth;
    pq.pop();
    for (int d = 0; d < 8; d++) {
      int nx = cx + dx[d];
      int ny = cy + dy[d];
      if (1 <= nx && nx <= w && 1 <= ny && ny <= h && map[ny][nx] < 0 && maxDepth[ny][nx] > max(map[ny][nx], curDepth)) {
        int nextDepth = max(map[ny][nx], curDepth);
        maxDepth[ny][nx] = nextDepth;
        pq.push({nx, ny, nextDepth});
      }
    }
  }

  cout << ans;
}
