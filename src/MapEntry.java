public class MapEntry {
    private int key;
    private int value;

    public MapEntry(int key) {
        this.key = key;
        this.value = 0;
    }

    public MapEntry(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
