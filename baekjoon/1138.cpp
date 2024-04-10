#include <algorithm>
#include <cstring>
#include <iostream>

using namespace std;

int N;

int ans[10];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int idx;
    cin >> idx;
    int j = 0;
    while (ans[j] || j < idx) {
      if (ans[j]) idx++;
      j++;
    }
    ans[j] = i + 1;
  }
  for (int i = 0; i < N; i++) {
    cout << ans[i] << " ";
  }
  return 0;
}
