#include <algorithm>
#include <iostream>
#include <cstdio>
using namespace std;

int N, M;
int a;
int main() {
  cin >> a;
  cin >> N >> M;

  if (a == 1) {
    printf("%d\n", min(max(N, M), min(N * 2, M * 2)));
  } else {
    int target = min(N, M);
    printf("%.8lf\n", (target * 2.0) / a);
  }
  return 0;
}