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

struct Edge {
  int s;
  int e;
  int v;
};

bool comp(Edge a, Edge b) {
  if (a.e == b.e)
    return a.s < b.s;
  return a.e < b.e;
}

int N, M, C;
vector<Edge> arr;
int truck[2001];
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> C >> M;
  for (int i = 0; i < M; i++) {
    int s, e, v;
    cin >> s >> e >> v;
    arr.push_back({s, e, v});
  }
  sort(arr.begin(), arr.end(), comp);
  for (Edge edge : arr) {
    int cur = edge.v;
    for (int i = edge.s; i < edge.e; i++) {
      cur = min(cur, C - truck[i]);
    }
    for (int i = edge.s; i < edge.e; i++) {
      truck[i] += cur;
    }
    ans += cur;
  }
  cout << ans;
}