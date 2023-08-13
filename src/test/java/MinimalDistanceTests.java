import org.junit.jupiter.api.Test;

public class MinimalDistanceTests {

    //TODO: in task description, w1 should be converted to w2, but in the minimalDistance, w2 is converted to w1

    @Test
    public void testFirstWordEmpty() {
        MinimalDistance.minimalDistance("", "kitten");
    }

    @Test
    public void testSecondWordEmpty() {
        MinimalDistance.minimalDistance("kitten", "");
    }
    @Test
    public void testBothWordsEmpty() {
        MinimalDistance.minimalDistance("", "");
    }

    @Test
    public void testBasicExample() {
        MinimalDistance.minimalDistance("kitten", "sitting");
    }

    @Test
    public void testShift1() {
        MinimalDistance.minimalDistance("mirror", "irror");
    }
    @Test
    public void testShift2() {
        MinimalDistance.minimalDistance("mirror", "irrors");
    }

}
