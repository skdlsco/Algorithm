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

int N;
int ans;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  int best;
  cin >> best;
  for (int i = 1; i < N; i++) {
    int cur;
    cin >> cur;
    ans = max(cur - best, ans);
    best = min(best, cur);
  }
  cout << ans;
}
