# 1920. 수 찾기
# N개의 정수 A[1], A[2], …, A[N]이 주어졌을 때,
# X라는 정수가 이 안에 존재하면 1 아니면 0을 출력하는 문제
# (M: X의 리스트)

N = int(input()) # 10^5
A = list(map(int, input().split()))
M = int(input()) # 10^5
Xs = list(map(int, input().split()))

# N*M -> 10^10 -> Time out -> M * (logN 이하의 탐색) 필요
# logN 탐색: bisect
# or 1 탐색: hash set

def bi():
    import bisect # 직접 이진탐색 구현해도 됨
    A.sort()

    for x in Xs:
        if bisect.bisect_right(A, x) - bisect.bisect_left(A, x):
            print("1")
        else:
            print("0")


def hash():
    
    from collections import Counter # 직접 카운터 딕셔너리 구현해도 됨
    d = Counter(A) # N

    for x in Xs: # M
        if x in d:
            print("1")
        else:
            print("0")


# 둘 다 pass. 아무거나 선택해도 됨.
# 시간은 약 30ms 정도 차이로 hash가 더 빠름.
# 메모리는 약 5MB 정도 차이로 bi가 더 적음.
bi()
hash()


# self-feedback
# 실버4는 확실히 난이도가 쉽다.
# 좀 더 어려운 문제로 연습하자.