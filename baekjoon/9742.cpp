#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

string str;
int N;
int idx = 1;
int arr[11];
int isSelected[11];
int exist = 0;
void f(int cur) {
  if (cur == str.length()) {
    if (idx == N) {
      cout << str << " " << N << " = ";
      for (int i = 0; i < str.length(); i++) {
        cout << str[arr[i]];
      }
      cout << "\n";
      exist = 1;
    }
    idx++;
    return;
  }
  for (int i = 0; i < str.length(); i++) {
    if (isSelected[i])
      continue;
    isSelected[i] = true;
    arr[cur] = i;
    f(cur + 1);
    isSelected[i] = false;
    arr[cur] = -1;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  while (cin >> str) {
    cin >> N;
    idx = 1;
    exist = 0;
    f(0);
    if (!exist) {
      cout << str << " " << N << " = No permutation\n";
    }
  }
  return 0;
}
