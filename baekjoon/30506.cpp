#include <stdio.h>

int main() {
  int first;
  scanf("%d", &first);
  char result[100];
  for (int i = 0; i < 100; i++) {
    printf("? ");
    for (int j = 0; j < 100; j++) {
      if (j != i)
        printf("2");
      else
        printf("5");
    }
    printf("\n");
    fflush(stdout);
    int cur;
    scanf("%d", &cur);
    if (cur < first) {
      result[i] = '5';
    } else if (cur > first) {
      result[i] = '0';
    } else {
      result[i] = '2';
    }
  }
  printf("! ");
  for (int i = 0; i < 100; i++) {
    printf("%c", result[i]);
  }
  printf("\n");
  fflush(stdout);
  return 0;
}