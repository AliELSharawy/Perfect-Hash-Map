import java.util.Random;

public class Util {
    public int[] decompose_into_n_bits(int key, int no_bits) {
        int[] binary_representation = new int[no_bits];
        int i = 0;
        while (key != 0) {
            binary_representation[i++] = key % 2;
            key /= 2;
        }
        return binary_representation;
    }

    public int decimal_value(int[] binary_no) {
        int index = 0;
        int value = 0;
        for (int i = binary_no.length - 1; i >= 0 ; i--) {
            value += binary_no[i] * Math.pow(2, index);
            index++;
        }
        return value;
    }

    public int[][] get_random_hash_fn(int b, int u) {
        int[][] random_hash_fn = new int[b][u];
        Random random = new Random();
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < u; j++) {
                random_hash_fn[i][j] = (random.nextInt() % 2 + 2) % 2;
            }
        }
        return random_hash_fn;
    }
}
