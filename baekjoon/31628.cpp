#include <algorithm>
#include <deque>
#include <iostream>
#include <string>

using namespace std;

typedef long long int ll;
int N = 10;
string arr[10][10];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < N; x++) {
      cin >> arr[y][x];
    }
  }
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < N - 1; x++) {
      if ((arr[y][x] != arr[y][x + 1]))
        break;
      if (x == N - 2) {
        cout << 1;
        return 0;
      }
    }
  }
  for (int x = 0; x < N; x++) {
    for (int y = 0; y < N - 1; y++) {
      if ((arr[y][x] != arr[y + 1][x]))
        break;
      if (y == N - 2) {
        cout << 1;
        return 0;
      }
    }
  }
  cout << 0;
  return 0;
}