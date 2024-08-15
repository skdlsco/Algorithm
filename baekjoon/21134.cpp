#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

vector<int> arr{1, 2, 3, 4, 5, 6, 7, 8, 9};

int map[10][10];
int ans = 123456789;
string s;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> s;
  for (int i = 0; i < s.length() - 1; i++) {
    map[s[i] - '0'][s[i + 1] - '0']++;
  }

  do {
    int diff[10][10];
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        diff[arr[i]][arr[j]] = abs(i - j);
      }
    }
    sum += diff[arr[0]][s[0] - '0'];
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= 9; j++) {
        sum += map[i][j] * diff[i][j];
      }
    }
    ans = min(ans, sum);
  } while (next_permutation(arr.begin(), arr.end()));
  cout << ans + s.length();
}