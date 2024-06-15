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
int N, ans;
vector<int> v;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    int S;
    cin >> S;
    v.push_back(S);
  }
  sort(v.begin(), v.end());
  int start = 0;
  for (int s : v) {
    if (start == 0) {
      start = s;
      ans++;
    }
    if (s >= start * 2) {
      start = s;
      ans++;
    }
  }
  cout << ans;
  return 0;
}
