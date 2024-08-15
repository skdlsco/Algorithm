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

int alphaCnt[] = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
int N, K;
ll dp[4001];
string A, B;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> A >> B;
  for (int i = 0; i < A.length(); i++) {
    dp[i * 2] = alphaCnt[A[i] - 'A'];
    dp[i * 2 + 1] = alphaCnt[B[i] - 'A'];
  }
  for (int i = 0; i < A.length() * 2 - 2; i++) {
    for (int j = 0; j < A.length() * 2 - i - 1; j++) {
      dp[j] = (dp[j] + dp[j + 1]) % 10;
    }
  }
  if (dp[0] == 0)
    cout << "0";
  cout << dp[0] * 10 + dp[1];
}
