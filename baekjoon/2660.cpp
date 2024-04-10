#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

typedef long long int ll;

int N;

vector<int> score[51];
int graph[51][51];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      graph[i][j] = 100;
    }
    graph[i][i] = 0;
  }
  while (true) {
    int u, v;
    cin >> u >> v;
    if (u == -1 && v == -1)
      break;
    graph[u][v] = 1;
    graph[v][u] = 1;
  }
  for (int m = 1; m <= N; m++) {
    for (int s = 1; s <= N; s++) {
      for (int e = 1; e <= N; e++) {
        if (graph[s][e] > graph[s][m] + graph[m][e]) {
          graph[s][e] = graph[s][m] + graph[m][e];
        }
      }
    }
  }
  for (int i = 1; i <= N; i++) {
    int s = 0;
    for (int j = 1; j <= N; j++) {
      if (i == j)
        continue;
      s = max(s, graph[i][j]);
    }
    score[s].push_back(i);
  }
  for (int i = 1; i <= N; i++) {
    if (!score[i].empty()) {
      cout << i << " " << score[i].size() << "\n";
      for (int m : score[i]) {
        cout << m << " ";
      }
      cout << "\n";
      break;
    }
  }
  return 0;
}
