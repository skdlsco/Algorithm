#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int arr[20][20];
string str;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  cin >> str;
  int i = 0;
  while (i < str.length()) {
    arr[i / N][((i / N) % 2) ? (N - (i % N + 1)) : (i % N)] = str[i];
    i++;
  }
  i = 0;
  int h = str.length() / N;
  while (i < str.length()) {
    cout << (char)arr[i % h][i / h];
    i++;
  }
  return 0;
}
