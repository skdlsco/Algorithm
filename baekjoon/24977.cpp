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
typedef pair<ll, ll> pll;
int N;
int parent[100001];

ll sum;

struct Edge {
  int s, e;
  ll moo;

  bool operator()(const Edge &s1, const Edge &s2) { return s1.moo < s2.moo; }
};

priority_queue<Edge, vector<Edge>, Edge> pq;

int getParent(int node) {
  if (parent[node] == node)
    return node;
  return parent[node] = getParent(parent[node]);
}

void join(int u, int v) { parent[u] = v; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    Edge e;
    e.s = i;
    cin >> e.e >> e.moo;
    sum += e.moo;
    pq.push(e);
    parent[i] = i;
  }
  while (!pq.empty()) {
    Edge e = pq.top();
    pq.pop();
    int sP = getParent(e.s);
    int eP = getParent(e.e);
    if (sP == eP)
      sum -= e.moo;
    else {
      join(sP, eP);
    }
  }
  cout << sum;
  return 0;
}
