package week5;

import structures.chapter5.ArrayCollection;
import structures.chapter5.SortedArrayCollection;

public class WordFrequencyAnalyzer {
    private ArrayCollection<WordFrequency> frequencies;

    public WordFrequencyAnalyzer() {
        this.frequencies = new ArrayCollection<>();
    }

    public int size() {
        return this.frequencies.size();
    }

    public void add(String word) {
        boolean found = false;
        for (WordFrequency wordFrequency : frequencies) {
            if (wordFrequency.getWord().equals(word)) {
                found = true;
                wordFrequency.increaseFrequency();
            }
        }
        if (!found) {
            frequencies.add(new WordFrequency(word));
        }
    }

    public SortedArrayCollection<WordFrequency> toSortedCollection() {
        SortedArrayCollection<WordFrequency> sortedCollection = new SortedArrayCollection<>();
        for (WordFrequency wordFrequency : frequencies) {
            sortedCollection.add(wordFrequency);
        }
        return sortedCollection;
    }

    public ArrayCollection<String> uniqueWords() {
        ArrayCollection<String> uniqueWords = new ArrayCollection<>();
        for (WordFrequency wordFrequency : frequencies) {
            uniqueWords.add(wordFrequency.getWord());
        }
        return uniqueWords;
    }

    @Override
    public String toString() {
        return "WordFrequencyAnalyzer {frequencies=" + frequencies + "}";
    }
}
