#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

// E W S N;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

// 워프 dx,dy 방향으로 n칸 이동가능 워프.
int warpMap[501][501][4];
int isWarp[501][501];

// 0: 일반 모드 1: 변신 모드
int visited[501][501][2];

struct Data {
  int cost;
  int form;
  int x;
  int y;

  bool operator<(const Data &d) const {
    return d.cost < cost;
  };
};

priority_queue<Data> pq;

int N, t, ey, ex;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> N >> t >> ey >> ex;

  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      char c;
      cin >> c;
      isWarp[y][x] = c == '#';
      visited[y][x][0] = visited[y][x][1] = 2121212121;
    }
  }

  // 워프사용시 도착지점 설정
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      for (int d = 0; d < 4; d++) {
        int cy = y + dy[d];
        int cx = x + dx[d];
        int len = 1;
        while (1 <= cy && cy <= N && 1 <= cx && cx <= N && !isWarp[cy][cx]) {
          len++;
          cy += dy[d];
          cx += dx[d];
        }
        if (1 <= cy && cy <= N && 1 <= cx && cx <= N)
          warpMap[y][x][d] = len;
      }
    }
  }
  pq.push({0, 0, 1, 1});
  visited[1][1][0] = 0;
  while (!pq.empty()) {
    Data cur = pq.top();
    pq.pop();
    if (cur.x == ex && cur.y == ey)
      break;

    if (cur.form) {
      // 변신 해제
      if (visited[cur.y][cur.x][0] > cur.cost) {
        visited[cur.y][cur.x][0] = cur.cost;
        pq.push({cur.cost, 0, cur.x, cur.y});
      }
      for (int d = 0; d < 4; d++) {
        int ny = cur.y + dy[d] * warpMap[cur.y][cur.x][d];
        int nx = cur.x + dx[d] * warpMap[cur.y][cur.x][d];
        if (visited[ny][nx][1] > cur.cost + 1) {
          visited[ny][nx][1] = cur.cost + 1;
          pq.push({cur.cost + 1, 1, nx, ny});
        }
      }
    } else {
      // 변! 신!
      if (visited[cur.y][cur.x][1] > cur.cost + t) {
        visited[cur.y][cur.x][1] = cur.cost + t;
        pq.push({cur.cost + t, 1, cur.x, cur.y});
      }

      for (int d = 0; d < 4; d++) {
        int ny = cur.y + dy[d];
        int nx = cur.x + dx[d];
        if (1 <= ny && ny <= N && 1 <= nx && nx <= N && visited[ny][nx][0] > cur.cost + 1) {
          visited[ny][nx][0] = cur.cost + 1;
          pq.push({cur.cost + 1, 0, nx, ny});
        }
      }
    }
  }
  cout << min(visited[ey][ex][0], visited[ey][ex][1]);
}