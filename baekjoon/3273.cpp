#include <algorithm>
#include <iostream>

using namespace std;

int N;
int X;
int check[2000001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int num ;
    cin >> num;
    check[num]++;
  }
  cin >> X;
  long long ans = 0;
  for (int i = 1; i < 2000000; i++) {
    if (X - i == i) {
      ans += check[i] * check[i - 1] / 2;
      check[i] = 0;
    } else if (X - i > 0 && check[X - i] > 0) {
      ans += check[i] * check[X - i];
      check[i] = 0;
      check[X - i] = 0;
    }
  }
  cout << ans;
  return 0;
}
