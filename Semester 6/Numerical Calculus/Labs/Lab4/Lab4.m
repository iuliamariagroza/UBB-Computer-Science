%ex1
A = [2 4 2;0 -1 1;0 0 -1];
b = [8;0;-1];
backwards(A,b);

A=[2 1 -1 -2; 4 4 1 3;-6 -1 10 10;-2 1 8 4];
b=[2,4,-5,1]';
gauss_pivot(A,b);

%ex2
n=7;
A=5*eye(n)-diag(ones(1,n-1),1)-diag(ones(1,n-1),-1);
[L,U,P]=lu(A);
b=[4,3*ones(1,n-2),4]';
y=forwards(L, P*b);
x=backwards(U,y)
