package paberu.org;

public class JapaneseWordCounter {

    private final int counter;
    private final boolean lastSeparator;

    public JapaneseWordCounter(int counter, boolean lastSeparator) {
        this.counter = counter;
        this.lastSeparator = lastSeparator;
    }

    public JapaneseWordCounter accumulate(Character c) {
        if(QuantitativeTextAnalysis.isSeparator(c)) {
            return lastSeparator ? this : new JapaneseWordCounter(counter, true);
        } else {
            return lastSeparator ? new JapaneseWordCounter(counter+1, false) : this;
        }
    }

    public JapaneseWordCounter combine(JapaneseWordCounter wordCounter) {
        return new JapaneseWordCounter(counter + wordCounter.counter, wordCounter.lastSeparator);
    }


    public int getCounter() {
        return counter;
    }

}
