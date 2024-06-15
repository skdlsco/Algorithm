#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

typedef long long int ll;

int ans[1000001];
int N;
int pos[10];
void init() {
  int i = 1;
  int num = 1;
  while (i < 1000001) {
    fill(pos, pos + 10, 0);
    int target = num;
    int isValid = 1;
    while (target) {
      int idx = 1;
      if (pos[target % 10]) {
        isValid = 0;
        pos[target % 10]--;
        int temp = 1;
        while (pos[target % 10]--) {
          temp *= 10;
        }
        num += temp;
        num -= num % temp;
        num--;
        break;
      }
      pos[target % 10] = idx;
      target /= 10;
      idx++;
    }
    if (isValid)
      ans[i++] = num;
    num++;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  init();
  while (true) {
    cin >> N;
    if (N == 0)
      break;
    cout << ans[N] << "\n";
  }
  return 0;
}
