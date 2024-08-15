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

int N, M;

vector<int> graph[100001];
vector<int> depth[100001];

int visited[100001];
int path[100001];
int invalid;
int first = 0;

pii getLen(int cur, int prev, int dist) {
  pii ans = {cur, dist};
  visited[cur] = 1;
  for (int i = 0; i < graph[cur].size(); i++) {
    int next = graph[cur][i];
    if (first && visited[next] && next != prev) {
      invalid = 1;
      return ans;
    }
    if (next != prev) {
      pii temp = getLen(next, cur, dist + 1);
      if (!first)
        depth[cur].push_back(temp.second - dist);
      if (ans.second < temp.second) {
        if (!first)
          path[cur] = next;
        ans = temp;
      }
    } else if (!first) {
      depth[cur].push_back(0);
    }
  }
  return ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  while (M--) {
    int u, v;
    cin >> u >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);
  }
  for (int i = 1; i <= N; i++) {
    path[i] = i;
  }

  for (int i = 1; i <= N; i++) {
    if (visited[i])
      continue;
    first = 1;
    pii temp = getLen(i, i, 0);
    int start = temp.first;
    first = 0;
    if (invalid)
      break;
    temp = getLen(start, start, 0);
    int end = temp.first;
    int len = temp.second;
    int cur = start;
    int prev = cur;
    while (1) {
      for (int i = 0; i < graph[cur].size(); i++) {
        int next = graph[cur][i];
        if (next == prev || path[cur] == next)
          continue;
        if (depth[cur][i] > 1) {
          invalid = 1;
        }
      }

      if (graph[cur].size() > 4)
        invalid = 1;
      else if (graph[cur].size() == 3) {
        if (prev == start || path[cur] == end)
          len--;
        else
          invalid = 1;
      }
      if (cur == end)
        break;
      prev = cur;
      cur = path[cur];
    }
    if (len < 3)
      invalid = 1;
    if (invalid)
      break;
  }
  if (invalid)
    cout << "NIE";
  else
    cout << "TAK";
}