import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimalDistanceTests {

    //TODO: in task description, w1 should be converted to w2, but in the minimalDistance, w2 is converted to w1

    @Test
    public void testFirstWordEmpty() {
        assertEquals(6, MinimalDistance.getMinimalDistanceCalculationResult("", "kitten").distance);
    }

    @Test
    public void testSecondWordEmpty() {
        assertEquals(6, MinimalDistance.getMinimalDistanceCalculationResult("kitten", "").distance);
    }

    @Test
    public void testBothWordsEmpty() {
        assertEquals(0, MinimalDistance.getMinimalDistanceCalculationResult("", "").distance);
    }

    @Test
    public void testBasicExample() {
        assertEquals(3, MinimalDistance.getMinimalDistanceCalculationResult("kitten", "sitting").distance);
    }

    @Test
    public void testShift1() {
        assertEquals(1, MinimalDistance.getMinimalDistanceCalculationResult("mirror", "irror").distance);
    }

    @Test
    public void testShift2() {
        assertEquals(2, MinimalDistance.getMinimalDistanceCalculationResult("mirror", "irrors").distance);
    }

    @Test
    public void testGetTransformationTrackExample() {
        MinimalDistance.Result result = MinimalDistance.getMinimalDistanceCalculationResult("kitten", "sitting");
        List<String> transformationTrack = MinimalDistance.getTransformationTrack("kitten", "sitting", result);

        List<String> expectedTrack = Arrays.asList("sitting", "sittin", "sitten", "kitten");
        assertEquals(expectedTrack, transformationTrack);
    }

}
