#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;

int isRed[100001];
ll redLength[100001];
vector<int> graph[100001];
int visited[100001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N - 1; i++) {
    int u, v;
    cin >> u >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);
  }
  string s;
  cin >> s;
  for (int i = 1; i <= N; i++) {
    isRed[i] = s[i - 1] == 'R';
  }
  for (int i = 1; i <= N; i++) {
    if (visited[i] || !isRed[i])
      continue;
    vector<int> group;
    queue<int> q;
    q.push(i);
    visited[i] = 1;
    while (!q.empty()) {
      int cur = q.front();
      q.pop();
      group.push_back(cur);
      for (int next : graph[cur]) {
        if (visited[next] || !isRed[next])
          continue;
        visited[next] = 1;
        q.push(next);
      }
    }
    for (int item : group) {
      redLength[item] = group.size();
    }
  }
  ll ans = 0;
  for (int i = 1; i <= N; i++) {
    if (isRed[i])
      continue;
    for (int next : graph[i]) {
      if (isRed[next])
        ans += redLength[next];
    }
  }
  cout << ans;
}
