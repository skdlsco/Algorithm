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
int arr[][3] = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1},
                {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
int T;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    int x = 0, y = 0, z = 0;
    for (int i = 0; i < 4; i++) {
      int n;
      cin >> n;
      x += arr[n][0];
      y += arr[n][1];
      z += arr[n][2];
    }
    if (x == 0 || x == 4 || y == 0 || y == 4 || z == 0 || z == 4)
      cout << "YES\n";
    else
      cout << "NO\n";
  }
  return 0;
}
