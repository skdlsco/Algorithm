#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int N;
ll arr[200001];
ll M, sum = 0;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
    sum += arr[i];
  }
  while (sum > 0 && M > 0) {
    M %= sum;
    for (int i = 1; i <= N; i++) {
      if (M < arr[i]) {
        sum -= arr[i];
        arr[i] = 0;
      } else {
        M -= arr[i];
      }
    }
  }
  cout << M;
  return 0;
}