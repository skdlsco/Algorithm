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
int A[51];
int B[51];
// Ai가 Bj와 승부를 했을 때의 최대값.
int dp[51][51];
int visited[51][51];

void f(int a, int b) {
  if (a < 0 || b < 0)
    return;
  if (visited[a][b])
    return;
  visited[a][b] = 1;
  if (b == 0) {
    if (A[a] > B[b])
      dp[a][b] = 2;
    else if (A[a] == B[b])
      dp[a][b] = 1;
    return;
  }
  int ans = 0;
  f(a, b - 1);
  f(a - 1, b - 1);
  ans = dp[a][b - 1];
  if (A[a] > B[b])
    ans = max(dp[a][b - 1], dp[a - 1][b - 1] + 2);
  else if (A[a] == B[b])
    ans = max(dp[a][b - 1], dp[a - 1][b - 1] + 1);
  dp[a][b] = ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> A[i];
  }

  for (int i = 0; i < N; i++) {
    cin >> B[i];
  }

  sort(A, A + N);
  sort(B, B + N);
  f(N - 1, N - 1);
  cout << dp[N - 1][N - 1];
}