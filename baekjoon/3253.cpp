#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

priority_queue<pii> pq;

int N, A, B;

vector<int> graph[101];
int visited[101];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> A >> B;
  for (int i = 1; i <= N; i++) {
    int M;
    cin >> M;
    while (M--) {
      int v;
      cin >> v;
      graph[i].push_back(v);
    }
  }
  fill(visited, visited + N + 1, 123456789);
  pq.push({0, A});
  visited[A] = 0;
  while (!pq.empty()) {
    int cur = pq.top().second;
    int dist = -pq.top().first;
    pq.pop();
    for (int i = 0; i < graph[cur].size(); i++) {
      int next = graph[cur][i];
      int nextDist = dist + !!i;
      if (visited[next] > nextDist) {
        visited[next] = nextDist;
        pq.push({-nextDist, next});
      }
    }
  }
  if (visited[B] == 123456789)
    cout << -1;
  else
    cout << visited[B];
}