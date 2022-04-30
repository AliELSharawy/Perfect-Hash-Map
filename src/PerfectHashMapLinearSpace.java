import java.util.*;

public class PerfectHashMapLinearSpace extends PerfectHashMapQuadSpace{

    private final List<Set<Integer>> level1HashTable;
    private final List<List<MapEntry>> linearHashTable;
    private final int[][][] listHashFn;

    public PerfectHashMapLinearSpace(Set<Integer> setOfKeys) {
        super(setOfKeys);
        level1HashTable = new ArrayList<>(Collections.nCopies(setOfKeys.size(), new HashSet<>()));
        linearHashTable = new ArrayList<>(Collections.nCopies(setOfKeys.size(), new ArrayList<>()));
        listHashFn = new int[setOfKeys.size() + 1][][];
        hash_using_linear_space_sol();
    }

    public void hash_using_linear_space_sol() {
        level1_hashing();
        level2_hashing();
    }

    private void level1_hashing() {
        int b = (int) (Math.log(setOfKeys.size()) / Math.log(2));
        int[][] level1_hash_fn = util.get_random_hash_fn(b, 32);
        listHashFn[0] = level1_hash_fn;
        for (int key : setOfKeys) {
            int hash_value = hash(key, level1_hash_fn);
            if (level1HashTable.get(hash_value).isEmpty())
                level1HashTable.set(hash_value, new HashSet<>());
            level1HashTable.get(hash_value).add(key);
        }
    }

    private void level2_hashing() {
        int slotSize = 0;
        for (int i = 0; i < setOfKeys.size(); i++) {
            slotSize = level1HashTable.get(i).size();
            totalSpace += Math.pow(slotSize, 2);
            if (slotSize == 0) {
                listHashFn[i + 1] = new int[0][32];
                continue;
            }
            if (linearHashTable.get(i).isEmpty())
                linearHashTable.set(i, new ArrayList<>());
            linearHashTable.set(i, hash_using_quadratic_space_sol(level1HashTable.get(i)));
            listHashFn[i + 1] = hashFn;
        }
    }

    @Override
    public int getValue(int key) {
        int slotIndex = hash(key, listHashFn[0]);
        int level2Index = hash(key, listHashFn[slotIndex + 1]);
        return linearHashTable.get(slotIndex).get(level2Index).getValue();
    }

    @Override
    public void setValue(int key, int value) {
        int slotIndex = hash(key, listHashFn[0]);
        int level2Index = hash(key, listHashFn[slotIndex + 1]);
        linearHashTable.get(slotIndex).get(level2Index).setValue(value);
    }

    @Override
    public int getTotalSpace() {
        return totalSpace;
    }

    public List<List<MapEntry>> getLinearHashTable() {
        return linearHashTable;
    }
}
