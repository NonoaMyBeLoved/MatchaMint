# 관계 출력
def print_relation(matrix, A) :
    for i in range(len(matrix)) :
        for j in range(len(matrix)) :
            if matrix[i][j] == 1 :
                print(f"({A[i]}, {A[j]})", end=' ')
    print()

# 반사
def is_reflexive(matrix) :
    for i in range(len(matrix)) :
        if matrix[i][i] != 1 : return False
    return True

# 비반사
def is_irreflexive(matrix):
    for i in range(len(matrix)) :
        if matrix[i][i] != 0 : return False
    return True

# 대칭
def is_symmetric(matrix) :
    n = len(matrix)
    for i in range(n) :
        for j in range(n) :
            if matrix[i][j] != matrix[j][i] : return False
    return True

# 관계행렬 부울곱 함수
def matrix_mult(A, B) :
    size = len(A)
    result = [[0] * size for _ in range(size)]
    for i in range(size) :
        for j in range(size) :
            for k in range(size) :
                result[i][j] |= A[i][k] & B[k][j]
    return result

# 추이 관계 여부 판별 함수
def is_transitive(matrix) :
    size = len(matrix)
    power = [row[:] for row in matrix] # M^1
    combined = [row[:] for row in matrix] # 누적 합집합
    
    for _ in range(1, size) : # 최대 size - 1제곱까지 확인
        power = matrix_mult(power, matrix)
        for i in range(len(matrix)) :
            for j in range(len(matrix)) :
                combined[i][j] |= power[i][j]
    return combined == matrix

# 반사 폐포
def reflexive_closure(matrix) :
    n = len(matrix)
    result = [row[:] for row in matrix]
    for i in range(n) : result[i][i] = 1
    return result

# 대칭 폐포
def symmetric_closure(matrix) :
    n = len(matrix)
    result = [row[:] for row in matrix]
    for i in range(n) :
        for j in range(n) :
            if matrix[i][j] == 1 : result[j][i] = 1
    return result

# 추이 폐포
def transitive_closure(matrix) :
    size = len(matrix)
    power = [row[:] for row in matrix]
    result = [row[:] for row in matrix]

    for _ in range(1, size) :
        power = matrix_mult(power, matrix)
        for i in range(size) :
            for j in range(size) : result[i][j] |= power[i][j]
    return result

# 동치 확인 후 동치류
def equivalence(x, A, matrix) :
    idx = A.index(x)
    eq = []
    for j in range(len(A)) :
        if matrix[idx][j] == 1 : eq.append(A[j])
    return eq

# 출력
def print_matrix(m) :
    for i in range(len(m)) :
        for j in range(len(m[i])) :
            print(m[i][j], end=' ')
        print()
    print()

# 메인함수
def main() :
    A = [1, 2, 3, 4, 5]
    n = len(A)
    matrix = [[0] * 5 for _ in range(n)]

    print("5x5 관계 행렬을 행 단위로 입력하세요 : ")
    for i in range(n) :
        matrix[i] = list(map(int, input().split()))

    print("\n[관계 R에 포함된 순서쌍]")
    print_relation(matrix, A)

    r = is_reflexive(matrix)
    ir = is_irreflexive(matrix)
    s = is_symmetric(matrix)
    t = is_transitive(matrix)

    print("\n[관계의 성질 판별]")
    print("반사인가? :", "네" if r else "아뇨")
    print("비반사인가? :", "네" if ir else "아뇨")
    print("대칭인가? :", "네" if s else "아뇨")
    print("추이인가? :", "네" if t else "아뇨")

    # 동치 확인
    if r and s and t :
        print("\n이 관계는 동치 관계입니다.\n")
        print("[동치류 출력]")
        for x in A :
            print(f"{x}의 동치류 :", equivalence(x, A, matrix))
        return
    else :
        print("\n이 관계는 동치 관계가 아닙니다.\n")

    print("\n[----폐포 변환----]\n")

    # 반사 폐포
    print("[반사 폐포 변환 전]")
    print_matrix(matrix)

    ref = reflexive_closure(matrix)
    print("[반사 폐포 변환 후]")
    print_matrix(ref)

    # 대칭 폐포
    print("[대칭 폐포 변환 전]")
    print_matrix(matrix)

    sym = symmetric_closure(matrix)
    print("[대칭 폐포 변환 후]")
    print_matrix(sym)

    # 추이 폐포
    print("[추이 폐포 변환 전]")
    print_matrix(matrix)

    tran = transitive_closure(matrix)
    print("[추이 폐포 변환 후]")
    print_matrix(tran)
    
    

    # 동치 확인
    print("\n----세 폐포를 모두 적용한 후 관계----\n")
    final_closure = transitive_closure(symmetric_closure(ref))
    print_matrix(final_closure)

    rr = is_reflexive(final_closure)
    ss = is_symmetric(final_closure)
    tt = is_transitive(final_closure)

    print("[최종 성질 판별]")
    print("반사인가? :", "네" if rr else "아뇨")
    print("대칭인가? :", "네" if ss else "아뇨")
    print("추이인가? :", "네" if tt else "아뇨")

    if rr and ss and tt :
        print("\n최종적으로 동치 관계입니다.\n")
        print("[동치류 출력]")
        for x in A :
            print(f"{x}의 동치류 :", equivalence(x, A, final_closure))
    else :
        print("\n최종적으로 동치 관계가 아닙니다.\n")
    return


# 실행
if __name__ == "__main__" :
    main()
