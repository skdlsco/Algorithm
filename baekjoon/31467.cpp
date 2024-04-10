#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <set>
#include <vector>
using namespace std;

typedef long long ll;
typedef pair<int, int> pii;

int N, M;
int choco;
int map[1001][1001];
vector<pii> ans;
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
int visit[1001][1001];
int visited[1212121];
int nodeNum = 1;
int edgeCount = 0;
vector<int> graph[1212121];
set<int> point;

int dfs(int cur, int isRoot) {
  int back = nodeNum;
  visited[cur] = nodeNum++;
  int childCnt = 0;
  for (auto next : graph[cur]) {
    if (visited[next] == -1) {
      int res = dfs(next, false);
      if (res >= visited[cur])
        point.insert(cur);
      back = min(back, res);
      childCnt++;
    } else {
      back = min(back, visited[next]);
    }
  }
  if (isRoot) {
    if (childCnt > 1)
      point.insert(cur);
    else
      point.erase(cur);
  }
  return back;
}

void init() {
  nodeNum = 1;
  point.clear();
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      if (!map[y][x])
        continue;
      if (y < N)
        edgeCount += map[y + 1][x];
      if (x < N)
        edgeCount += map[y][x + 1];
    }
  }
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      visited[y * N + x] = -1;
    }
  }
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      if (!map[y][x])
        continue;
      int here = y * N + x;
      for (int d = 0; d <= 3; d++) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (ny >= 1 && ny <= N && nx >= 1 && nx <= N && map[ny][nx]) {
          int there = ny * N + nx;
          graph[here].push_back(there);
        }
      }
    }
  }
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      if (!map[y][x])
        continue;
      int here = y * N + x;
      if (visited[here] == -1)
        dfs(here, 1);
    }
  }
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
  init();
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      if (!map[y][x] || point.find(y * N + x) != point.end())
        continue;
      int cnt = 0;
      for (int d = 0; d < 4; d++) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (ny >= 1 && ny <= N && nx >= 1 && nx <= N && map[ny][nx])
          cnt++;
      }
      if (choco - 2 == edgeCount - cnt)
        ans.push_back({y, x});
    }
  }
  cout << ans.size() << "\n";
  for (pii node : ans) {
    cout << node.first << " " << node.second << "\n";
  }
  return 0;
}