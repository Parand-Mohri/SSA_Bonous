regular = readtable("dataReg.csv");
serviceDesk = readtable("dataT.csv");
overall = readtable("dataO.csv");
arrivalTimeReg = regular.Arrivals;
arrivalTimeT = serviceDesk.Arrivals;
arrivalTimeReg = .Arrivals;



T = readtable("dataT1.csv");
Q = readtable("queueData1.csv");

xArrival = [mean(R1.Arrivals), mean(R2.Arrivals),mean(R3.Arrivals),mean(R4.Arrivals),mean(R5.Arrivals),mean(R6.Arrivals),mean(R7.Arrivals)];
xArrivalMachine = [mean(R1.ArrivalsAtMachine), mean(R2.ArrivalsAtMachine),mean(R3.ArrivalsAtMachine),mean(R4.ArrivalsAtMachine),mean(R5.ArrivalsAtMachine),mean(R6.ArrivalsAtMachine),mean(R7.ArrivalsAtMachine)];
xArrival = xArrival / max(xArrival);
xArrivalMachine = xArrivalMachine / max(xArrivalMachine);

% meanRegArrivals = mean(R.Arrivals);

%%checking if arrivals are poisson distributed 
sortedRArrivales = sort(xArrival) ;
g =  zeros(1, length(xArrival));
for i = 1  : length(xArrival)-1
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
delayR1 =  R1.ArrivalsAtMachine - R1.Arrivals;
delayR1 = delayR1 / max(delayR1);
delayR2 =  R2.ArrivalsAtMachine - R2.Arrivals;
delayR2 = delayR2 / max(delayR2);
delayR3 =  R3.ArrivalsAtMachine - R3.Arrivals;
delayR3 = delayR3 / max(delayR3);
delayR4 =  R4.ArrivalsAtMachine - R4.Arrivals;
delayR4 = delayR4 / max(delayR4);
delayR5 =  R5.ArrivalsAtMachine - R5.Arrivals;
delayR5 = delayR5 / max(delayR5);
delayR6 =  R6.ArrivalsAtMachine - R6.Arrivals;
delayR6 = delayR6 / max(delayR6);
delayR7 =  R7.ArrivalsAtMachine - R7.Arrivals;
delayR7 = delayR7 / max(delayR7);
delayR = [mean(delayR1), mean(delayR2),mean(delayR3),mean(delayR4),mean(delayR5),mean(delayR6),mean(delayR7)];
mDelayR = mean(delayR);

% delayT1 =  T1.ArrivalsAtMachine - T1.Arrivals;
% delayT2 =  T2.ArrivalsAtMachine - T2.Arrivals;
% delayT3 =  T3.ArrivalsAtMachine - T3.Arrivals;
% delayT4 =  T4.ArrivalsAtMachine - T4.Arrivals;
% delayT5 =  T5.ArrivalsAtMachine - T5.Arrivals;
% delayT6 =  T6.ArrivalsAtMachine - T6.Arrivals;
% delayT7 =  T7.ArrivalsAtMachine - T7.Arrivals;
% delayT = [mean(delayT1), mean(delayT2),mean(delayT3),mean(delayT4),mean(delayT5),mean(delayT6),mean(delayT7)];
% mDelayT = mean(delayT);

% delayO = [delayR; delayT];
% mDelayO = mean(delayO);

% mDelayT = mean(delayT);

%%z score for 95 confidence interval = 1.96

sR = sum((delayR - mDelayR).^2) / 6;

CI95R = [mDelayR- 2.447*(sqrt((sR./sqrt(6))/7)), mDelayR + 2.447*(sqrt((sR./sqrt(6))/7))]
% CI95T = [mean(delayT) - 1.96*(std(delayT)./sqrt(numel(delayT))), mean(delayT) + 1.96*(std(delayT)./sqrt(numel(delayT)))];
% CI95O = [mean(delayO) - 1.96*(std(delayO)./sqrt(numel(delayO))), mean(delayO) + 1.96*(std(delayO)./sqrt(numel(delayO)))];

figure 
histogram(delayR, 15)
hold on 
ylim = get(gca, 'YLim');
plot(mean(delayR)*[1,1],ylim*1.05,'r-','LineWidth',2);
plot(CI95R(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(CI95R(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title("delay REGULAR cust")


% figure 
% histogram(delayT, 20)
% hold on 
% ylim = get(gca, 'YLim');
% plot(mean(delayT)*[1,1],ylim*1.05,'r-','LineWidth',2);
% plot(CI95T(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
% plot(CI95T(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
% title("delay service cust")




qR = [Q.Queue1;Q.Queue2;Q.Queue3;Q.Queue4;Q.Queue5;Q.Queue6];
qT = Q.Queue7;
averagesReg = mean(qR);
averagesT = mean(qT);

queueCI95R = [mean(qR) - 1.96*(std(qR)./sqrt(numel(qR))), mean(qR) + 1.96*(std(qR)./sqrt(numel(qR)))];
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



