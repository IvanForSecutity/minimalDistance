import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimalDistanceTests {

    @Test
    public void testGetMinimalDistanceFirstWordEmpty() {
        assertEquals(6, MinimalDistance.getMinimalDistanceCalculationResult("", "kitten").distance());
    }

    @Test
    public void testGetMinimalDistanceSecondWordEmpty() {
        assertEquals(6, MinimalDistance.getMinimalDistanceCalculationResult("kitten", "").distance());
    }

    @Test
    public void testGetMinimalDistanceBothWordsEmpty() {
        assertEquals(0, MinimalDistance.getMinimalDistanceCalculationResult("", "").distance());
    }

    @Test
    public void testGetMinimalDistanceBasicExample() {
        assertEquals(3, MinimalDistance.getMinimalDistanceCalculationResult("kitten", "sitting").distance());
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
    public void testGetTransformationTrackOneWordEmpty() {
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
