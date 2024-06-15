#include <iostream>
#include <string>
using namespace std;

int check[101];
int N,M;
string str;
int main() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> str;
        for (int j = 0; j < M; j++) {
            check[j] |= str[j] == 'O';
        }
    }
    for (int i = 0; i < M; i++) {
        if (!check[i]) {
            cout << i + 1 << "\n";
            return 0;
        }
    }

    cout << "ESCAPE FAILED\n";
    return 0;
}