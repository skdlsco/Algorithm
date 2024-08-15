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
int N, nstar, nbang;
char result[11];
int TC = 1;
void f(int depth, char prev, int len) {
  if (depth == N) {
    for (int i = 0; i < N; i++) {
      cout << result[i];
    }
    cout << "\n";
    return;
  }
  if ((prev != '*' || len < nstar) && nstar > 0) {
    result[depth] = '*';
    f(depth + 1, '*', prev == '!' ? 1 : len + 1);
  }
  if ((prev != '!' || len < nbang) && nbang > 0) {
    result[depth] = '!';
    f(depth + 1, '!', prev == '*' ? 1 : len + 1);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  while (1) {
    cin >> N >> nstar >> nbang;
    if (N == 0 && nstar == 0 && nbang == 0)
      break;
    cout << "Sequence set " << TC << "\n";
    f(0, 0, 0);
    TC++;
  }
}