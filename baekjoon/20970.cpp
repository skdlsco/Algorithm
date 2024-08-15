#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N, K;
int arr[200001];
vector<int> graph[200001];
int ans[200001];
int visited[200001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 1; i <= N; i++) {
    arr[i] = i;
  }
  for (int i = 0; i < K; i++) {
    int u, v;
    cin >> u >> v;

    // 현재 u에 위치한 소가 v칸에 갈 수 있다.
    graph[arr[u]].push_back(v);
    graph[arr[v]].push_back(u);
    swap(arr[u], arr[v]);
  }
  for (int i = 1; i <= N; i++) {
    if (ans[i])
      continue;
    vector<int> group;
    set<int> s;
    int cur = i;
    while (1) {
      s.insert(cur);
      group.push_back(cur);
      for (int able : graph[cur]) {
        s.insert(able);
      }
      if (arr[cur] == i)
        break;
      cur = arr[cur];
    }
    for (int item : group) {
      ans[item] = s.size();
    }
  }

  for (int i = 1; i <= N; i++) {
    cout << ans[i] << "\n";
  }
}