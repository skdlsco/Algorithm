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

int N;
vector<int> arr;
ll ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int temp;
    cin >> temp;
    arr.push_back(temp);
  }
  sort(arr.rbegin(), arr.rend());
  if (N == 1)
    ans = arr[0];
  else if (N == 2) {
    ans = arr[0] + arr[1] - 1;
  } else {
    int i = 0;
    ans += arr[i];
    i += (N + 2) / 3;
    ans += arr[i];
    i += (N + 1) / 3;
    ans += arr[i];
    ans -= 3;
  }
  cout << ans;
}