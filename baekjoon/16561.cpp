#include<iostream>

using namespace std;

int main() {
   int n;
   cin >> n;
   int k = n / 3 - 1;
   cout << (k * (k - 1)) / 2;
   return 0;
}