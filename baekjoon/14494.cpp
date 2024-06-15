#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, M;
ll MOD = 1e9 + 7;
ll arr[1001][1001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  arr[0][0] = 1;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= M; j++) {
      arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j] + arr[i][j - 1]) % MOD;
    }
  }
  cout << arr[N][M];
  return 0;
}
