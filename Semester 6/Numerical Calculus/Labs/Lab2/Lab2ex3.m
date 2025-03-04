%ex3 a
clf
syms x
f = log(1+x);
f3=taylor(f,x,0,'order',3);
f5=taylor(f,x,0,'order',6);

fplot(f,[-0.9,1])
hold on
fplot(f3,[-0.9,1])
hold on
fplot(f5,[-0.9,1])

%ex3 b
vpa(log(2),6)
f100=taylor(f,x,0,'order',101);
vpa(subs(f100,x,1),6)

%ex3 c
f1=log(1-x);
flog=taylor(f1,x,0,'order',101)

%ex3 d
f3=f100-flog

%ex3 e
vpa(subs(f3,x,1),6)
