
M = readtable( "data.csv" );
R = readtable("dataReg.csv");
T = readtable("dataT.csv");
Q = readtable("queueData.csv");

meanRegArrivals = mean(R.Arrivals);

%%checking if arrivals are poisson distributed 
sortedRArrivales = sort(R.Arrivals);
g =  zeros(1, length(sortedRArrivales));
for i = 1  : length(sortedRArrivales)-1
    g(i) = sortedRArrivales(i+1) - sortedRArrivales(i);
end 
% figure 
% % histogram(g)
% % hold on
% x = (0:1:650);
% p = poisspdf(x, 60);
% % plot(x,p, 'g-','LineWidth',2)
% histogram(p)
% title("regular arrivals")



%%
delayR =  R.ArrivalsAtMachine - R.Arrivals;
delayT=  T.ArrivalsAtMachine - T.Arrivals;
delayO = [delayR; delayT];

%%z score for 95 confidence interval = 1.96

CI95R = [mean(delayR) - 1.96*(std(delayR)./sqrt(numel(delayR))), mean(delayR) + 1.96*(std(delayR)./sqrt(numel(delayR)))];
CI95T = [mean(delayT) - 1.96*(std(delayT)./sqrt(numel(delayT))), mean(delayT) + 1.96*(std(delayT)./sqrt(numel(delayT)))];
CI95O = [mean(delayO) - 1.96*(std(delayO)./sqrt(numel(delayO))), mean(delayO) + 1.96*(std(delayO)./sqrt(numel(delayO)))];

figure 
histogram(delayR, 15)
hold on 
ylim = get(gca, 'YLim');
plot(mean(delayR)*[1,1],ylim*1.05,'r-','LineWidth',2);
plot(CI95R(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(CI95R(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title("delay REGULAR cust")


figure 
histogram(delayT, 20)
hold on 
ylim = get(gca, 'YLim');
plot(mean(delayT)*[1,1],ylim*1.05,'r-','LineWidth',2);
plot(CI95T(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(CI95T(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title("delay service cust")




qR = [Q.Queue1;Q.Queue2;Q.Queue3;Q.Queue4;Q.Queue5;Q.Queue6];
qT = Q.Queue7;
averagesReg = mean(qR);
averagesT = mean(qT);

queueCI95R = [mean(qR) - 1.96*(std(qR)./sqrt(numel(qR))), mean(qR) + 1.96*(std(qR)./sqrt(numel(qR)))]
queueCI95T = [mean(qT) - 1.96*(std(qT)./sqrt(numel(qT))), mean(qT) + 1.96*(std(qT)./sqrt(numel(qT)))];

figure 
histogram(qR)
hold on 
ylim = get(gca, 'YLim');
plot(averagesReg*[1,1],ylim*1.05,'r-','LineWidth',2);
plot(queueCI95R(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(queueCI95R(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title("average queue length for regular cashiers")


figure 
histogram(qT)
hold on 
ylim = get(gca, 'YLim');
plot(averagesT*[1,1],ylim*1.05,'r-','LineWidth',3);
plot(queueCI95T(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(queueCI95T(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title("average queue length for the service desk")

% figure 
% scatterplot(delayT)
% title('dela')
% 
%one column is for customers
% lengths = zeros(size(Q, 2)) -1;
% for i = 2 : length(lengths)
%     h = Q(:,i);
%     sum = sum(h);
%     lengths(i) = sum;
% end 



