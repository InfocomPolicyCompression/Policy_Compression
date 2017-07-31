import java.util.ArrayList;

public class Reducer {
    public static boolean canMatch(boolean[] used, ArrayList<Class> classes, ArrayList<Policy> policies) {
        for (Policy p : policies) {
            int c = 0;
            boolean[] isTaken = new boolean[p.isBefore.length];
            for (int i = classes.size() - 1; i >= 0; --i) {
                if (used[i]) {
                    continue;
                }
                for (Class c1 : p.classes) {
                    if (c1 != classes.get(i) || isTaken[c1.id]) {
                        continue;
                    }

                    boolean take = true;

                    for (Class c2 : p.classes) {
                        if (c1 != c2 && p.isBefore(c1, c2)) {
                            take &= isTaken[c2.id];
                        }
                    }

                    if (take) {
                        isTaken[c1.id] = true;
                        c++;
                    }
                }
            }
            if (c != p.classes.size()) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Class> reduce(ArrayList<Class> ans, Data data) {
        boolean[] taken = new boolean[ans.size()];
        if (!canMatch(taken, ans, data.policies)) {
            throw new AssertionError();
        }

        ArrayList<Integer> order = new ArrayList<>();

        for (int i = 0; i < ans.size(); i++)
        {
            order.add(i);
        }

        RandomSort.sort(order, (o1, o2)->ans.get(o2).size - ans.get(o1).size);
        for (int j = 0; j < ans.size(); j++) {
            int i = order.get(j);
            taken[i] = true;
            if (!canMatch(taken, ans, data.policies)) {
                taken[i] = false;
            }
        }

        if (!canMatch(taken, ans, data.policies)) {
            throw new AssertionError();
        }

        ArrayList<Class> reduced = new ArrayList<>();
        for (int i = 0; i < ans.size(); i++) {
            if (!taken[i]) {
                reduced.add(ans.get(i));
            }
        }
        return reduced;
    }

    public long[] processReduce(ArrayList<Class> result, Data data)
    {

        long oldScore = Scorer.getScore(result);
        System.out.println("Before LD: "+Scorer.getScore(result));
        result = Reducer.reduce(result, data);
        long newScore = Scorer.getScore(result);
        System.out.println("After LD: " + Scorer.getScore(result));
        return new long[]{oldScore, newScore};
    }

}
