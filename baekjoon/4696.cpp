#include <iostream>

using namespace std;

double N;

int main() {
  cout.precision(2);
  while (1) {
    cin >> N;
    if (N == 0.0)
      break;
    cout << fixed << (1 + N + N * N + N * N * N + N * N * N * N) << "\n";
  }
  return 0;
}