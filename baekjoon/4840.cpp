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

string s;
int nodeNum;
vector<int> graph[10000];
int startHeight;
int curHeight;
int ans;
int TC = 1;
void f(int cur, int depth) {
  ans = max(ans, depth);
  for (int next : graph[cur]) {
    depth++;
    f(next, depth);
  }
}

void solve() {
  stack<int> st;
  st.push(0);
  ans = 0;
  nodeNum = 1;
  curHeight = 0;
  startHeight = 0;
  graph[0].clear();
  for (char c : s) {
    if (c == 'd') {
      curHeight++;
      startHeight = max(curHeight, startHeight);
      graph[nodeNum].clear();
      graph[st.top()].push_back(nodeNum);
      st.push(nodeNum);
      nodeNum++;
    } else {
      curHeight--;
      st.pop();
    }
  }
  f(0, 0);
  cout << "Tree " << TC << ": " << startHeight << " => " << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  while (1) {
    cin >> s;
    if (s == "#")
      break;
    solve();
    TC++;
  }
}
