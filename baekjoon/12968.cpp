#include <iostream>
using namespace std;

int R, C, K;
int main() {
  cin >> R >> C >> K;

  if (K == 1)
    cout << 1;
  else if (R + C == 3 || !(R * C % 2))
    cout << 1;
  else
    cout << 0;
}