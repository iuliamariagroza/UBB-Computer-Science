import math

def is_perfect_square(x):
    """
    Check if a number is a perfect square.

    :param x: Input number
    :return: Tuple (True, sqrt(x)) if x is a perfect square, (False, None) otherwise
    """
    if x < 0:
        return False, None
    sqrt_x = math.isqrt(x)
    if sqrt_x * sqrt_x == x:
        return True, sqrt_x
    return False, None

def calculate_t_differences_and_check_square(n):
    """
    Calculate t^2 - n for t = t0, t0+1, ..., t0+20, where t0 = [sqrt(n)],
    check if the result is a perfect square, and print the square root, t+s, and t-s.

    :param n: Input number
    :return: None (prints the results)
    """
    # Calculate t0 = [sqrt(n)]
    t0 = math.floor(math.sqrt(n))

    print(f"t0 = [sqrt({n})] = {t0}\n")
    print("Calculating t^2 - n and checking if it is a perfect square:\n")

    # Compute and print t^2 - n and check if it's a perfect square
    for i in range(21):
        t = t0 + i
        diff = t**2 - n
        is_square, sqrt_val = is_perfect_square(diff)
        if is_square:
            t_plus_s = t + sqrt_val
            t_minus_s = t - sqrt_val
            print(f"t = {t}, t^2 - n = {diff}, Perfect square: Yes, s = {sqrt_val}, t+s = {t_plus_s}, t-s = {t_minus_s}")
        else:
            print(f"t = {t}, t^2 - n = {diff}, Perfect square: No")

# Example usage
n = 7293
calculate_t_differences_and_check_square(n)
