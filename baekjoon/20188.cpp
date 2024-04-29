#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
vector<int> graph[300001];
ll ans;
int solve(int cur, int prev) {
  int cnt = 0;
  for (int next : graph[cur]) {
    if (next == prev)
      continue;
    ll children = solve(next, cur);
    cnt += children;
    ans += children * (N - children);
    if (children > 1) {
      ans += children * (children - 1) / 2;
    }
  }
  return cnt + 1;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i < N; i++) {
    int a, b;
    cin >> a >> b;
    graph[a].push_back(b);
    graph[b].push_back(a);
  }
  solve(1, 1);
  cout << ans;
  return 0;
}
