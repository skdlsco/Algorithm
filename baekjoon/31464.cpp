#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;
typedef pair<int, int> pii;

int N, M;
int choco;
int map[50][50];
vector<pii> ans;
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
int visit[50][50];
int cycle;
int cnt;

void init() {
  cnt = 0;
  cycle = 0;
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      visit[y][x] = 0;
    }
  }
}

void dfs(int x, int y, int px, int py) {
  visit[y][x] = 1;
  cnt++;
  for (int d = 0; d <= 3; d++) {
    int nx = x + dx[d];
    int ny = y + dy[d];
    if (nx == px && ny == py)
      continue;
    if (nx < 1 || N < nx || ny < 1 || N < ny)
      continue;
    if (!map[ny][nx])
      continue;
    if (visit[ny][nx])
      cycle = 1;
    else
      dfs(nx, ny, x, y);
  }
}

bool check() {
  init();
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      if (map[y][x]) {
        dfs(x, y, -1, -1);
        return cnt == choco && !cycle;
      }
    }
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      char c;
      cin >> c;
      map[y][x] = c == '#';
      choco += c == '#';
    }
  }
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      if (map[y][x]) {
        choco--;
        map[y][x] = 0;
        if (check())
          ans.push_back({y, x});
        map[y][x] = 1;
        choco++;
      }
    }
  }
  cout << ans.size() << "\n";
  for (pii node : ans) {
    cout << node.first << " " << node.second << "\n";
  }
  return 0;
}