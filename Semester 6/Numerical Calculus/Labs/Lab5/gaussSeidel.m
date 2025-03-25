function [x,nit] = gaussSeidel(A,b,x0,err,maxnit)
 D=diag(diag(A));
 L=-tril(A,-1);
 U=-triu(A,1);
 M=D-L;
 N=U;
 T=inv(M)*N;
 c=inv(M)*b;
 x=x0;
 for k=1:maxnit
     x_old=x;
     x=T*x_old+c;
     if norm(x-x_old,inf) <= err*(1-norm(T,inf))/norm(T,inf)
      nit=k;
      return;
     end
 end
 error('no solution');
end
