
M = readtable( "data.csv" );
R = readtable("dataReg.csv");
T = readtable("dataT.csv");

meanRegArrivals = mean(R.Arrivals);
%%checking if arrivals are poisson distributed 
sortedRArrivales = sort(R.Arrivals);
g =  zeros(1, length(sortedRArrivales));
for i = 1  : length(sortedRArrivales)-1
    g(i) = sortedRArrivales(i+1) - sortedRArrivales(i);
end 
figure 
% histogram(g)
% hold on
x = (0:1:650);
p = poisspdf(x, 60);
% plot(x,p, 'g-','LineWidth',2)
histogram(p)
title("regular arrivals")



%%
delayReg =  R.ArrivalsAtMachine - R.Arrivals;
delayT=  T.ArrivalsAtMachine - T.Arrivals;

%%z score for 95 confidence interval = 1.96
CI95 = [mean(delayT) - 1.96*(std(delayT)./sqrt(numel(delayT))), mean(delayT) + 1.96*(std(delayT)./sqrt(numel(delayT)))];

figure 
histogram(delayT, 15)
hold on 
ylim = get(gca, 'YLim');
plot(mean(delayT)*[1,1],ylim*1.05,'r-','LineWidth',2);
plot(CI95(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(CI95(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title("delay service cust")

figure 

histogram(delayReg)
title("delay regular cust ")



y = exppdf(x,meanREg);






% https://www.google.com/search?q=confidence+interval+formula&oq=confidence+interval+&aqs=chrome.2.69i57j0i67l4j69i60l3.5344j0j7&sourceid=chrome&ie=UTF-8
% 95% confidence level value : 1.96
sortedT = sort(T.Arrivals);
figure 
histogram(sortedT)
title("arrival for ta")


% figure
% histogram(delayT)
% hold on 
% ylim = get(gca, 'YLim');
% plot(mean(delayT)*[1,1],ylim*1.05,'r-','LineWidth',2);
% plot(CI95(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
% plot(CI95(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
% hold off
% title("delay time with confidence ")

