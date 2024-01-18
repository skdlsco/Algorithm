#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int N, M, p, a, x;
char c;

vector<int> graph[500001];
int arr[500001];
pair<int, int> rangeMap[500001];
int map[500001];
int rev[500001];
ll tree[1048577];

int num = 1;

int ott(int cur) {
  int start = num++;
  map[cur] = start;
  rev[start] = cur;
  int end = start;
  for (auto next : graph[cur]) {
    end = ott(next);
  }
  rangeMap[cur] = {start, end};
  return end;
}

void update(int node, int s, int e, int l, int r, ll v) {
  if (e < l || r < s)
    return;
  if (l <= s && e <= r) {
    tree[node] += v;
  } else {
    update(node * 2, s, (s + e) / 2, l, r, v);
    update(node * 2 + 1, (s + e) / 2 + 1, e, l, r, v);
  }
}

ll query(int node, int s, int e, int idx) {
  if (e < idx || idx < s)
    return 0L;
  if (s == e) {
    arr[rev[s]] += tree[node];
    tree[node] = 0L;
    return arr[rev[s]];
  }
  tree[node * 2] += tree[node];
  tree[node * 2 + 1] += tree[node];
  tree[node] = 0L;
  return query(node * 2, s, (s + e) / 2, idx) +
         query(node * 2 + 1, (s + e) / 2 + 1, e, idx);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  cin >> arr[1];
  for (int i = 2; i <= N; i++) {
    cin >> arr[i];
    cin >> p;
    graph[p].push_back(i);
  }
  ott(1);
  while (M--) {
    cin >> c;
    if (c == 'u') {
      cin >> a;
      cout << query(1, 1, N, map[a]) << "\n";
    } else {
      cin >> a >> x;
      arr[a] -= x;
      update(1, 1, N, rangeMap[a].first, rangeMap[a].second, x);
    }
  }
}