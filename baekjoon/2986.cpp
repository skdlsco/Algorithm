#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int key;
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  key = 1;
  for (int i = 2; i < min(N, 40000); i++) {
    if (N % i == 0) {
      key = max(key, i);
      key = max(key, N / i);
    }
  }
  cout << N - key << "\n";
  return 0;
}
