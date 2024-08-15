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
string S;
int startIdx, endIdx;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> S;

  int fCount = 0;
  for (int i = 0; i < N; i++) {
    if (S[i] != 'F') {
      startIdx = i;
      break;
    }
    fCount++;
  }
  for (int i = N - 1; i >= 0; i--) {
    if (S[i] != 'F') {
      endIdx = i + 1;
      break;
    }
    fCount++;
  }
  if (fCount == N * 2) {
    cout << N << "\n";
    for (int i = 0; i < N; i++) {
      cout << i << "\n";
    }
    return 0;
  }
  int maxV = 0;
  int minV = 0;
  for (int i = startIdx; i < endIdx; i++) {
    char c = S[i];
    if (c == 'F') {
      char a = S[i - 1];
      int len = 0;
      while (i < endIdx && S[i] == 'F') {
        i++;
        len++;
      }
      char b = S[i];
      if (a == b) {
        if (len % 2) {
          maxV += len + 1;
        } else {
          minV++;
          maxV += len + 1;
        }
      } else {
        if (len % 2) {
          minV++;
          maxV += len;
        } else {
          maxV += len;
        }
      }
    }
  }
  int prev = 0;
  for (char c : S) {
    if (c != 'F' && c == prev) {
      minV++;
      maxV++;
    }
    prev = c;
  }
  maxV += fCount;
  cout << (maxV - minV) / (1 + (fCount == 0)) + 1 << "\n";
  for (int i = minV; i <= maxV; i += 1 + (fCount == 0)) {
    cout << i << "\n";
  }
}