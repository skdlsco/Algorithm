#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
string s;
int frontCnt;
int arr[500001];
char ans[500001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  int idx = 0;
  int front = 1;
  for (int i = 0; i < N; i++) {
    char c;
    cin >> c;
    if (c == 'X' && front)
      frontCnt++;
    else
      arr[idx++] = c == 'O';
    if (c == 'O')
      front = 0;
  }
  for (int i = 0; i < frontCnt; i++) {
    arr[idx++] = 0;
  }
  if (front) {
    cout << "YES\n";
    for (int i = 0; i < N; i++)
      cout << "-";
    cout << "\n";
    return 0;
  }
  for (int i = 0; i < N; i++) {
    if (arr[i])
      ans[i] = '+';
    else {
      int cnt = 0;
      while (i + cnt < N && !arr[i + cnt])
        cnt++;
      if (cnt % 2 == 0) {
        cout << "NO";
        return 0;
      }
      for (int j = 0; j < cnt / 2; j++) {
        ans[i + j] = '+';
      }
      for (int j = cnt / 2; j < cnt; j++) {
        ans[i + j] = '-';
      }
      i += cnt - 1;
    }
  }
  cout << "YES\n";
  for (int i = N - frontCnt; i < N; i++) {
    cout << ans[i];
  }
  for (int i = 0; i < N - frontCnt; i++) {
    cout << ans[i];
  }
  cout << "\n";
}
