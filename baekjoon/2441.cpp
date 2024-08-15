#include<iostream>
using namespace std;

int main() {
  int N;
    cin >> N;
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < i; j++){
            cout << ' ';
            }
        for (int j = 0; j < N - i; j++){
            cout << '*';
            }
        
    cout<<'\n';
    }
    return 0;
}