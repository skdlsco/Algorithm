import math

N,M = map(int,input().split())
if N==1 and M==1:
    print(1)
else:
    two = ((N % 3) * (M % 3))
    MOD = 1000000007
    if two == 1:
        two = 4
    ans = pow(3, ((N * M) - two) // 3, MOD)
    if two != 0:
        ans = (ans * two) % MOD
    print(ans)