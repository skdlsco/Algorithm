#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int N;

vector<int> graph[200001];

ll firework[200001];
ll fireworkSum[200001];
ll fireworkTotal;
ll ans;

ll init(int cur, int prev) {
  fireworkSum[cur] += firework[cur];
  ll beautifulSum = 0;
  for (int next : graph[cur]) {
    if (next == prev)
      continue;
    beautifulSum += init(next, cur) + fireworkSum[next];
    fireworkSum[cur] += fireworkSum[next];
  }
  return beautifulSum;
}

ll dfs(ll bf, int cur, int prev) {
  ll ans = bf;
  for (int next : graph[cur]) {
    if (next == prev)
      continue;
    ll nextBf = bf - fireworkSum[next] + (fireworkTotal - fireworkSum[next]);
    ans = max(ans, dfs(nextBf, next, cur));
  }
  return ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N - 1; i++) {
    int a, b;
    cin >> a >> b;
    graph[a].push_back(b);
    graph[b].push_back(a);
  }
  for (int i = 1; i <= N; i++) {
    cin >> firework[i];
    fireworkTotal += firework[i];
  }
  ll startBeautiful = init(1, 0);
  cout << dfs(startBeautiful, 1, 0);
  return 0;
}
