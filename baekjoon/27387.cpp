#include <algorithm>
#include <iostream>

using namespace std;

int N, M;
int arr[101];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    arr[i] = i;
  }
  while (M--) {
    int a;
    cin >> a;
    swap(arr[a], arr[a + 1]);
  }
  for (int i = 1; i <= N; i++) {
    cout << arr[i] << "\n";
  }
  return 0;
}