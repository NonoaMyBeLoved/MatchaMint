# 행렬을 예쁘게 출력
def matrixout(mx, size) :
    print("┌" + "        " * size + " ┐")
    for i in range(size) :
        print("|", end=" ")
        for j in range(size) : print("%7.3f" % mx[i][j], end=" ")
        print("|")
    print("└" + "        " * size + " ┘")

# 전치 행렬 구하기
def transposeMatrix(m) :
    return [[m[j][i] for j in range(len(m))] for i in range(len(m[0]))]

# 소행렬 구하기
def getMatrixMinor(m, i, j) :
    return [row[:j] + row[j+1:] for row in (m[:i] + m[i+1:])]

# 행렬식 계산 (재귀)
def getMatrixDeterminant(m) :
    if len(m) == 1 : return m[0][0]
    if len(m) == 2 : return (m[0][0] * m[1][1]) - (m[0][1] * m[1][0])
    determinant = 0
    for c in range(len(m)) : determinant += (((-1)**c) * m[0][c] * getMatrixDeterminant(getMatrixMinor(m, 0, c)))
    return determinant

# 역행렬이 존재하는지 확인
def has_inverse(m) :
    determinant = getMatrixDeterminant(m)
    if abs(determinant) < 1e-10:
        print("오류! 해당 행렬의 행렬식은 0입니다.")
        print("역행렬 계산은 불가능합니다!")
        return False
    return True

# 직교행렬 확인
def isOrthogonal(m, tol = 1e-10) :
    ot = transposeMatrix(m)
    mul = [[sum(ot[i][k] * m[k][j] for k in range(len(m))) for j in range(len(m))] for i in range(len(m))]
    I = [[float(i == j) for j in range(len(m))] for i in range(len(m))]
    for i in range(len(m)) :
        for j in range(len(m)) :
            if abs(mul[i][j] - I[i][j]) > tol : return False
    return True

# 역행렬 계산
def getMatrixInverse(m) :
    determinant = getMatrixDeterminant(m)
    if len(m) == 1 : return [[1.0 / m[0][0]]]
    if len(m) == 2 : return [[m[1][1] / determinant, (-1 * m[0][1]) / determinant], [-1*m[1][0] / determinant, m[0][0] / determinant]]
    cofactors = []
    for r in range(len(m)) :
        cofactorRow = []
        for c in range(len(m)) :
            minor = getMatrixMinor(m, r, c)
            cofactorRow.append( ( (-1)**(r+c) ) * getMatrixDeterminant(minor))
        cofactors.append(cofactorRow)
    adjugate = transposeMatrix(cofactors)
    for r in range(len(adjugate)) :
        for c in range(len(adjugate)) : adjugate[r][c] = adjugate[r][c] / determinant
    return adjugate

# 가우스-조던 소거법 역행렬 계산
def getMatrixInverseGaussJordan(m) :
    arr = [m[i][:] + [float(i == j) for j in range(len(m))] for i in range(len(m))]
    for i in range(len(m)) :
        if abs(arr[i][i]) < 1e-10 :
            for j in range(i+1, len(m)) :
                if abs(arr[j][i]) > 1e-10:
                    arr[i], arr[j] = arr[j], arr[i]
                    break
            else : raise ValueError("가우스-조던 역행렬이 존재하지 않습니다.")
        pivot = arr[i][i]
        for k in range(len(arr[i])) : arr[i][k] /= pivot
        for j in range(len(m)) :
            if j != i :
                zero = arr[j][i]
                for k in range(len(arr[j])) : arr[j][k] -= zero * arr[i][k]
    return [inv[len(m):] for inv in arr]

# 두 결과 비교
def compareMatrix(m1, m2, flag = 1e-10) :
    for i in range(len(m1)) :
        for j in range(len(m1)) :
            if abs(m1[i][j] - m2[i][j]) > flag :
                return False
    return True

# ========== 메인 실행부 ==========
def main() :
    try:
        n = int(input("정방행렬의 차수를 입력하세요 : "))
        if n <= 0 :
            print("차수는 양의 정수여야 합니다.")
            return
        print(f"{n}x{n} 정방행렬 A를 입력하세요 (각 행을 공백으로 구분하여 한 줄씩 입력) :")
        matrix_a = []
        for i in range(n) :
            while True :
                try:
                    row_input = input(f"{i+1}행 : ").strip()
                    row_values = [float(x) for x in row_input.split()]
                    if len(row_values) != n :
                        print(f"입력 오류: 정확히 {n}개의 값을 입력해야 합니다.")
                        continue
                    matrix_a.append(row_values)
                    break
                except ValueError :
                    print("입력 오류: 숫자만 입력해주세요.")
        if has_inverse(matrix_a) :
            # 직교행렬 확인 추가
            if isOrthogonal(matrix_a) : print("\n직교행렬입니다.")
            else : print("\n직교행렬이 아닙니다.")
            
            print("\n행렬식 역행렬 결과 :")
            inverse_det = getMatrixInverse(matrix_a)
            matrixout(inverse_det, n)
            print("\n가우스-조던 역행렬 결과 :")
            try:
                inverse_gj = getMatrixInverseGaussJordan(matrix_a)
                matrixout(inverse_gj, n)
                if compareMatrix(inverse_det, inverse_gj) : print("\n두 결과가 같습니다!")
                else : print("\n 두 결과가 다릅니다.")
            except ValueError as e : print(e)
    except ValueError :
        print("입력 오류: 정수를 입력해주세요.")
    except Exception as e :
        print(f"예상치 못한 오류가 발생했습니다 : {e}")
        print()

# 프로그램 시작점
if __name__ == "__main__" : main()