#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
int N, M;
vector<int> graph[100001];
int arr[100001];

void dfs(int cur) {
  for (auto next : graph[cur]) {
    arr[next] += arr[cur];
    dfs(next);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  int p;
  for (int i = 1; i <= N; i++) {
    cin >> p;
    if (p != -1)
      graph[p].push_back(i);
  }
  int idx, w;
  for (int i = 1; i <= M; i++) {
    cin >> idx >> w;
    arr[idx] += w;
  }
  dfs(1);
  for (int i = 1; i <= N; i++) {
    cout << arr[i] << " ";
  }
  return 0;
}