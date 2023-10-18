#include <vector>
long long sum(std::vector<int> &a) {
	long long ans = 0;
    for (std::vector<int>::iterator i = a.begin(); i != a.end(); i++)
	{
		ans += *i;
	}
	return ans;
}
