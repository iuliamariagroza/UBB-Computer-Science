%ex1 with jacobi
n=7;
A=5*eye(n)-diag(ones(1,n-1),1)-diag(ones(1,n-1),-1);
b=[4,3*ones(1,n-2),4]';
maxnit=1000;
x0=zeros(size(b));
err=10^(-5);

[x,nit]=jacobi(A,b,x0,err,maxnit);

%ex1 with gauss
n=7;
A=5*eye(n)-diag(ones(1,n-1),1)-diag(ones(1,n-1),-1);
b=[4,3*ones(1,n-2),4]';
maxnit=1000;
x0=zeros(size(b));
err=10^(-5);

[x,nit]=gaussSeidel(A,b,x0,err,maxnit);

%ex2,a
A = [10, 7, 8, 7; 7, 5, 6, 5; 8, 6, 10, 9; 7, 5, 9, 10];
b = [32, 23, 33, 31]';
x=inv(A)*b;

%ex2,b
b1=[32.1,22.9,33.1,30.9]';
x1=inv(A)*b1
input_error=norm(b-b1)/norm(b)
output_error=norm(x-x1)/norm(x)


%ex2,c
A1 = [10, 7, 8.1, 7.2; 7.8, 5.04, 6, 5; 8, 5.98, 9.89, 9; 6.99, 4.99, 9, 9.98];
x2=inv(A1)*b
input_errorc=norm(A-A1)/norm(A)
output_errorc=norm(x-x2)/norm(x)

%ex2,d
condA=norm(A)*norm(inv(A))
