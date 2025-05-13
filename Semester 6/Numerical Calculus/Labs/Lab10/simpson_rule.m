function I = simpson_rule(f, a, b, n)
    h = (b - a) / (2 * n);
    I = h / 3 * (f(a) + f(b) + 4 * sum(f((2 * [1 : n] - 1) * h + a)) + 2 * sum(f(2 * [2 : (n - 1)] * h + a)));
endfunction
