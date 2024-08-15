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

string S, P;

int check[255];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> S >> P;
  for (char c : S) {
    check[c]++;
  }
  for (char c : P) {
    check[c]--;
    if (check[c] < 0) {
      cout << "NO";
      return 0;
    }
  }
  cout << "YES";
}