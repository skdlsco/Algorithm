#include <iostream>
#include <vector>

using namespace std;

int N;
int dist[100001];
int cost[100001];

int main() {
  cin >> N;
  for (int i = 0; i < N - 1; i++) {
    cin >> dist[i];
  }
  for (int i = 0; i < N; i++) {
    cin >> cost[i];
  }
  long long price = cost[0];
  long long sum = 0;
  for (int i = 0; i < N - 1; i++) {
    sum += price * dist[i];
    price = price > cost[i + 1] ? cost[i + 1] : price;
  }
  cout << sum;
  return 0;
}