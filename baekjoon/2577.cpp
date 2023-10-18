#include <cstdio>

int main(void)
{
	int cnt[10] = {0,};
	int num = 1, x;

	for (int i = 0 ; i < 3; i++)
	{
		scanf("%d", &x);
		num *= x;
	}
	while (num)
	{
		cnt[num % 10]++;
		num /= 10;
	}
	for (int i = 0; i < 10; i++)
	{
		printf("%d\n", cnt[i]);
	}
	return (0);
}
