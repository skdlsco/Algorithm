#include <algorithm>
#include <iostream>
#include <string>
using namespace std;

typedef long long int ll;

int K, N;

int up[26];
int down[26];
int map[1001][26];
int quetion;
string target;
char ans[26];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> K >> N;
  cin >> target;
  for (int i = 0; i < N; i++) {
    string s;
    cin >> s;
    for (int j = 0; j < K - 1; j++) {
      map[i][j] = s[j];
    }
    if (s[0] == '?')
      quetion = i;
  }
  for (int i = 0; i < K; i++) {
    up[i] = i;
    down[i] = target[i] - 'A';
  }
  for (int i = 0; i < quetion; i++) {
    for (int j = 0; j < K - 1; j++) {
      if (map[i][j] == '-') {
        swap(up[j], up[j + 1]);
      }
    }
  }
  // cout << "print\n";
  for (int i = N - 1; i > quetion; i--) {
    for (int j = 0; j < K - 1; j++) {
      if (map[i][j] == '-') {
        swap(down[j], down[j + 1]);
      }
    }
  }
  for (int i = 0; i < K - 1; i++) {
    if (up[i] == down[i]) {
      ans[i] = '*';
    } else if (up[i] == down[i + 1] && up[i + 1] == down[i]) {
      swap(up[i], up[i + 1]);
      ans[i] = '-';
    } else {
      for (int i = 0; i < K - 1; i++) {
        cout << "x";
      }
      return 0;
    }
  }
  for (int i = 0; i < K - 1; i++) {
    cout << ans[i];
  }
  return 0;
}
