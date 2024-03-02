#include <algorithm>
#include <cstring>
#include <iostream>
#include <stack>
#include <vector>

using namespace std;
typedef long long ll;

int N, Q;

ll arr[100001];
ll tree[300001];
ll tree2[300001];

void update(int node, int s, int e, int l, int r) {
  if (r < s || e < l)
    return;
  if (l <= s && e <= r) {
    tree2[node]++;
    tree[node] += 1 + s - l;
    return;
  }
  update(node * 2, s, (s + e) / 2, l, r);
  update(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
}

void query(int node, int s, int e, int x) {
  if (x < s || e < x)
    return;
  if (s == e) {
    arr[x] += tree[node];
    tree[node] = 0;
    tree2[node] = 0;
    return;
  }
  if (tree[node]) {
    tree[node * 2] += tree[node];
    tree[node * 2 + 1] += tree[node] + (((s + e) / 2 + 1) - s) * tree2[node];
    tree2[node * 2] += tree2[node];
    tree2[node * 2 + 1] += tree2[node];
    tree[node] = 0;
    tree2[node] = 0;
  }
  query(node * 2, s, (s + e) / 2, x);
  query(node * 2 + 1, (s + e) / 2 + 1, e, x);
  return;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }
  cin >> Q;
  while (Q--) {
    int a, b, c;
    cin >> a;
    if (a == 1) {
      cin >> b >> c;
      update(1, 1, N, b, c);
    } else {
      cin >> b;
      query(1, 1, N, b);
      cout << arr[b] << "\n";
    }
  }
}
