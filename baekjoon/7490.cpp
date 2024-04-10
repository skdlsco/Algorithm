#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

int T, N;
int op[10];

void dfs(int i) {
  if (i == N - 1) {
    int sign = 1;
    int sum = 0;
    int num = 1;
    int cur = 1;
    for (int j = 0; j < N - 1; j++) {
      if (op[j] == ' ') {
        num *= 10;
      } else if (op[j] == '-') {
        sum += num * sign;
        num = 0;
        sign = -1;
      } else {
        sum += num * sign;
        num = 0;
        sign = 1;
      }
      cur++;
      num += cur;
    }
    sum += num * sign;
    if (sum == 0) {
      for (int j = 0; j < N; j++) {
        cout << (j + 1);
        if (j != N - 1)
          cout << (char)op[j];
      }
      cout << "\n";
    }
    return;
  }
  op[i] = ' ';
  dfs(i + 1);
  op[i] = '+';
  dfs(i + 1);
  op[i] = '-';
  dfs(i + 1);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;

  while (T--) {
    cin >> N;
    dfs(0);
    if (T > 0)
      cout << "\n";
  }
  return 0;
}
