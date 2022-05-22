M = readtable( "data.csv" );
R = readtable("dataReg.csv");
T = readtable("dataT.csv");
x = M.ArrivalsAtMachine - M.Arrivals; 
% mean(x)
SEM = std(x)/sqrt(length(x));               % Standard Error
ts = tinv([0.05  0.95],length(x)-1);      % T-Score
CI = mean(x) + ts*SEM ; 
delay =M.Departures - M.ArrivalsAtMachine;
delayReg = R.Departures - R.ArrivalsAtMachine;
delayT= T.Departures - T.ArrivalsAtMachine;
figure 
hist(delayT )
title("delay service cust")
figure 
hist(delayReg)
title("delay regular cust ")
