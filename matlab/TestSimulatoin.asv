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



