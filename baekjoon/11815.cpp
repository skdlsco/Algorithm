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

int N;
ll num;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    cin >> num;
    ll temp = sqrtl(num);
    if (temp * temp == num)
      cout << "1 ";
    else
      cout << "0 ";
  }
  return 0;
}
