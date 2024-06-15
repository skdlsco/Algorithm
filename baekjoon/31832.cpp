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
int case1(string &str) {
  int upper = 0;
  int lower = 0;
  for (char c : str) {
    if ('A' <= c && c <= 'Z')
      upper++;
    if ('a' <= c && c <= 'z')
      lower++;
  }
  return upper <= lower;
}
int case2(string &str) { return str.length() <= 10; }
int case3(string &str) {
  for (char c : str) {
    if ('0' <= c && c <= '9')
      continue;
    return 1;
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    string str;
    cin >> str;
    if (case1(str) && case2(str) && case3(str)) {
      cout << str;
      break;
    }
  }
  return 0;
}
