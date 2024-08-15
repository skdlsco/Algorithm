#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int R, C, N;
ll bonus[3];
ll pointMap[201][201];
vector<ll> pointList;
int visited[201][201];
int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[] = {1, 1, 0, -1, -1, -1, 0, 1};
ll ans, sum;
vector<ll> selectList;

void calc(int r, int c, int depth) {
  if (depth == 2) {
    depth--;
    for (int d = 0; d < 8; d++) {
      int nc = dx[d] + c;
      int nr = dy[d] + r;
      if (nr < 0 || nr >= R || nc < 0 || nc >= C)
        continue;
      if (visited[nr][nc]) {
        depth++;
      }
    }
  }
  ll point = sum;
  for (int i = 0; i < depth; i++) {
    point += bonus[i];
  }
  int idx = N - 1;
  vector<ll> temp = vector<ll>(selectList);
  sort(temp.begin(), temp.end());
  for (int i = 0; i < temp.size(); i++) {
    if (pointList[idx] < temp[i])
      break;
    point -= pointList[idx];
    point += temp[i];
    idx--;
  }
  ans = max(ans, point);
}

void f(int prevR, int prevC, int depth) {
  if (depth == 2)
    return;
  for (int d = 0; d < 8; d++) {
    int nc = dx[d] + prevC;
    int nr = dy[d] + prevR;
    if (nr < 0 || nr >= R || nc < 0 || nc >= C)
      continue;
    if (visited[nr][nc])
      continue;
    visited[nr][nc] = 1;
    selectList.push_back(pointMap[nr][nc]);
    calc(nr, nc, depth + 1);
    f(nr, nc, depth + 1);
    selectList.pop_back();
    visited[nr][nc] = 0;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> R >> C >> N;
  for (int i = 0; i < 3; i++) {
    cin >> bonus[i];
  }
  for (int i = 0; i < 3; i++) {
    cin >> bonus[i];
  }
  for (int r = 0; r < R; r++) {
    for (int c = 0; c < C; c++) {
      cin >> pointMap[r][c];
      pointList.push_back(pointMap[r][c]);
    }
  }
  sort(pointList.begin(), pointList.end(), greater<ll>());
  sort(bonus, bonus + 3, greater<ll>());
  for (int i = 0; i < N; i++) {
    sum += pointList[i];
  }
  ans = sum;
  for (int r = 0; r < R; r++) {
    for (int c = 0; c < C; c++) {
      visited[r][c] = 1;
      selectList.push_back(pointMap[r][c]);
      f(r, c, 0);
      selectList.pop_back();
      visited[r][c] = 0;
    }
  }
  cout << ans << "\n";
}