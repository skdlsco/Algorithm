#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<ll, ll> pll;
int N;
struct Node {
  int cnt;
  ll sum;
};

struct Edge {
  int next;
  ll w;
};

Node arr[10001];
vector<Edge> graph[10001];
ll ans;

void init(int cur, int prev) {
  int cnt = 1;
  ll sum = 0;
  for (Edge &edge : graph[cur]) {
    if (edge.next == prev) continue;
    init(edge.next, cur);
    cnt += arr[edge.next].cnt;
    sum += arr[edge.next].sum + arr[edge.next].cnt * edge.w;
  }
  arr[cur] = {cnt, sum};
}

void run(int cur, int prev, ll sum) {
  ll curSum = sum + arr[cur].sum;
  ans = min(ans, curSum);
  for (Edge &edge : graph[cur]) {
    if (edge.next == prev) continue;
    ll nextSum = curSum + edge.w * (N - arr[edge.next].cnt) -
                 arr[edge.next].sum - arr[edge.next].cnt * edge.w;
    run(edge.next, cur, nextSum);
  }
}

void solve() {
  ans = 1e13;
  for (int i = 0; i < N; i++) {
    arr[i].cnt = 0;
    arr[i].sum = 0;
    graph[i].clear();
  }
  for (int i = 0; i < N - 1; i++) {
    int a, b, w;
    cin >> a >> b >> w;
    graph[a].push_back({b, w});
    graph[b].push_back({a, w});
  }
  init(0, 0);
  run(0, 0, 0);
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  while (true) {
    cin >> N;
    if (!N) break;
    solve();
  }

  return 0;
}
