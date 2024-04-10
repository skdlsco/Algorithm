#include <algorithm>
#include <iostream>
#include <queue>
#include <string>
using namespace std;

typedef long long int ll;

struct Node {
  int x, y, cost;

  bool operator<(const Node other) const { return cost > other.cost; }
};

int INF = 123456789;
int N;
int input[125][125];
int map[125][125];
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

priority_queue<Node> pq;

int solve() {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cin >> input[i][j];
      map[i][j] = INF;
    }
  }
  pq.push({0, 0, input[0][0]});
  while (!pq.empty()) {
    Node node = pq.top();
    pq.pop();
    if (node.cost > map[node.y][node.x])
      continue;
    for (int i = 0; i < 4; i++) {
      int nx = node.x + dx[i];
      int ny = node.y + dy[i];
      if (0 <= nx && nx < N && 0 <= ny && ny < N) {
        int nextCost = input[ny][nx] + node.cost;
        if (map[ny][nx] > nextCost) {
          map[ny][nx] = nextCost;
          pq.push({nx, ny, nextCost});
        }
      }
    }
  }
  return map[N - 1][N - 1];
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  int i = 1;
  while (true) {
    cin >> N;
    if (!N)
      break;
    cout << "Problem " << i << ": " << solve() << "\n";
    i++;
  }
  return 0;
}
