function L=barycentric_louis(x0,f0,x)
 L=zeros(size(x));
 w=ones(1,length(x0));

 for i=1:length(x)
     for j=[1:i-1, i+1:length(x0)]
         w(i)= 1 / ((x0(i)-x0(j)));
     end
 end 

 for i=1:length(x)
     numerator = 0;
     denominator = 0;

     for j=1:length(x0)
         ux = w(i)/ (x(i)-x0(j));
         numerator = numerator + ux * f0(i);
         denominator = denominator + ux;
     end
     L(i) = numerator / denominator;
 end
end
