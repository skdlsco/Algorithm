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
int sum;
int maxSum;
int maxIdx;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  for (int i = 1; i <= 5; i++) {
    sum = 0;
    for (int j = 1; j <= 4; j++) {
      int num;
      cin >> num;
      sum += num;
    }
    if (sum > maxSum) {
      maxIdx = i;
      maxSum = sum;
    }
  }
  cout << maxIdx << " " << maxSum;
  return 0;
}
