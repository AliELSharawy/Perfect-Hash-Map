import java.util.*;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) {
        HashSet<Integer> setOfKeys = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            setOfKeys.add(Math.abs(random.nextInt()) % (int)Math.pow(2, 32));
            setOfKeys.add((i + 1) % (int) Math.pow(2, 32));
        }
        PerfectHashMapQuadSpace map = new PerfectHashMapQuadSpace(setOfKeys);
        List<MapEntry> quadHashTable = map.getQuadHashTable();
        for (MapEntry mapEntry : quadHashTable) {
            if(mapEntry == null)
                System.out.println("null");
            else
                System.out.println(mapEntry.getKey() + "->" + mapEntry.getValue());
        }
        System.out.println("Quad space :" + map.getTotalSpace());

        System.out.println("------------------------------------------------------------------");


        PerfectHashMapLinearSpace mapLinearSpace = new PerfectHashMapLinearSpace(setOfKeys);
        List<List<MapEntry>> linearHashTable = mapLinearSpace.getLinearHashTable();
        for (List<MapEntry> mapEntryList : linearHashTable) {
            for (MapEntry mapEntry : mapEntryList) {
                if(mapEntry == null)
                    System.out.println("null");
                else
                    System.out.println(mapEntry.getKey() + "->" + mapEntry.getValue());
            }
        }
        System.out.println("Linear space : "+ mapLinearSpace.getTotalSpace());

    }
}
