##Efficient Representations of Multiple Service Policies
This repository contains code to reproduce simulation in the INFOCOM 2018 submission "Efficient Representations of Multiple Service Policies".



To compile code go to the folder `src/` and run `javac Runner.java`
Run experiments with command `java Runner`, it will produce results on 100 instances with same parameters into file `ans.txt`.
You can modify parameters directly in code, modifying file `Params.java`.
To calculate average ratio between overhead and lower bound compile `StatCalculator.java` and run `java StatCalculator < ans.txt` where `ans.txt` is file produced by runner.
