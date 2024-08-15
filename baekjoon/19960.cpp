#include <iostream>

using namespace std;

int N;
int main() {
  cin >> N;
  if (N <= 5) {
    cout << -1;
    return 0;
  }
  if (N % 2) {
    for (int i = 0; i < N / 2; i++) {
      cout << "0 ";
    }
    for (int i = 0; i < N / 2 - 1; i++) {
      cout << "1 ";
    }
    cout << "0 1";
  } else {
    for (int i = 0; i < N / 2 - 1; i++) {
      cout << "0 ";
    }
    for (int i = 0; i < N / 2 - 1; i++) {
      cout << "1 ";
    }
    cout << "0 1";
  }
  return 0;
}
