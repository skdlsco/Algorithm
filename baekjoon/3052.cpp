#include <cstdio>

int main(void)
{
	int check[42] = {0, };
	int n;

	for (int i = 0; i < 10; i++)
	{
		scanf("%d", &n);
		check[n % 42]++;
	}
	n = 0;
	for (int i = 0 ; i < 42; i++)
	{
		if (check[i])
			n++;
	}
	printf("%d\n", n);
	return (0);
}
