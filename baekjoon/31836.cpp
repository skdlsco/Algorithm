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

int N;
vector<int> A;
vector<int> B;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N > 1) {
    if (N == 2) {
      A.push_back(1);
      B.push_back(2);
      N -= 2;
    } else {
      B.push_back(N);
      A.push_back(N - 1);
      A.push_back(N - 2);
      N -= 3;
    }
  }
  cout << A.size() << "\n";
  for (int v : A) {
    cout << v << " ";
  }
  cout << "\n";
  cout << B.size() << "\n";
  for (int v : B) {
    cout << v << " ";
  }
  cout << "\n";
  return 0;
}
