#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int T, N;
vector<int> primes;
int cieve[100001];

void init() {
  for (int i = 2; i < 100000; i++) {
    if (!cieve[i]) {
      primes.push_back(i);
      for (int j = i + i; j < 100000; j += i) {
        cieve[j] = 1;
      }
    }
  }
}
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  init();
  cin >> T;
  while (T--) {
    cin >> N;
    int i = 0;
    int cnt = 0;
    while (N > 1) {
      if (N % primes[i] == 0) {
        N /= primes[i];
        cnt++;
      } else {
        if (cnt != 0) {
          cout << primes[i] << " " << cnt << "\n";
        }
        cnt = 0;
        i++;
      }
    }
    if (cnt != 0) {
      cout << primes[i] << " " << cnt << "\n";
    }
  }
  return 0;
}
