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
typedef pair<ll, ll> pll;

int T;
string A, B;
ll arrA[100001];
ll arrB[100001];

ll ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> A >> B;
    ans = 0;
    int len = A.length();
    int idx = 0;
    for (int i = 0; i < len; i++) {
      if (A[i] == 'b')
        arrA[idx++] = i;
    }
    idx = 0;
    for (int i = 0; i < len; i++) {
      if (B[i] == 'b')
        arrB[idx++] = i;
    }
    for (int i = 0; i < idx; i++) {
      ans += abs(arrA[i] - arrB[i]);
    }
    cout << ans << "\n";
  }
  return 0;
}
