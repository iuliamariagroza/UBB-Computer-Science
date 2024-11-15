from math import gcd

def calculate_sequence_and_gcds(n, f, x0, steps=20):
    sequence = [x0]  # Initialize the sequence with the starting value

    # Generate the sequence
    for _ in range(steps):
        x_next = f(sequence[-1]) % n
        sequence.append(x_next)

    # Calculate the GCDs for the specified pairs
    gcds = []
    for i in range(1, 11):  # Generate indices for x2-x1, x4-x2, ..., x20-x10
        diff = abs(sequence[2 * i] - sequence[i])  # Calculate |x(2*i) - x(i)|
        gcd_value = gcd(diff, n)  # Calculate GCD(|x(2*i) - x(i)|, n)
        gcds.append(gcd_value)

    return sequence, gcds

# Define the function f(x) = x^2 + x + 1
def f(x):
    return x**2 + 1

# Input parameters
n = 8903
x0 = 2

# Calculate the sequence and the GCDs
sequence, gcds = calculate_sequence_and_gcds(n, f, x0)

# Print the sequence
print("Sequence:")
for i, x in enumerate(sequence):
    print(f"x{i} = {x}")

# Print the GCDs
print("\nGCDs:")
for i, gcd_value in enumerate(gcds, start=1):
    print(f"GCD(|x{2*i} - x{i}|, n) = {gcd_value}")
