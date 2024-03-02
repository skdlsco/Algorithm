#include <algorithm>
#include <iomanip>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
ll N, M;
string s;

struct Node {
  int child[26];
  int cnt = 0;
  int kind = 0;
  int valid = 0;
  void init() {
    for (int i = 0; i < 26; i++) {
      child[i] = -1;
    }
  }
};

struct Trie {
  vector<Node> trie;
  int cnt = 0;
  int newNode() {
    Node temp;
    temp.init();
    trie.push_back(temp);
    return trie.size() - 1;
  }

  void add(const char *s, int node) {
    if (*s == '\0') {
      trie[node].valid = 1;
      return;
    }
    trie[node].cnt++;
    int idx = *s - 'a';
    if (trie[node].child[idx] == -1) {
      trie[node].kind++;
      int next = newNode();
      trie[node].child[idx] = next;
    }
    add(s + 1, trie[node].child[idx]);
  }

  ll query(int node) {
    ll sum = 0;
    if (trie[node].valid || trie[node].kind > 1 || node == 0) {
      sum += trie[node].cnt;
    }
    for (auto next : trie[node].child) {
      if (next == -1)
        continue;
      sum += query(next);
    }
    return sum;
  }
};

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cout << fixed << setprecision(2);
  while (1) {
    cin >> N;
    if (cin.eof())
      return 0;
    Trie t;
    t.newNode();
    for (int i = 0; i < N; i++) {
      cin >> s;
      t.add(s.c_str(), 0);
    }
    ll sum = t.query(0);
    cout << sum / (double)N << endl;
  }
  return 0;
}
