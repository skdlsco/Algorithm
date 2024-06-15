#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

double K, D1, D2;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> K >> D1 >> D2;

  if (D1 > D2)
    swap(D1, D2);
  double diff = (D2 - D1) / 2;
  cout << K * K - diff * diff;
  return 0;
}
