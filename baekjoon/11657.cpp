#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;

struct Edge {
  int s;
  int e;
  ll c;
};

const ll INF = 30000000001;
int N, M;

Edge edges[6001];

ll dist[501];
int hasCycle;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    dist[i] = INF;
  }
  for (int i = 1; i <= M; i++) {
    cin >> edges[i].s >> edges[i].e >> edges[i].c;
  }
  dist[1] = 0;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= M; j++) {
      int s = edges[j].s;
      int e = edges[j].e;
      int c = edges[j].c;
      if (dist[s] != INF && dist[s] + c < dist[e]) {
        if (i == N)
          hasCycle = 1;
        dist[e] = dist[s] + c;
      }
    }
  }
  if (hasCycle) {
    cout << "-1\n";
  } else {
    for (int i = 2; i <= N; i++) {
      if (dist[i] == INF)
        cout << "-1\n";
      else
        cout << dist[i] << "\n";
    }
  }
  return 0;
}
