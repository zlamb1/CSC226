package week8;

import structures.chapter5.ICollection;
import structures.chapter5.SortedArrayCollection;
import structures.chapter7.BinarySearchTree;

public class MovieCollection extends BinarySearchTree<Movie> {
    public MovieCollection() {
        super(Movie::compareTo);
    }

    public String moviesIn(String genre) {
        ICollection<Movie> movies = search(x -> x.getGenre().equals(genre));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (Movie movie : movies) {
            stringBuilder.append("\t").append(movie).append(",\n");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String moviesBetween(int startYear, int endYear) {
        ICollection<Movie> movies = search(x -> x.getYear() >= startYear && x.getYear() <= endYear);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (Movie movie : movies) {
            stringBuilder.append("\t").append(movie).append(",\n");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String moviesDirectedBy(String director) {
        ICollection<Movie> movies = search(x -> x.getDirector().equals(director));

        SortedArrayCollection<Movie> sortedMovies = new SortedArrayCollection<>((a, b) -> b.getYear() - a.getYear());
        for (Movie movie : movies) {
            sortedMovies.add(movie);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (Movie movie : sortedMovies) {
            stringBuilder.append("\t").append(movie).append(",\n");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
