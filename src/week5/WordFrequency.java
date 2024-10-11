package week5;

import structures.chapter5.IComparable;

public class WordFrequency implements IComparable<WordFrequency> {
    private String word;
    private int frequency;

    public WordFrequency(String word) {
        this.word = word;
        this.frequency = 1;
    }

    public String getWord() {
        return this.word;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    @Override
    public String toString() {
        return "WordFrequency{word=" + word + ", frequency=" + frequency + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof WordFrequency wf) {
            return this.word.equals(wf.getWord()) && this.frequency == wf.getFrequency();
        }
        return false;
    }

    @Override
    public int compareTo(WordFrequency o) {
        int cmp = this.frequency - o.frequency;
        if (cmp == 0) {
            return this.word.compareTo(o.word);
        }
        return cmp;
    }
}
