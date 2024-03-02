#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
typedef long long ll;

int N, horizontal, vertical;

string arr[100];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }

  for (int y = 0; y < N; y++) {
    int len = 0;
    for (int x = 0; x < N; x++) {
      if (arr[y][x] == '.') {
        len++;
      } else {
        if (len >= 2)
          horizontal++;
        len = 0;
      }
    }
    if (len >= 2)
      horizontal++;
  }
  for (int x = 0; x < N; x++) {
    int len = 0;
    for (int y = 0; y < N; y++) {
      if (arr[y][x] == '.') {
        len++;
      } else {
        if (len >= 2)
          vertical++;
        len = 0;
      }
    }
    if (len >= 2)
      vertical++;
  }
  cout << horizontal << " " << vertical;
  return 0;
}
