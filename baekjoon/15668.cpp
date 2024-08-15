#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
int len;
int used[10];
int A;

void check(int num) {
  int B = N - num;
  int checkArr[10];
  for (int i = 0; i <= 9; i++) {
    checkArr[i] = used[i];
  }
  while (B > 9) {
    if (checkArr[B % 10])
      return;
    checkArr[B % 10] = 1;
    B /= 10;
  }
  if (!checkArr[B]) {
    A = num;
  }
}

void f(int cur, int depth) {
  if (cur >= N || depth >= 7)
    return;
  check(cur);
  if (A)
    return;
  for (int i = 0; i <= 9; i++) {
    if (used[i])
      continue;
    used[i] = 1;
    f(cur * 10 + i, depth + 1);
    if (A)
      return;
    used[i] = 0;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  f(0, 0);
  if (A)
    cout << A << " + " << N - A;
  else
    cout << -1;
}