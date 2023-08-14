import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimalDistance {

    protected record Result(int distance, int[][] editDistanceMatrix) { }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Please provide exactly two strings as arguments.");
            return;
        }

        Result result = getMinimalDistanceCalculationResult(args[0], args[1]);
        System.out.println("Minimal distance: " + result.distance);
        List<String> transformationTrack = getTransformationTrack(args[0], args[1], result);
        System.out.print("Transformation track: " + result.distance);
        for (String step : transformationTrack) {
            System.out.println(step);
        }
    }

    public static Result getMinimalDistanceCalculationResult(String word1, String word2) {
        int firstWordLength = word1.length();
        int secondWordLength = word2.length();
        int[][] editDistanceMatrix = new int[firstWordLength + 1][secondWordLength + 1];
        initializeDistanceMatrix(firstWordLength, secondWordLength, editDistanceMatrix);

        for (int i = 1; i <= firstWordLength; i++) {
            for (int j = 1; j <= secondWordLength; j++) {
                int insertion = editDistanceMatrix[i][j - 1] + 1;
                int deletion = editDistanceMatrix[i - 1][j] + 1;
                int substitution = editDistanceMatrix[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);

                editDistanceMatrix[i][j] = Math.min(Math.min(deletion, insertion), substitution);
            }
        }
        return new Result(editDistanceMatrix[firstWordLength][secondWordLength], editDistanceMatrix);
    }

    private static void initializeDistanceMatrix(int firstWordLength, int secondWordLength, int[][] editDistanceMatrix) {
        for (int i = 0; i <= firstWordLength; i++) {
            editDistanceMatrix[i][0] = i;
        }
        for (int j = 0; j <= secondWordLength; j++) {
            editDistanceMatrix[0][j] = j;
        }
    }

    public static List<String> getTransformationTrack(String word1, String word2, Result result) {
        List<String> transformationSteps = new ArrayList<>();
        int distance = result.distance;
        int[][] editDistanceMatrix = result.editDistanceMatrix;
        int currentWord1Index = word1.length();
        int currentWord2Index = word2.length();
        StringBuilder currentWord = new StringBuilder(word2);

        transformationSteps.add(currentWord.toString());
        while (distance > 0) {
            int deletion = currentWord2Index >= 1
                    ? editDistanceMatrix[currentWord1Index][currentWord2Index - 1]
                    : Integer.MAX_VALUE;
            int insertion = currentWord1Index >= 1
                    ? editDistanceMatrix[currentWord1Index - 1][currentWord2Index]
                    : Integer.MAX_VALUE;
            int substitution = currentWord1Index >= 1 && currentWord2Index >= 1
                    ? editDistanceMatrix[currentWord1Index - 1][currentWord2Index - 1]
                    : Integer.MAX_VALUE;

            if (substitution < distance) {
                currentWord.setCharAt(currentWord2Index - 1, word1.charAt(currentWord1Index - 1));
                currentWord1Index--;
                currentWord2Index--;
                distance = substitution;
                transformationSteps.add(currentWord.toString());
            } else if (deletion < distance) {
                currentWord.deleteCharAt(currentWord2Index - 1);
                currentWord2Index--;
                distance = deletion;
                transformationSteps.add(currentWord.toString());
            } else if (insertion < distance) {
                currentWord.insert(currentWord2Index, word1.charAt(currentWord1Index - 1));
                currentWord1Index--;
                distance = insertion;
                transformationSteps.add(currentWord.toString());
            } else {
                currentWord1Index--;
                currentWord2Index--;
            }
        }

        Collections.reverse(transformationSteps);
        return transformationSteps;
    }
}
