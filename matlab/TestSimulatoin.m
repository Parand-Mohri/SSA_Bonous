%This data represents the averages of already 101 simulations runned of the
%same system( please do not misconfuse the header of Customers of the csv
%file since this is just because we did not change the headers string when
%we computed this data 

regular = readtable("dataReg.csv");
serviceDesk = readtable("dataT.csv");
overall = readtable("dataO.csv");
queue = readtable("queueData.csv");

arrivalTimeReg = regular.Arrivals;
arrivalTimeT = serviceDesk.Arrivals;
arrivalTimeO = overall.Arrivals;

machineTimeReg = regular.ArrivalsAtMachine;
machineTimeT = serviceDesk.ArrivalsAtMachine;
machineTimeO = overall.ArrivalsAtMachine;

%%given that we performed 101 simulations and then we recorded the each of
%%the averages of teh arrivals, at machine and departure we can say that
%%we have 100 degrees of freedom, and we have 0.95 as significance level,
%%so looking it up in the t-test student score test (its the one he uses in
%%the lecture videos) we obtained the coeff 1.066

n = 101;


delayR =  machineTimeReg - arrivalTimeReg;
[mR, CIR] = CIplot(delayR,n, 1.066, "delay regular customer");

delayT =  machineTimeT - arrivalTimeT;
[mT, CIT] = CIplot(delayT,n, 1.066, "delay service desk customer");

delayO =  machineTimeO - arrivalTimeO;
[mO, CIO] = CIplot(delayO,n, 1.066, "delay overal customer");

qR = queue.Var2;
[mqR, CIqr] = CIplot(qR,n, 1.066, "queue length regular desk");

qT = queue.Var3;
[mqT, CIqt] = CIplot(qT,n, 1.066, "queue length service desk");

qO = queue.Var4;
[mqO, CIqo] = CIplot(qO,n, 1.066, "queue length overall");



