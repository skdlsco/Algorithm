#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
int N, M;

ll INF = -10000000001;

struct info {
  ll left;
  ll right;
  ll sum;
  ll ans;
};

int arr[100001];
info tree[262144];

void init(int node, int s, int e) {
  if (s == e) {
    tree[node] = {arr[s], arr[s], arr[s], arr[s]};
  } else {
    init(node * 2, s, (s + e) / 2);
    init(node * 2 + 1, (s + e) / 2 + 1, e);
    info lInfo = tree[node * 2];
    info rInfo = tree[node * 2 + 1];
    ll left = max(lInfo.left, lInfo.sum + rInfo.left);
    ll right = max(rInfo.right, rInfo.sum + lInfo.right);
    ll sum = lInfo.sum + rInfo.sum;
    ll ans =
        max(left, max(right,
                      max(lInfo.ans,
                          max(rInfo.ans, max(lInfo.right + rInfo.left, sum)))));
    tree[node] = {left, right, sum, ans};
  }
}

info query(int node, int s, int e, int l, int r) {
  if (r < s || e < l)
    return {INF, INF, INF, INF};
  if (l <= s && e <= r)
    return tree[node];
  info lInfo = query(node * 2, s, (s + e) / 2, l, r);
  info rInfo = query(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
  if (lInfo.ans == INF)
    return rInfo;
  if (rInfo.ans == INF)
    return lInfo;
  ll left = max(lInfo.left, lInfo.sum + rInfo.left);
  ll right = max(rInfo.right, rInfo.sum + lInfo.right);
  ll sum = lInfo.sum + rInfo.sum;
  ll ans =
      max(left,
          max(right, max(lInfo.ans,
                         max(rInfo.ans, max(lInfo.right + rInfo.left, sum)))));
  return {left, right, sum, ans};
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    ll temp;
    cin >> temp;
    arr[i] = temp;
  }
  init(1, 1, N);
  cin >> M;
  for (int i = 1; i <= M; i++) {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    ll ans = INF;
    if (y1 < x2) {
      info mid = query(1, 1, N, y1, x2);
      ans = mid.sum;
      info right = query(1, 1, N, x2, y2);
      ans = max(ans, ans + right.left - arr[x2]);
      info left = query(1, 1, N, x1, y1);
      ans = max(ans, ans + left.right - arr[y1]);
    } else {
      info mid = query(1, 1, N, x2, y1);
      ans = mid.ans;
      info left = query(1, 1, N, x1, x2);
      info right = query(1, 1, N, x2, y2);
      ans = max(ans, left.right + right.left - arr[x2]);
      left = query(1, 1, N, x1, y1);
      right = query(1, 1, N, y1, y2);
      ans = max(ans, left.right + right.left - arr[y1]);
    }
    cout << ans << "\n";
  }
  return 0;
}