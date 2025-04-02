%ex1b
x0=linspace(-2,4,10);
x=linspace(-2,4,500);
f = @(x) (x + 1) ./ (3 * x .^ 2 + 2 * x + 1);

number = classical_louis(x0,f(x0),x);
plot(x, number)

%ex1c
f1=f(x);
error=abs(f1-number);
plot(x,error)

%ex3
x0=[115,119,121];
f0=sqrt(x0);
ex3 = classical_louis(x0,f0, 118);

%ex2
x0=[1980, 1990, 2000, 2010, 2020];
f0=[4451, 5287, 6090, 6970, 7821];
barycentric_louis(x0,f0,2005)
