function I=romberg(f,a,b,error,n_max)
  h=b-a;
  R=zeros(n_max, n_max);
  R(1,1)=(h/2)*(f(a)+f(b));
  for k=2:n_max
    h=h/2;
    num_new=2^(k - 2);
    sum_new=0;
    for i=1:num_new
      x=a+(2*i-1)*h;
      sum_new=sum_new+f(x);
    end
    R(k,1)=0.5*R(k-1,1)+h*sum_new;
    for j=2:k
      R(k,j) = (4^(j-1)*R(k,j-1)-R(k-1,j-1))/(4^(j-1) - 1);
    end
    if abs(R(k,k)-R(k-1,k-1))<error
      I=R(k,k);
      return;
    end
  end

  I=R(n_max,n_max);
end
