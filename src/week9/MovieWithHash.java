package week9;

import week8.Movie;

public class MovieWithHash extends Movie {
    public MovieWithHash(String title, String director, String genre, int year) {
        super(title, director, genre, year);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = result + 31 + title.hashCode();
        result = result * 31 + director.hashCode();
        result = result * 31 + genre.hashCode();
        result = result * 31 + year;
        return result;
    }
}
