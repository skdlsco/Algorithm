#include <iostream>

using namespace std;

int N;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  cout << (N * (N + 2)) << "\n";
  for (int i = 0; i < N; i++) {
    for (int j = 0; j <= i; j++) {
      cout << i % 2 + 1 << " " << j + 1 << "\n";
    }
  }
  for (int i = 1; i <= N; i++) {
    cout << N % 2 + 1 << " " << i << "\n";
  }
  for (int i = 1; i <= N; i++) {
    for (int j = i; j <= N; j++) {
      cout << ((i + N % 2) % 2 + 1) << " " << j << "\n";
    }
  }
}