function N=newton(x0,f0,x)
d=divdiff(x0, f0);
n=zeros(size(x));  
for i=1:length(x0)
    p=d(1,i);       
    for j=1:(i-1) 
      p=p.*(x-x0(j)); 
    end
    n=n+p;       
  end

  N = n;
end
