M = readtable( "data copy.csv" );

% delay time
x = M.ArrivalsAtMachine - M.Arrivals; 

meanX = mean(x);
SEM = std(x)/sqrt(length(x));               % Standard Error
ts = tinv([0.05  0.95],length(x)-1);      % T-Score
CI = mean(x) + ts*SEM ; 

% https://www.google.com/search?q=confidence+interval+formula&oq=confidence+interval+&aqs=chrome.2.69i57j0i67l4j69i60l3.5344j0j7&sourceid=chrome&ie=UTF-8
% 95% confidence level value : 1.96

CI95 = [mean(x) - 1.96*(std(x)./sqrt(numel(x))), mean(x) + 1.96*(std(x)./sqrt(numel(x)))];
