function [m,CI] = CIplot(input,n, tScore, name)
%CIplot calculate mean confidence interval and plot it
m = mean(input);
s = sum((input - m).^2) ./ (n-1) ;
CI = [m- tScore*(sqrt(s./n)), m + tScore*(sqrt(s./n))];

figure 
histogram(input)
hold on 
ylim = get(gca, 'YLim');
plot(m*[1,1],ylim*1.05,'r-','LineWidth',2);
plot(CI(1)*[1,1],ylim*1.05,'g-','LineWidth',2);
plot(CI(2)*[1,1],ylim*1.05,'g-','LineWidth',2);
title(name)
end

