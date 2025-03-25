%ex1
n=7;
A=5*eye(n)-diag(ones(1,n-1),1)-diag(ones(1,n-1),-1);
b=[4,3*ones(1,n-2),4]';
maxnit=1000;
x0=zeros(size(b));
err=10^(-5);

[x,nit]=jacobi(A,b,x0,err,maxnit)
