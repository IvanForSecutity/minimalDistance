import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimalDistanceTests {

    @Test
    public void testFirstWordEmpty() {
        assertEquals(6, MinimalDistance.getMinimalDistanceCalculationResult("", "kitten").distance());
    }

    @Test
    public void testSecondWordEmpty() {
        assertEquals(6, MinimalDistance.getMinimalDistanceCalculationResult("kitten", "").distance());
    }

    @Test
    public void testBothWordsEmpty() {
        assertEquals(0, MinimalDistance.getMinimalDistanceCalculationResult("", "").distance());
    }

    @Test
    public void testBasicExample() {
        assertEquals(3, MinimalDistance.getMinimalDistanceCalculationResult("kitten", "sitting").distance());
    }

    @Test
    public void testShift1() {
        assertEquals(1, MinimalDistance.getMinimalDistanceCalculationResult("mirror", "irror").distance());
    }

    @Test
    public void testShift2() {
        assertEquals(2, MinimalDistance.getMinimalDistanceCalculationResult("mirror", "irrors").distance());
    }

    @Test
    public void testGetTransformationTrackExample() {
        String word1 = "kitten";
        String word2 = "sitting";
        MinimalDistance.Result result = MinimalDistance.getMinimalDistanceCalculationResult(word1, word2);
        List<String> transformationTrack = MinimalDistance.getTransformationTrack(word1, word2, result);

        List<String> expectedTrack = Arrays.asList(word1, "sitten", "sittin", word2);
        assertEquals(expectedTrack, transformationTrack);
    }

    @Test
    public void testGetTransformationTrackExample2() {
        String word1 = "mirror";
        String word2 = "irror";
        MinimalDistance.Result result = MinimalDistance.getMinimalDistanceCalculationResult(word1, word2);
        List<String> transformationTrack = MinimalDistance.getTransformationTrack(word1, word2, result);

        List<String> expectedTrack = Arrays.asList(word1, word2);
        assertEquals(expectedTrack, transformationTrack);
    }

    @Test
    public void testGetTransformationTrackExample3() {
        String word1 = "";
        String word2 = "irror";
        MinimalDistance.Result result = MinimalDistance.getMinimalDistanceCalculationResult(word1, word2);
        List<String> transformationTrack = MinimalDistance.getTransformationTrack(word1, word2, result);

        List<String> expectedTrack = Arrays.asList(word1, "i", "ir", "irr", "irro", word2);
        assertEquals(expectedTrack, transformationTrack);
    }

    @Test
    public void testGetTransformationTrackBothEmpty() {
        String word1 = "";
        String word2 = "";
        MinimalDistance.Result result = MinimalDistance.getMinimalDistanceCalculationResult(word1, word2);
        List<String> transformationTrack = MinimalDistance.getTransformationTrack(word1, word2, result);

        List<String> expectedTrack = List.of(word1);
        assertEquals(expectedTrack, transformationTrack);
    }

    @Test
    public void testGetTransformationTrackSame() {
        String word1 = "hello";
        String word2 = "hello";
        MinimalDistance.Result result = MinimalDistance.getMinimalDistanceCalculationResult(word1, word2);
        List<String> transformationTrack = MinimalDistance.getTransformationTrack(word1, word2, result);

        List<String> expectedTrack = List.of(word1);
        assertEquals(expectedTrack, transformationTrack);
    }

}
