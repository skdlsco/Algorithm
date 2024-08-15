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

int T, N;
int arr[1000001];
void solve() {
  cin >> N;
  int sum = 0;
  int has3 = 0;
  int only3and0 = 1;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    sum ^= arr[i];
    if (arr[i] == 1 || arr[i] == 2)
      only3and0 = 0;
    if (arr[i] == 3)
      has3 = 1;
  }

  // 3이 없는 경우
  if (has3 == 0) {
    cout << "0\n";
  } else if (N == 1) {
    // 3만 딱 하나 있는 경우
    cout << "-1\n";
  } else if (sum != 3) {
    // xor 1..N 했을 때 3이 아니면 1번 만에 전부 0, 1, 2 로 만들 수 있다
    cout << "1\n";
  } else if (only3and0) {
    // 3과 0으로만 이루어진 경우
    int has0 = 0;
    for (int i = 0; i < N; i++) {
      if (arr[i] == 0)
        has0 = 1;
    }
    // 0이 존재 하는 경우 바로 3을 짝수개로 만들 수 있다
    if (has0)
      cout << "2\n";
    else
      cout << "3\n";
  } else {
    int startWith = 0;
    int endWith = 0;
    for (int i = 0; i < N; i++) {
      if (arr[i] == 0)
        continue;
      if (arr[i] != 3)
        startWith = 1;
      break;
    }
    for (int i = N - 1; i >= 0; i--) {
      if (arr[i] == 0)
        continue;
      if (arr[i] != 3)
        endWith = 1;
      break;
    }
    // 1 or 2로 시작하거나 끝나는 경우.
    if (startWith || endWith)
      cout << "1\n";
    else
      cout << "2\n";
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
}