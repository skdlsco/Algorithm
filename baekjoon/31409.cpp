#include <algorithm>
#include <iostream>
using namespace std;

int N, cnt;
int arr[100001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
    if (arr[i] == i) {
      cnt++;
      arr[i] = (i == N) ? 1 : i + 1;
    }
  }
  cout << cnt << "\n";
  for (int i = 1; i <= N; i++) {
    cout << arr[i] << " ";
  }
  return 0;
}