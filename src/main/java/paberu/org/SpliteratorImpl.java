package paberu.org;

import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorImpl implements Spliterator<Character> {
    private final static int THRESHOLD = 10;
    private final String string;
    private int currentChar = 0;

    public SpliteratorImpl(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    //TODO: ensure that the String isn’t split at a random position but only at separator characters
    /**
     * This method should return null if currentSize is smaller than the THRESHOLD.
     * After check for the above is done, a for loop can be used to establish the splitPosition.
     * Inside the loop we have to check if the character at the splitPosition is a separator - using QuantitativeTextAnalysis.isSeparator.
     * If such is the case, we should create a new Spliterator instance, update the currentChar to the splitPosition,
     * and return the recently created instance of the Spliterator.
     * The method finishes with a simple return null statement.
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        return null;
    }

    @Override
    public long estimateSize() {
         return string.length() - currentChar;
    }

    //TODO: decide which characteristics hold true for this spliterator
    /**
     * Decide which characteristics pertain to the spliterator you are implementing.
     * They are all listed below, delete the ones that do not belong here in your opinion.
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + SORTED + DISTINCT + NONNULL + IMMUTABLE + CONCURRENT;
    }
}
