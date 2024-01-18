#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int N;
vector<int> graph[51];

int solve(int cur) {
  vector<int> children;
  for (auto next : graph[cur]) {
    children.push_back(-solve(next));
  }
  sort(children.begin(), children.end());
  int t = 1;
  int ans = 0;
  for (auto child : children) {
    ans = max(t - child, ans);
    t++;
  }
  return ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  int p;
  for (int i = 0; i < N; i++) {
    cin >> p;
    if (p != -1)
      graph[p].push_back(i);
  }
  cout << solve(0);
  return 0;
}