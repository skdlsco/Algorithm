#include <algorithm>
#include <iostream>

using namespace std;
typedef long long int ll;

int N;
int graph[101][101];
int visited[101];

void dfs(int cur, int prev) {
  visited[cur] = 1;
  int next = 0;
  cout << "maze" << endl;
  cin >> next;
  while (!visited[next]) {
    if (!visited[next]) {
      graph[cur][next] = 1;
      graph[next][cur] = 1;
      dfs(next, cur);
    }
    cout << "maze" << endl;
    cin >> next;
  }
  cout << "gaji"
       << " " << cur << endl;
  cin >> cur;
  if (cur == prev) return;
  cout << "gaji"
       << " " << prev << endl;
  cin >> cur;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dfs(1, 1);
  cout << "answer" << endl;
  for (int i = 1; i < N; i++) {
    for (int j = i + 1; j <= N; j++) {
      if (graph[i][j]) {
        cout << i << " " << j << endl;
      }
    }
  }
  return 0;
}
