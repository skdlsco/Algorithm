#include <iostream>

using namespace std;

int N;
int check[10000];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  int i = 0;
  while (!check[N]) {
    check[N] = 1;
    int temp = (N / 10) % 100;
    N = temp * temp;
    i++;
  }
  cout << i;
}
