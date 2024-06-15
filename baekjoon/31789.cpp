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

int N;
int X, S;
int atp;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> X >> S;
  for (int i = 0; i < N; i++) {
    int power, cost;
    cin >> cost >> power;
    if (cost <= X)
      atp = max(atp, power);
  }
  cout << ((atp > S) ? "YES" : "NO");
  return 0;
}
