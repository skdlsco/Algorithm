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

int T;
int N, S, E;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> N >> S >> E;
    if (N > 2 && abs(S - E) == 1)
      cout << 1 << "\n";
    else if (S == 1 || S == N) {
      if (E == 1 || E == N)
        cout << 0 << "\n";
      else
        cout << 1 << "\n";
    } else
      cout << 2 << "\n";
  }
  return 0;
}
