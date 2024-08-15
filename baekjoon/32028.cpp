#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int MIN_V = -2000000001;
int MAX_V = 2000000001;

struct Node {
  int id;
  int v;
  int l;
  int r;
  int lId;
  int rId;
};
vector<Node> tree[200001];
pii ans[200001];
int N;

bool comp(const Node &a, const Node &b) {
  return a.v < b.v;
}

// 깊이, 정수, 오름차순
// 깊이 1인것을 루트로 넣어둔다.
// 깊이별 vector를 만들어둔다.
// 각 노드는 좌, 우에 값을 넣을 수 있다.(그리고 넣을 수 있는 min, max를 지정해준다.)
//
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    int v, d;
    cin >> v >> d;
    ans[i] = {-1, -1};
    tree[d].push_back({i,
                       v,
                       MIN_V, MAX_V,
                       -1, -1});
  }
  for (int i = 1; i <= N; i++) {
    sort(tree[i].begin(), tree[i].end(), comp);
  }
  int valid = 1;
  for (int d = 2; d <= N; d++) {
    int pIdx = 0;
    for (int i = 0; i < tree[d].size(); i++) {
      Node &cur = tree[d][i];
      while (valid && pIdx < tree[d - 1].size()) {
        Node &p = tree[d - 1][pIdx];
        if (p.l <= cur.v && cur.v <= p.r) {
          if (p.v < cur.v && p.rId == -1) {
            p.rId = cur.id;
            ans[p.id].second = cur.id;
            cur.l = p.v;
            cur.r = p.r;
            break;
          } else if (cur.v < p.v && p.lId == -1) {
            p.lId = cur.id;
            ans[p.id].first = cur.id;
            cur.l = p.l;
            cur.r = p.v;
            break;
          }
        }
        pIdx++;
      }
      if (pIdx == tree[d - 1].size())
        valid = 0;
    }
    if (!valid)
      break;
  }
  if (!valid) {
    cout << -1;
    return 0;
  }
  for (int i = 1; i <= N; i++) {
    cout << ans[i].first << " " << ans[i].second << "\n";
  }
  return 0;
}
