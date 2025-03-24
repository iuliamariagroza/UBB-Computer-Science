%ex1a
x=[0,1,2];
f=1./(1+x);
t=divdiff(x,f);

%ex1b
df=-1./(1+x).^2;
[z,t2]=divdiff2(x,f,df);

%ex1c
x=linspace(1,2,11);
f=1./(1+x);
df=-1./(1+x).^2;
t3=divdiff(x,f)
[z,t4]=divdiff2(x,f,df)

