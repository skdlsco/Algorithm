#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
ll ans;
ll tree[1 << 21];

ll dfs(int depth, int node) {
  if (depth == N) {
    return tree[node];
  }

  ll left = dfs(depth + 1, node * 2);
  ll right = dfs(depth + 1, node * 2 + 1);
  tree[node] += max(left, right);
  ans += max(left, right) - min(left, right);
  return (tree[node]);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  tree[1] = 0;
  for (int i = 2; i < 2 << N; i++) {
    cin >> tree[i];
    ans += tree[i];
  }
  dfs(0, 1);
  cout << ans;
  return 0;
}
