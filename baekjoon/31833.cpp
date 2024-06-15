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

string A, B;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    string str;
    cin >> str;
    A += str;
  }
  for (int i = 0; i < N; i++) {
    string str;
    cin >> str;
    B += str;
  }
  if (A.length() < B.length())
    cout << A;
  else if (B.length() < A.length())
    cout << B;
  else if (A.compare(B) < 0)
    cout << A;
  else
    cout << B;
  return 0;
}
