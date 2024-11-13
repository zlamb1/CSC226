package structures.chapter8;

public class Driver {
    public static void hashMapStringTest() {
        HashMap<String, Integer> map = new HashMap<>();
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

    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 25; i++) {
            hashMap.put(i, i);
        }
        for (int i = 0; i < 20; i++) {
            hashMap.remove(i);
        }
        System.out.println(hashMap);
    }
}
