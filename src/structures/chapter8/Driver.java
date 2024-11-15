package structures.chapter8;

public class Driver {
    public static void hashMapStringTest() {
        PHashMap<String, Integer> map = new PHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println(map);
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
        System.out.println(map.get("C"));
        map.put("A", 0);
        System.out.println(map);
        map.remove("A");
        System.out.println(map);
    }

    public static void hashMapTo25Test() {
        IMap<Integer, Integer> hashMap = new CHashMap<>();
        for (int i = 0; i < 25; i++) {
            hashMap.put(i, i);
        }
        for (int i = 0; i < 16; i++) {
            hashMap.remove(i);
        }
        System.out.println(hashMap);
    }

    public static void hashMap100MillionTest() {
        CHashMap<Integer, Integer> hashMap = new CHashMap<>();
        // reserve
        hashMap.resize(100_000_001);
        for (int i = 0; i < 100_000_000; i++) {
            hashMap.put(i, i);
        }
    }

    public static void main(String[] args) {
        hashMapTo25Test();
    }
}
