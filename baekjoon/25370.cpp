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

int N;
int check[4782970];
int ans;
void f(int cur, int dep, int res) {
  if (dep == N) {
    check[res] = 1;
    return;
  }
  for (int i = cur; i <= 9; i++) {
    f(cur, dep + 1, res * i);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  f(1, 0, 1);
  for (int i = 0; i < 4782970; i++) {
    ans += check[i];
  }
  cout << ans;
  return 0;
}
