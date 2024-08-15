#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;

struct SegTree {

  ll tree[300000];
  ll lazy[300000];

  SegTree() {
    fill(tree, tree + 300000, 0);
    fill(lazy, lazy + 300000, 0);
  };

  void propagation(int node, int s, int e) {
    tree[node] += lazy[node] * (e - s + 1);
    if (s != e) {
      lazy[node * 2] += lazy[node];
      lazy[node * 2 + 1] += lazy[node];
    }
    lazy[node] = 0;
  }

  void update(int node, int s, int e, int l, int r, ll v) {
    propagation(node, s, e);
    if (l > e || r < s)
      return;
    if (l <= s && e <= r) {
      lazy[node] += v;
      propagation(node, s, e);
      return;
    }
    update(node * 2, s, (s + e) / 2, l, r, v);
    update(node * 2 + 1, (s + e) / 2 + 1, e, l, r, v);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }
  ll query(int node, int s, int e, int l, int r) {
    propagation(node, s, e);
    if (l > e || r < s)
      return 0;
    if (l <= s && e <= r)
      return tree[node];
    return query(node * 2, s, (s + e) / 2, l, r) + query(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
  }
};

ll tea[100002];
ll milk[100002];
SegTree teaTree;
SegTree milkTree;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;

  teaTree.update(1, 0, N, 0, N - 1, 1);
  milkTree.update(1, 0, N, 0, N - 1, 1);
  for (int i = 1; i < N; i++) {
    char c;
    cin >> c;
    if (c == 'H')
      teaTree.update(1, 0, N, i, N - 1, 1);
    else
      milkTree.update(1, 0, N, i, N - 1, 1);
  }

  for (int i = 0; i <= N; i++) {
    ll curTea = teaTree.query(1, 0, N, i, i);
    tea[i] += curTea;
    tea[i + 1] += tea[i] / 2;
    tea[i] %= 2;

    ll curMilk = milkTree.query(1, 0, N, i, i);
    milk[i] += curMilk;
    milk[i + 1] += milk[i] / 2;
    milk[i] %= 2;
  }
    
  for (int i = N + 1; i >= 0; i--) {

    if (tea[i] > milk[i]) {
      cout << "H";
      return 0;
    } else if (milk[i] > tea[i]) {
      cout << "M";
      return 0;
    }
  }
  cout << "HM";
}