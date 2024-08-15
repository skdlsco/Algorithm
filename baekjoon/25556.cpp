#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int arr[4];
int N;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int cur;
    cin >> cur;
    sort(arr, arr + 4);
    for (int j = 3; j >= 0; j--) {
      if (arr[j] < cur) {
        arr[j] = cur;
        cur = 0;
      }
    }
    if (cur != 0) {
      cout << "NO\n";
      return 0;
    }
  }
  cout << "YES\n";
}