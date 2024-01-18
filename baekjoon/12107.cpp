#include <iostream>

using namespace std;

int N;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  if (N > 1)
    cout << "A";
  else
    cout << "B";
}