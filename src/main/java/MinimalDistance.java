public class MinimalDistance {
    public static void main(String[] args) {
        minimalDistance(args[0], args[1]);
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
    
    public static void minimalDistance(String word1, String word2) {
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
        
        int distance = editDistanceMatrix[firstWordLength][secondWordLength];
        System.out.println(distance);
        int curI = firstWordLength;
        int curJ = secondWordLength;
        String[] curWord = word2.split("");
        
        System.out.println(String.join("", curWord));
        while (distance > 0) {
            int deletion = editDistanceMatrix[curI][curJ - 1];
            int insertion = editDistanceMatrix[curI - 1][curJ];
            int substitution = editDistanceMatrix[curI - 1][curJ - 1];
            if (substitution < distance) {
                curWord[curJ - 1] = Character.toString(word1.charAt(curI - 1));
                curI--;
                curJ--;
                distance = substitution;
                System.out.println(String.join("", curWord));
            } else if (deletion < distance) {
                curWord[curJ - 1] = "";
                curJ--;
                distance = deletion;
                System.out.println(String.join("", curWord));
            } else if (insertion < distance) {
                curWord = insertIntoArray(curWord, curJ, Character.toString(word1.charAt(curI - 1)));
                curI--;
                distance = insertion;
                System.out.println(String.join("", curWord));
            } else {
                curI--;
                curJ--;
            }
        }
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
