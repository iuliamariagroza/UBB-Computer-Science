%ex 5
function f=CNLab1ex5(n)
   if n==0
        f=1+1;
   else
        f=1+1/CNLab1ex5(n-1);
   end
end
