import java.util.*;

public class Runner {
    public static void main(String[] args) {
        HashSet<Integer> setOfKeys = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            setOfKeys.add(Math.abs(random.nextInt()) % (int)Math.pow(2, 32));
        }
        PerfectHashMapQuadSpace map = new PerfectHashMapQuadSpace(setOfKeys);
        List<Integer> quadHashTable = map.getQuadHashTable();
        System.out.println(quadHashTable);
        System.out.println(map.getTotalSpace());

        System.out.println("------------------------------------------------------------------");


        PerfectHashMapLinearSpace mapLinearSpace = new PerfectHashMapLinearSpace(setOfKeys);
        List<List<Integer>> linearHashTable = mapLinearSpace.getLinearHashTable();
        System.out.println(linearHashTable);
        System.out.println(mapLinearSpace.getTotalSpace());

    }
}
