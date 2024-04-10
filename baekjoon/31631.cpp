#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;
int N, M;
int arr[5001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  M = N;
  int left = 0;
  int right = N - 1;
  arr[right--] = N--;
  while (N) {
    if (N)
      arr[left++] = N--;
    if (N)
      arr[left++] = N--;
    if (N)
      arr[right--] = N--;
    if (N)
      arr[right--] = N--;
  }
  for (int i = 0; i < M; i++) {
    cout << arr[i] << " ";
  }
  return 0;
}