import java.util.*;

public class PerfectHashMapQuadSpace {

    protected Set<Integer> setOfKeys;
    protected final Util util;
    private List<MapEntry> quadHashTable;
    protected int totalSpace;
    protected int[][] hashFn;


    public PerfectHashMapQuadSpace(Set<Integer> setOfKeys) {
        this.setOfKeys = setOfKeys;
        util = new Util();
        totalSpace = 0;
    }

    protected int hash(int key, int[][] hash_fn) {
        int[] key_binary = util.decompose_into_n_bits(key, 32);
        int[] hash_value = new int[hash_fn.length];
        for (int i = 0; i < hash_fn.length; i++) {
            for (int j = 0; j < hash_fn[0].length; j++) {
                hash_value[i] += hash_fn[i][j] * key_binary[j];
            }
            hash_value[i] %= 2;
        }
        return util.decimal_value(hash_value);
    }

    protected List<MapEntry> hash_using_quadratic_space_sol(Set<Integer> setOfKeys) {
        int table_size = (int) Math.pow(setOfKeys.size(), 2);
        MapEntry[] hash_table = new MapEntry[table_size];
        int b = (int) (Math.log(table_size) / Math.log(2));
        hashFn = util.get_random_hash_fn(b, 32);
        for (int key : setOfKeys) {
            int hash_value = hash(key, hashFn);
            if (hash_table[hash_value] != null && hash_table[hash_value].getKey() != 0)
                return hash_using_quadratic_space_sol(setOfKeys);
            hash_table[hash_value] = new MapEntry(key);
        }
        return Arrays.stream(hash_table).toList();
    }

    public int getValue(int key) {
       int index = hash(key, hashFn);
       return quadHashTable.get(index).getValue();
    }

    public void setValue(int key, int value) {
        int index = hash(key, hashFn);
        quadHashTable.get(index).setValue(value);
    }

    public void setKeys(Set<Integer> keys) {
        this.setOfKeys = keys;
    }

    public int getTotalSpace() {
        return totalSpace = (int) Math.pow(setOfKeys.size(), 2);
    }

    public List<MapEntry> getQuadHashTable() {
        return quadHashTable = hash_using_quadratic_space_sol(setOfKeys);
    }
}
