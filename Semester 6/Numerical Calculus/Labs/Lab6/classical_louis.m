function L=classical_louis(x0,f0,x)
 L=zeros(size(x));
 l=zeros(size(x0));
 for i=1:length(x)
     for j=1:length(x0)
         l(j)=prod(x(i)-x0([1:j-1,j+1:length(x0)])) / prod(x0(j)-x0([1:j-1,j+1:length(x0)]));
     end
     L(i)= l*f0';
 end   
end
