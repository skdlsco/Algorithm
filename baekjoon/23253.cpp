#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N, M, K;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  while (M--) {
    int num, cur = N + 1;
    cin >> K;
    while (K--) {
      cin >> num;
      if (num > cur) {
        cout << "No";
        return 0;
      }
      cur = num;
    }
  }
  cout << "Yes";
  return 0;
}