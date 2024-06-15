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
int A[1000001];
int B[1000001];
int ans[1000001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> A[i];
  }
  for (int i = 0; i < M; i++) {
    cin >> B[i];
  }
  for (int i = 0, j = 0, k = 0; k < N + M; k++) {
    if (i == N || j < M && B[j] < A[i])
      cout << B[j++] << " ";
    else
      cout << A[i++] << " ";
  }
  return 0;
}
