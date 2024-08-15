#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
struct Node {
  int x;
  int y;
  int waveCnt;
};

int N, M;
int arr[1001][1001];
int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[] = {1, 1, 0, -1, -1, -1, 0, 1};
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      char c;
      cin >> c;
      if (c == '.')
        arr[y][x] = -1;
      else
        arr[y][x] = c - '0';
    }
  }
  queue<Node> q;

  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      if (arr[y][x] != -1)
        continue;
      for (int d = 0; d < 8; d++) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (0 <= ny && ny < N && 0 <= nx && nx < M && arr[ny][nx] != -1) {
          arr[ny][nx]--;
          if (arr[ny][nx] == 0)
            q.push({nx, ny, 1});
        }
      }
    }
  }
  int ans = 0;
  while (!q.empty()) {
    int x = q.front().x;
    int y = q.front().y;
    int waveCnt = q.front().waveCnt;
    ans = waveCnt;
    q.pop();

    for (int d = 0; d < 8; d++) {
      int ny = y + dy[d];
      int nx = x + dx[d];
      if (0 <= ny && ny < N && 0 <= nx && nx < M) {
        arr[ny][nx]--;
        if (arr[ny][nx] == 0)
          q.push({nx, ny, waveCnt + 1});
      }
    }
  }
  cout << ans;
}
