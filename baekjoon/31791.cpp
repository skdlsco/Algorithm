#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

struct Data {
  int t;
  int x;
  int y;

  bool operator()(Data a, Data b) {
    return a.t > b.t;
  }
};

bool cmp(Data a, Data b) {
  if (a.y == b.y)
    return a.x < b.x;
  return a.y < b.y;
}

int N, M;
int endTime;
int structTime;
int X, B;
int visited[2001][2001];
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
char city[2001][2001];
priority_queue<Data, vector<Data>, Data> pq;
vector<Data> ans;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  cin >> endTime >> structTime >> X >> B;

  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      visited[y][x] = 123456789;
      cin >> city[y][x];
      if (city[y][x] == '*') {
        visited[y][x] = 0;
        pq.push({0, x, y});
      }
    }
  }
  while (!pq.empty()) {
    int t = pq.top().t;
    int x = pq.top().x;
    int y = pq.top().y;
    pq.pop();
    if (t > visited[y][x])
      continue;
    for (int d = 0; d < 4; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];
      if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
        int nt = t + 1;
        if (city[ny][nx] == '#')
          nt += structTime;
        if (visited[ny][nx] > nt) {
          pq.push({nt, nx, ny});
          visited[ny][nx] = nt;
        }
      }
    }
  }
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      if (visited[y][x] > endTime)
        ans.push_back({0, x, y});
    }
  }
  sort(ans.begin(), ans.end(), cmp);
  for (Data &d : ans) {
    cout << d.y + 1 << " " << d.x + 1 << "\n";
  }
  if (ans.empty())
    cout << -1;
}