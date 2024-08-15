#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

struct Command {
  int r;
  int c;
  int s;
};

int N, M, K;

int originArr[51][51];
int arr[51][51];
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};

Command commands[6];

void initArr() {
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= M; x++) {
      arr[y][x] = originArr[y][x];
    }
  }
}

void rotate(int sy, int sx, int len) {
  int y = sy;
  int x = sx;
  int temp = arr[sy][sx];
  for (int i = 0; i < len * 4 - 1; i++) {
    int ny = y + dy[i / len];
    int nx = x + dx[i / len];
    arr[y][x] = arr[ny][nx];
    y = ny;
    x = nx;
  }
  arr[y][x] = temp;
}

int getMinRow() {
  int result = 123456789;
  for (int y = 1; y <= N; y++) {
    int sum = 0;
    for (int x = 1; x <= M; x++) {
      sum += arr[y][x];
    }
    result = min(result, sum);
  }
  return result;
}

void doCommand(Command cmd) {
  int r = cmd.r;
  int c = cmd.c;
  int s = cmd.s;
  for (int i = 1; i <= s; i++) {
    rotate(r - i, c - i, i * 2);
  }
}

int seq[6];
int selected[6];
int ans = 123456789;
void run(int depth) {
  if (depth == K) {
    initArr();
    for (int i = 0; i < K; i++) {
      doCommand(commands[seq[i]]);
    }
    int result = getMinRow();
    ans = min(ans, result);
    return;
  }
  for (int i = 0; i < K; i++) {
    if (selected[i])
      continue;
    seq[depth] = i;
    selected[i] = 1;
    run(depth + 1);
    selected[i] = 0;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> N >> M >> K;
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= M; x++) {
      cin >> originArr[y][x];
    }
  }
  for (int i = 0; i < K; i++) {
    int r, c, s;
    cin >> r >> c >> s;
    commands[i] = {r, c, s};
  }
  run(0);
  cout << ans;
  return 0;
}