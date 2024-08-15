#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

ll N, M, C;

ll dp[100001];
vector<pll> graph[100001];
int ind[100001];
queue<int> q;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> C;
  for (int i = 1; i <= N; i++) {
    cin >> dp[i];
  }
  for (int i = 1; i <= C; i++) {
    int a, b;
    ll x;
    cin >> a >> b >> x;
    graph[a].push_back({b, x});
    ind[b]++;
  }
  for (int i = 1; i <= N; i++) {
    if (!ind[i])
      q.push(i);
  }
  while (!q.empty()) {
    int cur = q.front();
    q.pop();
    for (pll item : graph[cur]) {
      int next = item.first;
      ll cost = item.second;
      dp[next] = max(dp[next], dp[cur] + cost);
      ind[next]--;
      if (!ind[next])
        q.push(next);
    }
  }
  for (int i = 1; i <= N; i++) {
    cout << dp[i] << "\n";
  }
  return 0;
}