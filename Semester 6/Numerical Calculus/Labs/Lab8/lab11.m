%ex3, lab 10
f=@(x) 2/sqrt(pi)*exp(-x.^2);
a=0;
error=0.01;
m=4;

for x=0.1:0.1:1
    I = ad_trapezoid(f,a,x,error,m);
    I_second = integral(f,a,x);
end

%ex1, lab 11
f1=@(x) 1/(2+sin(x));
n_max=10;
b=pi/2;
I=romberg(f1,a,b,error,n_max);

@ex3, lab 11
f2=@(x) exp(cos(x));
J=build_Jm(5)
eigenvalues = eig(J)
