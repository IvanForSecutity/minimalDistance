import java.util.ArrayList;
import java.util.List;

public class MinimalDistance {

    protected static class Result {
        int distance;
        int[][] editDistanceMatrix;

        Result(int distance, int[][] editDistanceMatrix) {
            this.distance = distance;
            this.editDistanceMatrix = editDistanceMatrix;
        }
    }

    public static void main(String[] args) {
        Result result = getMinimalDistanceCalculationResult(args[0], args[1]);
        System.out.println("Minimal distance: " + result.distance);
        List<String> transformationTrack = getTransformationTrack(args[0], args[1], result);
        for (String step : transformationTrack) {
            System.out.println(step);
        }
    }

    public static String[] insertIntoArray(String[] arr, int index, String newItem) {
        String[] result = new String[arr.length + 1];
        for (int i = 0; i < index; i++) {
            result[i] = arr[i];
        }
        result[index] = newItem;
        for (int i = index + 1; i < result.length; i++) {
            result[i] = arr[i - 1];
        }
        return result;
    }

    public static Result getMinimalDistanceCalculationResult(String word1, String word2) {
        int firstWordLength = word1.length();
        int secondWordLength = word2.length();
        int[][] editDistanceMatrix = new int[firstWordLength + 1][secondWordLength + 1];

        fillFirstRowWithWord1Indexes(firstWordLength, editDistanceMatrix);
        fillFirstColumnWithWord2Indexes(secondWordLength, editDistanceMatrix);

        for (int i = 1; i <= firstWordLength; i++) {
            for (int j = 1; j <= secondWordLength; j++) {
                int deletion = editDistanceMatrix[i][j - 1] + 1;
                int insertion = editDistanceMatrix[i - 1][j] + 1;
                int substitution = editDistanceMatrix[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);

                editDistanceMatrix[i][j] = Math.min(Math.min(deletion, insertion), substitution);
            }
        }

        return new Result(editDistanceMatrix[firstWordLength][secondWordLength], editDistanceMatrix);
    }

    public static List<String> getTransformationTrack(String word1, String word2, Result result) {
        List<String> transformationSteps = new ArrayList<>();
        int distance = result.distance;
        int[][] editDistanceMatrix = result.editDistanceMatrix;
        int curI = word1.length();
        int curJ = word2.length();
        String[] curWord = word2.split("");

        transformationSteps.add(String.join("", curWord));
        while (distance > 0) {
            int deletion = editDistanceMatrix[curI][curJ - 1];
            int insertion = editDistanceMatrix[curI - 1][curJ];
            int substitution = editDistanceMatrix[curI - 1][curJ - 1];
            if (substitution < distance) {
                curWord[curJ - 1] = Character.toString(word1.charAt(curI - 1));
                curI--;
                curJ--;
                distance = substitution;
                transformationSteps.add(String.join("", curWord));
            } else if (deletion < distance) {
                curWord[curJ - 1] = "";
                curJ--;
                distance = deletion;
                transformationSteps.add(String.join("", curWord));
            } else if (insertion < distance) {
                curWord = insertIntoArray(curWord, curJ, Character.toString(word1.charAt(curI - 1)));
                curI--;
                distance = insertion;
                transformationSteps.add(String.join("", curWord));
            } else {
                curI--;
                curJ--;
            }
        }

        return transformationSteps;
    }

    private static void fillFirstColumnWithWord2Indexes(int secondWordLength, int[][] editDistanceMatrix) {
        for (int j = 0; j <= secondWordLength; j++) {
            editDistanceMatrix[0][j] = j;
        }
    }

    private static void fillFirstRowWithWord1Indexes(int firstWordLength, int[][] editDistanceMatrix) {
        for (int i = 0; i <= firstWordLength; i++) {
            editDistanceMatrix[i][0] = i;
        }
    }
}
