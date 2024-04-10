#include <algorithm>
#include <iostream>

using namespace std;
typedef long long int ll;

int N, M;
int map[501][501];
int cnt;
int ans;
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
int visited[501][501];

int dfs(int y, int x) {
  int sum = 1;
  for (int i = 0; i < 4; i++) {
    int ny = y + dy[i];
    int nx = x + dx[i];

    if (0 <= ny && ny < N && 0 <= nx && nx < M && !visited[ny][nx] &&
        map[ny][nx]) {
      visited[ny][nx] = 1;
      sum += dfs(ny, nx);
    }
  }
  return sum;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      cin >> map[y][x];
    }
  }

  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      if (!visited[y][x] && map[y][x]) {
        cnt++;
        visited[y][x] = 1;
        ans = max(ans, dfs(y, x));
      }
    }
  }
  cout << cnt << "\n" << ans << "\n";
  return 0;
}
