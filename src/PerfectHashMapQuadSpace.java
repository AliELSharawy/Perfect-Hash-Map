import java.util.*;
import java.util.stream.Stream;

public class PerfectHashMapQuadSpace {

    protected Set<Integer> setOfKeys;
    protected final Util util;
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

    protected List<Integer> hash_using_quadratic_space_sol(Set<Integer> setOfKeys) {
        int table_size = (int) Math.pow(setOfKeys.size(), 2);
        int[] hash_table = new int[table_size];
        int b = (int) (Math.log(table_size) / Math.log(2));
        hashFn = util.get_random_hash_fn(b, 32);
        for (int key : setOfKeys) {
            int hash_value = hash(key, hashFn);
            if (hash_table[hash_value] != 0)
                return hash_using_quadratic_space_sol(setOfKeys);
            hash_table[hash_value] = key;
        }
        return Arrays.stream(hash_table).boxed().toList();
    }



    public void setKeys(Set<Integer> keys) {
        this.setOfKeys = keys;
    }

    public int getTotalSpace() {
        return totalSpace = (int) Math.pow(setOfKeys.size(), 2);
    }

    public List<Integer> getQuadHashTable() {
        return hash_using_quadratic_space_sol(setOfKeys);
    }
}
