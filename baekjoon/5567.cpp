#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N, M;
vector<int> graph[501];
int visited[501];

void dfs(int cur, int depth) {
  visited[cur] = 1;
  if (depth == 2)
    return;
  for (auto next : graph[cur]) {
    dfs(next, depth + 1);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  while (M--) {
    int A, B;
    cin >> A >> B;
    graph[A].push_back(B);
    graph[B].push_back(A);
  }
  dfs(1, 0);
  int ans = 0;
  for (int i = 2; i <= N; i++) {
    ans += visited[i];
  }
  cout << ans;

  return 0;
}