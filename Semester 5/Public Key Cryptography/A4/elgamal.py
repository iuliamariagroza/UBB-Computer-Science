import random

ALPHABET = ' abcdefghijklmnopqrstuvwxyz'
CHUNK_LENGTH = 3
ALPHABET_LENGTH = len(ALPHABET)

"""
KEY GENERATION
"""


def keys_generator():
    p = get_random_prime(10 ** 7)
    print("generated prime is=" + str(p))

    g = get_random_generator(p)
    print("generator g is=" + str(g))

    a = get_int_less_than(p)  # private key

    ga = pow(g, a, p)
    print("g^a mod p is=" + str(ga))

    public_key = (p, g, ga)  # public key
    return public_key, a


def get_random_prime(bound):
    primes = sieve(bound)
    return random.choice(primes)


"""ciurul lui Eratostene - nr prime pana la un bound specificat"""


def sieve(bound):
    # Create a boolean array for marking non-prime numbers

    marked = [False] * (bound + 1)  # Primes are False, non-primes are True
    primes = []

    # Start from the smallest prime, 2
    for i in range(2, bound + 1):
        if not marked[i]:
            primes.append(i)
            # Mark all multiples of i as non-prime
            for j in range(i * i, bound + 1, i):
                marked[j] = True

    # Return primes starting from the 10001st prime
    return primes[10000:]


def get_random_generator(n):
    return random.choice(generators(n))


def generators(n):
    gens = []
    for g in range(2, n):
        if gcd(g, n) == 1:
            gens.append(g)
    return gens


def get_int_less_than(p):
    return random.randint(1, p - 2)


"""
ENCRYPTION
"""


def validate(message):
    for char in message:
        if char not in ALPHABET:
            return False
    return True


def convert_characters_to_number(characters):
    number = 0
    power = 1  # for units, tens, hundreds etc
    for char in reversed(characters):  # iterates in a way similar to binary
        number += power * (ALPHABET.find(char) + 1)
        power *= (ALPHABET_LENGTH + 1)  # position given in my numeral system: base 27
    return number


def gcd(a, b):
    while b:
        r = a % b
        a = b
        b = r
    return a


def convert_number_to_characters(number):
    characters = ""
    while number:
        characters = ALPHABET[number % (ALPHABET_LENGTH + 1) - 1] + characters
        number //= (ALPHABET_LENGTH + 1)
    return characters


def generate_random_text():
    tests = []
    for i in range(25):
        length = random.randint(5, 10)
        message = ""
        for _ in range(length):
            message += random.choice(ALPHABET)
        tests.append(message)
    return tests


def main():
    import sys
    args = sys.argv[1:]
    initial_message = ""
    if not args:
        initial_message = "el gamal"
    elif args[0] == "-m":
        initial_message = " ".join(args[1:])

    print("message:" + initial_message)

    if not validate(initial_message):
        print("Invalid characters in input message. Please only use lowercase alphabet and spaces.")
        assert False

    """
    KEY GENERATOR
    """
    public_key, private_key = keys_generator()
    print("public key=" + str(public_key))
    print("private key=" + str(private_key))
    p = public_key[0]
    g = public_key[1]
    ga = public_key[2]
    a = private_key

    """
    BREAKS MESSAGE INTO CHUNKS FOR EFFICIENCY AND TO STAY WITHIN BOUNDS OF P
    """
    decrypted_text = ""
    chunks = [initial_message[i:i + CHUNK_LENGTH] for i in range(0, len(initial_message), CHUNK_LENGTH)]

    for c in chunks:
        """
        ENCRYPTION
        """
        nr = convert_characters_to_number(c)
        k = get_int_less_than(p)
        alpha = pow(g, k, p)
        beta = nr * pow(ga, k, p) % p
        cyphertext = (alpha, beta)
        print("Cypher text is: " + str(cyphertext))

        """
        DECRYPTION
        """

        # Fermat Little Theorem: alpha ^ (-a) = alpha ^ (p-1-a) (mod p0, where
        # m = alpha ^ (-a) * beta mod p
        decrypted_number = pow(alpha, p - 1 - a, p) * beta % p
        decrypted_text += convert_number_to_characters(decrypted_number)

    print("decrypted message:" + decrypted_text)
    assert decrypted_text == initial_message


main()
