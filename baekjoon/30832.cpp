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

struct Command {
  int a, b, c;
};

int N;

vector<int> treeA[1001];
vector<int> treeB[1001];
vector<Command> ans;

void prepareTreeA(int cur, int prev) {
  if (prev != 1) {
    ans.push_back({cur, prev, 1});
  }
  for (int next : treeA[cur]) {
    if (next != prev)
      prepareTreeA(next, cur);
  }
}

void changeToB(int cur, int prev) {
  for (int next : treeB[cur]) {
    if (next != prev)
      changeToB(next, cur);
  }
  if (prev != 1) {
    ans.push_back({cur, 1, prev});
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N - 1; i++) {
    int u, v;
    cin >> u >> v;
    treeA[u].push_back(v);
    treeA[v].push_back(u);
  }
  for (int i = 0; i < N - 1; i++) {
    int u, v;
    cin >> u >> v;
    treeB[u].push_back(v);
    treeB[v].push_back(u);
  }
  prepareTreeA(1, 1);
  changeToB(1, 1);
  cout << ans.size() << "\n";
  for (Command c : ans) {
    cout << c.a << " " << c.b << " " << c.c << "\n";
  }
}
