import org.junit.jupiter.api.Test;

public class MinimalDistanceTests {

    @Test
    public void testSomePair() {
        MinimalDistance.minimalDistance("sitting", "kitten");
        //in task description, w1 should be converted to w2, but in the minimalDistance, w2 is converted to w1
    }
}
