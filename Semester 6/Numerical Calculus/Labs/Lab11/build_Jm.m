function J = build_Jm(m)
    beta=zeros(m,1);
    beta(1)=2; 
    for k=1:m-1
        beta(k+1)=1/(4-1/(k^2));
    end
    J=zeros(m);
    for i=1:m
        J(i,i)=0; 
        if i<m
            J(i,i+1)=sqrt(beta(i+1));
            J(i+1,i)=sqrt(beta(i+1)); 
        end
    end
    J
end

