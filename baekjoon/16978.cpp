#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

typedef long long ll;
int N, M;

struct getQ {
  int idx;
  int count;
  int l;
  int r;
  ll result;
};

struct updateQ {
  int idx;
  int v;
};

ll tree[300001];
vector<getQ> queryList;
vector<updateQ> updateList;

void update(int node, int s, int e, int idx, int v) {
  if (idx < s || e < idx)
    return;
  if (s == e) {
    tree[node] = v;
  } else {
    update(node * 2, s, (s + e) / 2, idx, v);
    update(node * 2 + 1, (s + e) / 2 + 1, e, idx, v);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }
}

ll query(int node, int s, int e, int l, int r) {
  if (r < s || e < l)
    return 0;
  if (l <= s && e <= r)
    return tree[node];
  return query(node * 2, s, (s + e) / 2, l, r) +
         query(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    int num;
    cin >> num;
    update(1, 1, N, i, num);
  }
  cin >> M;
  int idx = 0;
  while (M--) {
    int command;
    cin >> command;
    if (command == 1) {
      int i, v;
      cin >> i >> v;
      updateList.push_back({i, v});
    } else {
      int count, l, r;
      cin >> count >> l >> r;
      queryList.push_back({idx++, count, l, r, -1});
    }
  }
  sort(queryList.begin(), queryList.end(),
       [](getQ &first, getQ &second) -> bool {
         return first.count < second.count;
       });
  idx = 0;
  for (getQ &item : queryList) {
    while (idx < item.count) {
      updateQ &data = updateList[idx];
      update(1, 1, N, data.idx, data.v);
      idx++;
    }
    item.result = query(1, 1, N, item.l, item.r);
  }
  sort(
      queryList.begin(), queryList.end(),
      [](getQ &first, getQ &second) -> bool { return first.idx < second.idx; });

  for (getQ &item : queryList) {
    cout << item.result << "\n";
  }
  return 0;
}