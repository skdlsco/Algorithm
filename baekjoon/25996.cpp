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
 
ll n;
long double x;
ll arr[100001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> n >> x;
 
  ll sum = 0;
  for (int i = 0; i < n; i++) {
    cin >> arr[i];
    sum += arr[i] * arr[i];
  }
  if (sum == 0) {
    for (int i = 0; i < n; i++) {
      cout << arr[i] << " ";
    }
    return 0;
  }
  x *= n;
  if (x != 0) {
    x /= sum;
    x = sqrt(x);
  }
 
  cout.precision(10);
  cout << fixed;
  for (int i = 0; i < n; i++) {
    cout << arr[i] * x << " ";
  }
}