#include <algorithm>
#include <iostream>
using namespace std;

int N, ans, game;
int arr[1001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    game ^= arr[i];
  }

  for (int i = 0; i < N; i++) {
    for (int j = 1; j <= arr[i]; j++) {
      if (!(game ^ arr[i] ^ (arr[i] - j)))
        ans++;
    }
  }
  cout << ans;
  return 0;
}