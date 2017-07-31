# Efficient Representations of Multiple Service Policies
This repository contains the code to reproduce the experimental evaluation in the INFOCOM 2018 submission "Efficient Representations of Multiple Service Policies".

## Run and compile: 

`cd src/` then `javac Runner.java` and then `java Runner`. It simulates all algorithms on 100 random instances.

## Parameters
`Params.java` - file with parameters. Change it to obtain different experiments.

## Calculate statistics:

`javac StatCalculator.java` and then `java StatCalculator < ans.txt`. It will calculate average ratio of overhead against lower bound,  where `ans.txt` is file produced by `Runner`.
