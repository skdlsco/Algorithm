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
typedef pair<int, int> pii;
int N, M;
vector<pii> v;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= M; i++) {
    int A, B;
    cin >> A >> B;
    v.push_back({A, i});
    v.push_back({B, i});
  }
  sort(v.begin(), v.end());
  cout << v[(N - 1) % v.size()].second;
  return 0;
}
