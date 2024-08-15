#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
int arr[1001];
queue<int> q;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }
  sort(arr, arr + N);
  for (int i = 0; i < N; i++) {
    if (!q.empty() && q.front() < arr[i])
      q.pop();
    q.push(arr[i]);
  }
  cout << q.size();
}