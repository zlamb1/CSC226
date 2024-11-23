package week9;

import structures.chapter8.CHashMap;
import structures.chapter8.IMap;
import structures.chapter8.LoadFactor;
import structures.chapter8.PHashMap;
import week8.Movie;

import java.util.Random;

public class Main {
    public static void testPrimeBucketEfficiency() {
        for (int i = 0; i < 3; i++) {
            IMap<Integer, Integer> map = new PHashMap<>();
            map.setLoadFactor(new LoadFactor(0.0, 1.0, false, false));
            int len = 100 + i;
            map.resize(len);

            Random random = new Random();

            for (int j = 0; j < 100; j++) {
                int key = (int) (random.nextDouble() * 100 + 100), value = (int) (random.nextDouble() * 100 + 100);
                map.put(key, value);
            }

            System.out.println(len + ": " + map.getAverageBucketSize());
        }
    }

    public static void testLoadFactor() {
        IMap<Integer, Integer> map = new CHashMap<>();
        map.setLoadFactor(new LoadFactor(0.0, 1.0, false, false));
        map.resize(101);

        for (int i = 0; i < 100; i++) {
            int key = (int) (Math.random() * 100 + 100), value = (int) (Math.random() * 100 + 100);
            map.put(key, value);
            if (i == 69) {
                System.out.println("getEmptyBuckets at 70: " + map.getEmptyBuckets());
            }
        }

        System.out.println("getEmptyBuckets at 100: " + map.getEmptyBuckets());
    }

    public static void testMovieMap() {
        IMap<Movie, Movie> map = new CHashMap<>();
        map.setLoadFactor(new LoadFactor(0.0, 1.0, false, false));
        map.resize(11);

        for (int i = 0; i < 11; i++) {
            Movie movie = new Movie("Cars", "John Lasseter", "Animation", 2006 + i);
            map.put(movie, movie);
        }

        System.out.println(map);

        IMap<MovieWithHash, MovieWithHash> map2 = new CHashMap<>();
        map.setLoadFactor(new LoadFactor(0.0, 1.0, false, false));
        map.resize(11);

        for (int i = 0; i < 11; i++) {
            MovieWithHash movieWithHash = new MovieWithHash("Cars", "John Lasseter", "Animation", 2006 + i);
            map2.put(movieWithHash, movieWithHash);
        }

        System.out.println(map2);
    }

    public static void main(String[] args){
        testPrimeBucketEfficiency();
        testLoadFactor();
        testMovieMap();
    }
}
