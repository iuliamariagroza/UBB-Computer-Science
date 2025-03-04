%ex1 a
clf
syms x
f= exp(x)
p1=taylor(f,x,0,'order',2)
p2=taylor(f,x,0,'order',3)
p3=taylor(f,x,0,'order',4)
p4=taylor(f,x,0,'order',5)

fplot(f, [-3,3])
hold on
fplot(p1,[-3,3])
hold on
fplot(p2,[-3,3])
hold on
fplot(p3,[-3,3])
hold on
fplot(p4,[-3,3])

%ex1 b
vpa(exp(1),7)
vpa(subs(p4,x,1),7)

%ex2
