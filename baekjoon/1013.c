#include <stdio.h>

void solve() {
  char str[201];
  int isValid = 1;
  
  scanf("%s", str);
  int cur = 0;
  while (str[cur]) {
    if (str[cur] == '0' && str[cur + 1] == '1') {
      cur += 2;
      continue;
    } else if (str[cur] == '1' && str[cur + 1] == '0') {
      //  10
      cur += 2;
      // 0 하나 이상 필요.
      if (str[cur] != '0') {
        isValid = 0;
        break;
      }
      // 0이 아닐 때 까지 cur++
      while (str[cur] == '0')
        cur++;
      // 1하나 이상 필요.
      if (str[cur] != '1') {
        isValid = 0;
        break;
      }
      // 1이 아닐 때 까지 cur++
      while (str[cur] == '1')
        cur++;
      // 다음이 null 인 경우 sucess
      if (str[cur] == '\0')
        break;
      // 다음이 0인 경우 1개 인지 2개 인지 세야한다.
      // str[cur] == 0인 상태, str[cur + 1]이 1이면 continue, 0이면 cur -- 이후 continue null이면 invalid
      if (str[cur + 1] == '1')
        continue;
      else if (str[cur + 1] == '0' && str[cur - 2] == '1') {
        cur--; 
        continue;
      } else {
        isValid =0;
        break;
      }
    } else {
      isValid = 0;
      break;
    }
  }
  if (isValid) 
    printf("YES\n");
  else 
    printf("NO\n");
}

int main() {
  int N;

  scanf("%d", &N);
  while (N--)
    solve();  
}