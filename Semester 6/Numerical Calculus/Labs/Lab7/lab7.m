%ex1 c
x=linspace(0,1);
x0=[0,1/3,1/2,1];
f= @(x) cos(pi*x);
plot(x, f(x))
hold on
plot(x,newton(x0, f(x0),x))

%ex1 d
x=1/5;
newton(x0, f(x0),x)

%ex2
x01=linspace(-4,4,9);
f1= @(x) 2.^x;
x1=1/2;
aitken(x01, f1(x01),x1)

%ex3
x0=[1000, 1010, 1020, 1030, 1040, 1050];
f0=[3.0000000, 3.0043214, 3.0086002, 3.0128372, 3.0170333, 3.0211893];
x=[1001:1009];
newton(x0, f0, x)
