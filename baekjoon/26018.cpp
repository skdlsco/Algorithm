#include <iostream>
#include <string>

using namespace std;

int length;
int arr[10001];

void initLength() {
  cout << "? ";
  for (int i = 0; i < 26; i++)
    cout << (char)('a' + i);
  cout << endl << flush;
  cin >> length;
  int temp;
  for (int i = 0; i < length; i++)
    cin >> temp;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  fill(arr, arr + 10000, 0);
  initLength();
  for (int i = 0; i < 5; i++) {
    cout << "? ";
    for (int j = 0; j < 26; j++) {
      if ((j / ((1 << 4) / (1 << i))) % 2)
        cout << (char)('a' + j);
    }
    cout << endl << flush;
    int n;
    cin >> n;
    for (int j = 0; j < n; j++) {
      int idx;
      cin >> idx;
      arr[idx - 1] |= 16 >> i;
    }
  }
  cout << "! ";
  for (int i = 0; i < length; i++) {
    cout << (char)('a' + arr[i]);
  }
  cout << flush;
  return 0;
}