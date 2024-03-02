#include <algorithm>
#include <iostream>
using namespace std;

int N;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;

  N--;
  cout << 0.5 + 0.5 / N;
  return 0;
}