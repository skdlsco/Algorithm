#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int T;
int N;

int origin[4];
int arr[4];
int bitCount[16];
int mask[5] = {0, 1, 3, 7, 15};

void initBitCount() {
  for (int i = 0; i < 16; i++) {
    int cnt = 0;
    int num = i;
    while (num) {
      cnt += num % 2;
      num /= 2;
    }
    bitCount[i] = cnt;
  }
}

bool canWork() {
  int check[16];
  fill(check, check + 16, 0);
  for (int i = 0; i < N; i++) {
    check[arr[i]]++;
  }
  int sum = 0;
  for (int i = 0; i < 1 << N; i++) {
    sum += check[i];
    if (check[i] && bitCount[i] != check[i])
      return 0;
  }
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      for (int k = 0; k < N; k++) {
        if (arr[i] & 1 << k && arr[j] & 1 << k && arr[i] != arr[j])
          return 0;
      }
    }
  }
  return sum == N;
}

void solve(int caseNum) {
  cin >> N;
  for (int i = 0; i < N; i++) {
    int num = 0;
    for (int j = 0; j < N; j++) {
      char c;
      cin >> c;
      if (c == '1')
        num |= 1 << j;
    }
    origin[i] = num;
  }
  int ans = 16;
  for (int i = 0; i < 1 << (N * N); i++) {
    for (int j = 0; j < N; j++) {
      arr[j] = (i & (mask[N] << (j * N))) >> (j * N);
    }
    if (canWork()) {
      int valid = 1;
      int cnt = 0;
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          if (origin[j] & (1 << k) && !(arr[j] & (1 << k)))
            valid = 0;
          if (arr[j] & (1 << k) && !(origin[j] & (1 << k)))
            cnt++;
        }
      }
      if (valid)
        ans = min(ans, cnt);
    }
  }
  cout << "Case #" << caseNum << ": " << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  initBitCount();
  for (int i = 1; i <= T; i++) {
    solve(i);
  }
  return 0;
}
