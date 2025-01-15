import random
from math import gcd

ALPHABET = "  abcdefghijklmnopqrstuvwxyz"
CHAR_TO_INDEX = {char: i for i, char in enumerate(ALPHABET)}
INDEX_TO_CHAR = {i: char for i, char in enumerate(ALPHABET)}

def is_coprime(a, b):
    """Check if a and b are coprime."""
    return gcd(a, b) == 1


def mod_inverse(a, m):
    """Calculate the modular inverse of a under modulo m."""
    for x in range(1, m):
        if (a * x) % m == 1:
            return x
    raise ValueError("Modular inverse does not exist")


def generate_superincreasing_sequence(n):
    """Generate a superincreasing sequence of length n."""
    sequence = []
    total = 0
    for _ in range(n):
        next_value = total + random.randint(1, 10)
        sequence.append(next_value)
        total += next_value
    return sequence


def generate_keys(n):
    """Generate public and private keys."""
    private_key = generate_superincreasing_sequence(n)
    total_sum = sum(private_key)
    modulus = total_sum + random.randint(1, 10)
    multiplier = random.randint(2, modulus - 1)
    while not is_coprime(multiplier, modulus):
        multiplier = random.randint(2, modulus - 1)

    public_key = [(multiplier * x) % modulus for x in private_key]
    return public_key, private_key, modulus, multiplier


def validate_plaintext(plaintext):
    """Validate that the plaintext contains only valid characters."""
    for char in plaintext:
        if char.lower() not in CHAR_TO_INDEX:
            raise ValueError(f"Invalid character in plaintext: {char}")
    return plaintext.lower()


def encrypt(plaintext, public_key):
    """Encrypt plaintext using the public key."""
    plaintext = validate_plaintext(plaintext)
    binary_representation = []
    for char in plaintext:
        binary_representation.append(format(CHAR_TO_INDEX[char],
                                            f'0{len(public_key)}b'))  

    ciphertext = []
    for binary in binary_representation:
        ciphertext.append(sum(int(bit) * pk for bit, pk in zip(binary, public_key)))

    return ciphertext


def validate_ciphertext(ciphertext):
    """Validate that the ciphertext contains valid values."""
    if not all(isinstance(c, int) for c in ciphertext):
        raise ValueError("Ciphertext must be a list of integers.")
    if any(c < 0 for c in ciphertext):
        raise ValueError("Ciphertext contains negative values, which are invalid.")
    return ciphertext


def decrypt(ciphertext, private_key, modulus, multiplier):
    """Decrypt ciphertext using the private key."""
    inverse_multiplier = mod_inverse(multiplier, modulus)
    plaintext = ""

    for value in ciphertext:
        reduced_value = (value * inverse_multiplier) % modulus
        binary = ""
        for weight in reversed(private_key):
            if weight <= reduced_value:
                binary = "1" + binary
                reduced_value -= weight
            else:
                binary = "0" + binary

        index = int(binary, 2)
        plaintext += INDEX_TO_CHAR[index]

    return plaintext


if __name__ == "__main__":
    n = 8
    public_key, private_key, modulus, multiplier = generate_keys(n)

    print("Public Key:", public_key)
    print("Private Key:", private_key)
    print("Modulus:", modulus)
    print("Multiplier:", multiplier)

    plaintext = "Hello"
    try:
        validate_plaintext(plaintext)
        print("Plaintext:", plaintext)
        print("Plaintext is valid.")
    except ValueError as e:
        print("Plaintext validation error", e)

    ciphertext = encrypt(plaintext, public_key)
    print("Ciphertext:", ciphertext)

    try:
        validate_ciphertext(ciphertext)
        print("Ciphertext is valid.")
    except ValueError as e:
        print("Ciphertext validation error:", e)

    decrypted_text = decrypt(ciphertext, private_key, modulus, multiplier)
    print("Decrypted Text:", decrypted_text)
