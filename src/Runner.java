import java.util.*;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) {
        HashSet<Integer> setOfKeys = new HashSet<>();
        Random random = new Random();
        int noOfKeys = 100;
        System.out.println("Total no of keys : "+ noOfKeys);
        for (int i = 0; i < noOfKeys; i++) {
            setOfKeys.add(Math.abs(random.nextInt()) % (int)Math.pow(2, 32));
        }
        PerfectHashMapQuadSpace mapQuadSpace = new PerfectHashMapQuadSpace(setOfKeys);
        List<MapEntry> quadHashTable = mapQuadSpace.getQuadHashTable();
        for (MapEntry mapEntry : quadHashTable) {
            if(mapEntry == null)
                System.out.println("null");
            else
                System.out.println(mapEntry.getKey() + "->" + mapEntry.getValue());
        }
        System.out.println("Quad space : " + mapQuadSpace.getTotalSpace());
        System.out.println("No of times to rebuild Hash Table = "+ mapQuadSpace.getNoTimesRebuild());

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
        System.out.println("No of times to rebuild Hash Tables = "+ mapLinearSpace.getNoTimesRebuild());

        System.out.println("---------------------------------------------");

        HashSet<Integer> setKeys = new HashSet<>();
        setOfKeys.add(1040177888);
        setOfKeys.add(24404444);
        setOfKeys.add(3041144);
        setOfKeys.add(4541054);
        setOfKeys.add(660511170);
        setOfKeys.add(48414445);
        setOfKeys.add(64484818);
        setOfKeys.add(1811515);
        noOfKeys = 1000;
        for (int i = 0; i < noOfKeys; i++) {
            setKeys.add(Math.abs(random.nextInt()) % (int)Math.pow(2, 32));
        }
        System.out.println("Total no of keys : "+ setKeys.size());
        PerfectHashMapLinearSpace perfectHashMapLinearSpace = new PerfectHashMapLinearSpace(setKeys);
        int keyToLookUp = 48414445;
        System.out.println("Default value of key "+ keyToLookUp);
        long start = System.currentTimeMillis();
        System.out.println(keyToLookUp +" -> "+ perfectHashMapLinearSpace.getValue(48414445));
        long end = System.currentTimeMillis();
        System.out.println("After setting value of key "+ keyToLookUp);
        perfectHashMapLinearSpace.setValue(keyToLookUp, 18484844);
        System.out.println(keyToLookUp + " -> " + perfectHashMapLinearSpace.getValue(keyToLookUp));
        System.out.print("Time to look up for key : ");
        System.out.println(end - start+ " ms");
    }
}
