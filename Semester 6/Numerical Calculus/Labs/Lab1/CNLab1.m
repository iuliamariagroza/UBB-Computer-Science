%ex1 a
x=-4:0.1:7.2
px=x.^5-5*x.^4-16*x.^3+16*x.^2-17*x+21
plot(x,px)

%ex1 b
p=[1,-5,-16,16,-17,21] 
polyval(p,-2.5)

%ex1 c
roots(p)

%ex2
x=0:0.1*pi:2*pi
f=sin(x)
g=sin(2*x)
h=sin(3*x)
subplot(3,1,1) 
plot(x,f)
subplot(3,1,2)
plot(x,g)
subplot(3,1,3)
plot(x,h)

%ex3
t=0:0.1*pi:10*pi
R=3.8
r=1
xt=(R+r)*cos(t)- r*cos((R/r+1)*t)
yt=(R+r)*sin(t)- r*sin((R/r+1)*t)
clf
plot(xt,yt)

%ex4
%using mesh
[x,y]=meshgrid(-2:0.1:2, 0.5:0.1:4.5)
z=sin(exp(x)).*cos(log(y))
mesh(x,y,z)
