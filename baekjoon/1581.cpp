#include <algorithm>
#include <iostream>

using namespace std;

int FF, FS, SF, SS;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> FF >> FS >> SF >> SS;
  if (FF) {
    if (FS)
      cout << FF + SS + min(FS, SF) * 2 + (FS > SF);
    else
      cout << FF;
  } else if (FS) {
    if (SF)
      cout << SS + min(FS, SF) * 2 + (FS > SF);
    else
      cout << SS + 1;
  } else if (SS) {
    cout << SS + (SF > 0);
  } else
    cout << (SF > 0);
}
