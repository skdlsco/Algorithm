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

ll arr[1000001];
ll ans;
int N, bodyL, bodyR;

int main() {
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
    arr[i] += arr[i - 1];
  }
  bodyL = 1;
  bodyR = N;
  for (int i = 1; i <= N; i++) {
    while (bodyR > i && (arr[i] >= arr[N] - arr[bodyR]))
      bodyR--;
    while (bodyL < bodyR && (bodyL <= i || arr[bodyL] - arr[i] <= arr[N] - arr[bodyL]))
      bodyL++;
    if (arr[i] < arr[N] - arr[bodyR] && arr[bodyR] - arr[i] > arr[N] - arr[bodyR] &&
        arr[i] < arr[N] - arr[bodyL] && arr[bodyL] - arr[i] > arr[N] - arr[bodyL])
      ans += bodyR - bodyL + 1;
  }
  cout << ans << "\n";
}