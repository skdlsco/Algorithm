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

int N;
string preOrder, inOrder;

pii tree[128];

// [S, E)
void f(int inS, int inE, int preS, int preE) {
  char root = preOrder[preS];
  int rootIdx;
  for (int i = inS; i < inE; i++) {
    if (inOrder[i] == root) {
      rootIdx = i;
      break;
    }
  }
  int lSize = rootIdx - inS;
  int rSize = inE - rootIdx - 1;
  tree[root].first = 0;
  tree[root].second = 0;
  if (lSize != 0) {
    tree[root].first = preOrder[preS + 1];
    f(inS, rootIdx, preS + 1, preS + lSize + 1);
  }
  if (rSize != 0) {
    tree[root].second = preOrder[preS + lSize + 1];
    f(rootIdx + 1, inE, preS + lSize + 1, preE);
  }
}

void postOrder(char cur) {
  if (tree[cur].first)
    postOrder(tree[cur].first);
  if (tree[cur].second)
    postOrder(tree[cur].second);
  cout << cur;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  while (cin >> preOrder >> inOrder) {
    N = preOrder.length();
    f(0, N, 0, N);
    postOrder(preOrder[0]);
    cout << "\n";
  }
}