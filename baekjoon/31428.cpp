#include <iostream>

using namespace std;

int N;
char target;
int arr[128];
int main() {
  cin >> N;
  while (N--) {
    char c;
    cin >> c;
    arr[c]++;
  }
  cin >> target;
  cout << arr[target];
}