#include <algorithm>
#include <iostream>
using namespace std;

int city[1000001];
int country[40000];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int N, V, E;
  cin >> N;
  int idx = 0;
  for (int i = 0; i < N; i++) {
    cin >> V >> E;
    for (int j = 0; j < V; j++, idx++) {
      city[idx] = i;
    }
    country[i] = V - E;
    int temp;
    while (E--) {
      cin >> temp >> temp;
    }
  }
  int Q;
  cin >> Q;
  int c, k, u, v;
  while (Q--) {
    cin >> c;
    if (c == 1) {
      cin >> k;
      cout << country[k - 1] << "\n";
    } else {
      cin >> u >> v;
      if (c == 2)
        country[city[u - 1]]++;
      else
        country[city[u - 1]]--;
    }
  }
}