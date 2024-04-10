#include <algorithm>
#include <iostream>
#include <queue>

using namespace std;

typedef long long int ll;
int N, K;
int ans;
int parent[100001];
int groupSize[100001];
int map[2001][2001];
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

struct Node {
  int x, y, t, group;
};

queue<Node> q;

int find(int p) {
  if (p == parent[p])
    return p;
  parent[p] = find(parent[p]);
  groupSize[p] = groupSize[parent[p]];
  return parent[p];
}

void join(int a, int b) {
  int pA = parent[a];
  int pB = parent[b];
  if (pA == pB)
    return;
  groupSize[pA] += groupSize[pB];
  parent[pB] = pA;
}

void check(int x, int y, int group) {
  for (int i = 0; i < 4; i++) {
    int nx = x + dx[i];
    int ny = y + dy[i];
    if (1 <= nx && nx <= N && 1 <= ny && ny <= N && map[ny][nx]) {
      join(find(group), find(map[ny][nx]));
    }
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 1; i <= K; i++) {
    int x, y;
    cin >> x >> y;
    map[y][x] = i;
    parent[i] = i;
    groupSize[i] = 1;
    check(x, y, i);
    q.push({x, y, 1, find(i)});
  }
  if (groupSize[find(1)] >= K) {
    cout << 0;
    return 0;
  }
  while (true) {
    Node cur = q.front();
    q.pop();
    if (ans != cur.t) {
      if (groupSize[find(1)] >= K)
        break;
      ans = cur.t;
    }
    for (int i = 0; i < 4; i++) {
      int nx = cur.x + dx[i];
      int ny = cur.y + dy[i];
      if (1 <= nx && nx <= N && 1 <= ny && ny <= N && !map[ny][nx]) {
        map[ny][nx] = cur.group;
        q.push({nx, ny, cur.t + 1, find(cur.group)});
        check(nx, ny, find(cur.group));
      }
    }
  }
  cout << ans << "\n";
  return 0;
}