#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

int N, M;
int map[5003][5003];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j <= i; j++) {
      char c;
      cin >> c;
      map[i][j] = (c == 'R') ? 1 : 2;
    }
  }

  for (int i = 0; i < N; i++) {
    for (int j = 0; j <= i; j++) {
      if (map[i][j] == 1) {
        if (map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
          map[i][j] = 0;
          map[i + 1][j] = 0;
          map[i + 1][j + 1] = 0;
        }
      } else if (map[i][j] == 2) {
        if (map[i][j + 1] == 2 && map[i + 1][j + 1] == 2) {
          map[i][j] = 0;
          map[i][j + 1] = 0;
          map[i + 1][j + 1] = 0;
        }
      }
    }
  }

  for (int i = 0; i < N; i++) {
    for (int j = 0; j <= i; j++) {
      if (map[i][j] != 0) {
        cout << "0\n";
        return 0;
      }
    }
  }
  cout << "1\n";
  return 0;
}