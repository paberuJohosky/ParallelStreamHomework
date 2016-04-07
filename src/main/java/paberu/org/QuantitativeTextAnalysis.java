package paberu.org;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class QuantitativeTextAnalysis
{
    private static List SEPARATORS = Arrays.asList('の','は','が','を','と','に','も','で','。');

    static boolean isSeparator(char character) {
        return SEPARATORS.contains(character) ? true : false;
    }

    static int iterativeWordCount(String string) {
        int counter = 0;
        boolean lastSeparator = true;

        for(char character : string.toCharArray()) {

            if(isSeparator(character)) {
                lastSeparator = true;
            } else {
                if (lastSeparator) counter++;
                lastSeparator = false;
            }
        }
        return counter;
    }

    static int streamingWordCount(Stream<Character> stream) {
        JapaneseWordCounter japaneseWordCounter =
                stream
                        .reduce(
                                new JapaneseWordCounter(0, true),
                                JapaneseWordCounter::accumulate,
                                JapaneseWordCounter::combine);

        return japaneseWordCounter.getCounter();
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
