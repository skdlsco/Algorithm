#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;
struct Edge {
  int node;
  ll cost;
};
int N;
vector<Edge> graph[100001];

ll dfs(int prev, int cur, ll cost) {
  ll sum = 0;
  for (auto e : graph[cur]) {
    if (e.node == prev)
      continue;
    sum += dfs(cur, e.node, e.cost);
  }
  if (sum == 0)
    sum = 2e9;
  return min(cost, sum);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i < N; i++) {
    int A, B, V;
    cin >> A >> B >> V;
    graph[A].push_back({B, V});
    graph[B].push_back({A, V});
  }
  cout << dfs(1, 1, 2e9) << "\n";
  return 0;
}