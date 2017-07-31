**Efficient Representations of Multiple Service Policies

This repository contains code to reproduce simulation in the INFOCOM 2018 submission "Efficient Representations of Multiple Service Policies".

*Run and compile: 

`cd src/` then `javac Runner.java` and then `java Runner`. It will simualate all algorithms on 100 instances.

*Calculate statistics:

`javac StatCalculator.java` and then `java StatCalculator < ans.txt`. It will calculate average ratio of overhead against lower bound,  where `ans.txt` is file produced by runner.
