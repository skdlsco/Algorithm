#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

const int TREE_SIZE = 1 << 19;
vector<int> tree[TREE_SIZE];
int arr[100001];
int N, M;

void build(int node, int s, int e) {
  if (s == e) {
    tree[node].push_back(arr[s]);
    return;
  }
  int m = (s + e) / 2;
  build(node * 2, s, m);
  build(node * 2 + 1, m + 1, e);
  int mergeSize = tree[node * 2].size() + tree[node * 2 + 1].size();
  tree[node].resize(mergeSize);
  int i = 0, j = 0, k = 0;
  while (i < mergeSize) {
    if (k == tree[node * 2 + 1].size() ||
        j < tree[node * 2].size() &&
            tree[node * 2][j] < tree[node * 2 + 1][k]) {
      tree[node][i] = tree[node * 2][j++];
    } else {
      tree[node][i] = tree[node * 2 + 1][k++];
    }
    i++;
  }
}

int query(int k, int node, int s, int e, int l, int r) {
  if (e < l || s > r)
    return 0;
  if (l <= s && e <= r)
    return tree[node].end() -
           upper_bound(tree[node].begin(), tree[node].end(), k);
  int m = (s + e) / 2;
  return query(k, node * 2, s, m, l, r) +
         query(k, node * 2 + 1, m + 1, e, l, r);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }
  build(1, 1, N);
  cin >> M;
  int last_ans = 0;
  while (M--) {
    int a, b, c;
    cin >> a >> b >> c;
    int i = a ^ last_ans;
    int j = b ^ last_ans;
    int k = c ^ last_ans;
    int ans = query(k, 1, 1, N, i, j);
    cout << ans << "\n";
    last_ans = ans;
  }
  return 0;
}
