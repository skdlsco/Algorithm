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
string str;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    cin >> str;
    if (str.compare("anj") == 0) {
      cout << "뭐야;";
      return 0;
    }
  }
  cout << "뭐야?";
  return 0;
}
