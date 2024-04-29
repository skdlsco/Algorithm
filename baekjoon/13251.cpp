#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int M, K, N;
int stones[51];
double ans = 0.0;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> M;
  for (int i = 0; i < M; i++) {
    cin >> stones[i];
    N += stones[i];
  }
  cin >> K;
  for (int i = 0; i < M; i++) {
    if (stones[i] < K)
      continue;
    double temp = 1;
    for (int j = 0; j < K; j++) {
      temp *= (stones[i] - j) / (double)(N - j);
    }
    ans += temp;
  }
  cout.precision(20);
  cout << ans;
  return 0;
}
