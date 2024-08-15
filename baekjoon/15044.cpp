#include <algorithm>
#include <cmath>
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
typedef pair<ll, ll> pll;

int N, K;
int arr[1001];
int ans = 0;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 0; i < N; i++) {
    int temp;
    cin >> temp;
    arr[temp]++;
  }
  for (int i = 1000; i >= 1; i--) {
    if (K <= 0)
      break;
    ans += arr[i];
    K -= arr[i];
  }
  cout << ans;
}