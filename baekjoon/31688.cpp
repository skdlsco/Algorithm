#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int T;
int N;
vector<int> result;

void solve() {
  cin >> N;
  result.clear();
  for (int y = 0; y < N; y++) {
    vector<int> temp;
    for (int x = 0; x < N; x++) {
      int num;
      cin >> num;
      temp.push_back(num);
    }
    if (y % 2)
      reverse(temp.begin(), temp.end());
    result.insert(result.end(), temp.begin(), temp.end());
  }
  int prev = 0;
  int up = -1;
  int down = 0;
  for (int cur : result) {
    if (prev < cur)
      up++;
    else
      down++;
    prev = cur;
  }
  if (up > down)
    reverse(result.begin(), result.end());
  for (int cur : result) {
    cout << cur << " ";
  }
  cout << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}
