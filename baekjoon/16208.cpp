#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
ll ans;
ll sum;
ll arr[500001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    sum += arr[i];
  }
  sort(arr, arr + N);
  for (int i = 0; i < N - 1; i++) {
    sum -= arr[i];
    ans += sum * arr[i];
  }
  cout << ans;
  return 0;
}
