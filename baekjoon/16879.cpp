#include <algorithm>
#include <iostream>

using namespace std;

int N, ans, x, y;

int main() {
	cin.tie(0);
	cout.tie(0);
	ios_base::sync_with_stdio(false);
	cin >> N;
	while (N--) {
		cin >> x >> y;
		int v = (x + y) % 3;
		int m = 3;
		x /= 3;
		y /= 3;
		while (x + y) {
			v += m * ((x + y) % 2);
			m *= 2;
			x /= 2;
			y /= 2;
		}
		ans ^= v;
	}
	cout << (ans ? "koosaga" : "cubelover");
}
