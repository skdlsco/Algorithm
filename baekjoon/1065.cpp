#include <cstdio>

int check(int n)
{
	int d;
	int prev;

	prev = n % 10;
	n /= 10;
	d = prev - (n % 10);
	prev = n % 10;
	n /= 10;
	while (n)
	{
		if (d != (prev - (n % 10)))
			return (0);
		n /= 10;
	}
	return (1);
}

int main(void)
{
	int n;
	int cnt;

	cnt = 0;
	scanf("%d", &n);
	for (int i = 1; i <= n; i++)
	{
		if (check(i))
			cnt++;
	}
	printf("%d\n", cnt);
	return (0);
}
