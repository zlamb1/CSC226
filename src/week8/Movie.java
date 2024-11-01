package week8;

import structures.chapter5.IComparable;

public class Movie implements IComparable<Movie> {
    private String title, director, genre;
    private int year;

    public Movie(String title, String director, String genre, int year) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{title=" + title + ", director=" + director + ", genre=" + genre + ", year=" + year + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;

        if (other instanceof Movie movie) {
            return title.equals(movie.getTitle()) &&
                    director.equals(movie.getDirector()) &&
                    genre.equals(movie.getGenre()) &&
                    year == movie.getYear();
        }

        return false;
    }

    @Override
    public int compareTo(Movie other) {
        int yearDiff = year - other.getYear();
        if (yearDiff == 0) {
            int titleDiff = title.compareTo(other.getTitle());
            if (titleDiff == 0) {
                return director.compareTo(other.getDirector());
            }

            return titleDiff;
        }

        return yearDiff;
    }
}
