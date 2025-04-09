function A=aitken(x0,f0,x);
 p=zeros(length(x0));
 p(:,1)=f0';
 n=length(x0);
 for i=2:n
     for j=2:i
         p(i,j)=(1/(x0(i)-x0(i-j+1)))*((x-x0(i-j+1))*p(i,j-1)-(x-x0(i))*p(i-1,j-1));
     end   
 end 
 A=p(n,n);
end
