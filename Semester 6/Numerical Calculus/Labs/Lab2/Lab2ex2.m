%ex2 a
clf
syms x
fs=sin(x);
fs3=taylor(fs,x,0,'order',4);
fs5=taylor(fs,x,0,'order',6);


fplot(fs,[-pi,pi])
hold on
fplot(fs3,[-pi,pi])
hold on
fplot(fs5,[-pi,pi])

%b
vpa(sin(pi/5),6)
