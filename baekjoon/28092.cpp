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

int N, Q;
map<int, priority_queue<int>> m;
int parent[100001];
int groupSize[100001];
int edgeCount[100001];

int getRoot(int node) {
  if (node == parent[node])
    return node;
  int root = getRoot(parent[node]);
  return root;
}

void join(int a, int b) {
  int aRoot = getRoot(a);
  int bRoot = getRoot(b);

  if (aRoot > bRoot)
    swap(aRoot, bRoot);
  edgeCount[aRoot] += edgeCount[bRoot] + 1;
  if (aRoot == bRoot)
    return;
  groupSize[aRoot] += groupSize[bRoot];
  parent[bRoot] = aRoot;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> Q;
  for (int i = 1; i <= N; i++) {
    m[1].push(-i);
    parent[i] = i;
    groupSize[i] = 1;
    edgeCount[i] = 0;
  }
  while (Q--) {
    int c;

    cin >> c;
    if (c == 1) {
      int u, v;
      cin >> u >> v;
      join(u, v);
      int node = getRoot(u);
      m[groupSize[node]].push(-node);
    } else {
      while (1) {
        int cnt = m.rbegin()->first;
        priority_queue<int> &pq = m.rbegin()->second;
        if (pq.empty()) {
          m.erase(cnt);
          continue;
        }
        int ans = 0;
        while (!pq.empty()) {
          int cur = -pq.top();
          pq.pop();
          int root = getRoot(cur);
          if (groupSize[root] - 1 == edgeCount[root] && cnt == groupSize[root]) {
            ans = root;
            break;
          }
        }
        if (ans) {
          cout << ans << "\n";
          break;
        }
      }
    }
  }
}