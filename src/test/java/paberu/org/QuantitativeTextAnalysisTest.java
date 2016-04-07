package paberu.org;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class QuantitativeTextAnalysisTest
    extends TestCase
{

    private static String PASSAGE = "人の一生は重荷を負うて遠き道をゆくが如し。\n" +
            "急ぐべからず。\n" +
            "不自由を常と思えば不足なし。\n" +
            "心に望みおこらば困窮したる時を思い出すべし。\n" +
            "堪忍は無事長久の基　怒りは敵と思え。\n" +
            "勝つ事ばかり知りて負くる事を知らざれば害其の身に至る。\n" +
            "おのれを責めて人を責むるな。\n" +
            "及ばざるは過ぎたるよりまされり。\n" +
            "人はただ身のほどをしれ草の葉の露も重きは落つるものかな。" +
            "わが命旦夕に迫るといへども。\n" +
            "将軍斯くおはしませば天下のこと心安し。\n" +
            "されども将軍の政道その理にかなはず。\n" +
            "億兆の民艱難することもあらんには。\n" +
            "たれにても其の任に代らるべし。\n" +
            "天下は一人の天下に非ず天下は天下の天下なり。\n" +
            "たとへ他人の天下の政務をとりたりとも。\n" +
            "四海安穏にして万人その仁恵を蒙らばもとより。\n" +
            "家康が本意にしていささかもうらみに思うことなし。";

    @Test
    //81 is our reference
    public void testIterativeApproach() {
        assertEquals(81, QuantitativeTextAnalysis.iterativeWordCount(PASSAGE) );
    }

    @Test
    public void testSequentialStream() {
        Stream<Character> sequentialStream = IntStream.range(0, PASSAGE.length()).mapToObj(PASSAGE::charAt);
        assertEquals(81, QuantitativeTextAnalysis.streamingWordCount(sequentialStream) );
    }

    @Test
    //this test will keep failing because it's done in parallel and uses the default spliterator
    public void testStreamWithDefaultSpliterator() {
        Stream<Character> streamForDefaultSpliterator = IntStream.range(0, PASSAGE.length()).mapToObj(PASSAGE::charAt);
        assertEquals(81, QuantitativeTextAnalysis.streamingWordCount(streamForDefaultSpliterator.parallel()) );
    }

    @Test
    //this is your task - make the test pass by implementing the custom spliterator
    public void testStreamWithCustomSpliterator() {
        Spliterator<Character> spliterator = new SpliteratorImpl(PASSAGE);
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        assertEquals(81, QuantitativeTextAnalysis.streamingWordCount(parallelStream) );
    }


    @Test
    //this test will check if you selected the right characteristics
    public void testCharacteristics() {
        Spliterator<Character> spliterator = new SpliteratorImpl(PASSAGE);
        assertEquals(17744, spliterator.characteristics());
    }
}
