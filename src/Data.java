import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Data {
    Random rnd = new Random();

    ArrayList<Class> classes;
    ArrayList<Policy> policies;

    public Data(Policy p, ArrayList<Class> classes)
    {
        policies = new ArrayList<>(Arrays.asList(p));
        this.classes = new ArrayList<>(classes);
    }

    public Data(Params params) {
        classes = new ArrayList<>();
        policies = new ArrayList<>();
        for (int i = 0; i < params.numberOfClasses; i++) {
            classes.add(new Class(classes.size(), params.minNumberOfRules + rnd.nextInt(params.maxNumberOfRules - params.minNumberOfRules)));
        }


        ArrayList<ArrayList<Class>> orders = new ArrayList<>();
        for (int kl = 0; kl < params.numberOfPolicies; kl++) {
            ArrayList<Class> test = new ArrayList<>(classes);
            Collections.shuffle(test, rnd);
            orders.add(new ArrayList<>(test.subList(0, params.numberOfClassesInEachPolicy)));
        }


        int k = params.numberOfMaximumIntersectionsForClass;
        boolean[][] pairs = new boolean[params.numberOfClasses][params.numberOfClasses];
        for (int i = 0; i < pairs.length; i++) {
            int p = rnd.nextInt(k + 1);
            for (int t = 0; t < p; t++) {
                int nx = rnd.nextInt(pairs.length);
                pairs[nx][i] = pairs[i][nx] = true;
            }
        }

       for (int i = 0; i < orders.size(); i++) {
            Policy p = new Policy(i, params.numberOfClasses);
            ArrayList<Class> curClasses = orders.get(i);
            for (Class c : curClasses) {
                p.addClass(c);
            }
            for (int i1 = 0; i1 < curClasses.size(); i1++) {
                for (int i2 = i1 + 1; i2 < curClasses.size(); i2++) {
                    if (pairs[curClasses.get(i1).id][curClasses.get(i2).id]) {
                        p.addRelation(curClasses.get(i1), curClasses.get(i2));
                    }
                }
            }

            p.finishBuilding();
            policies.add(p);
        }
    }
}
