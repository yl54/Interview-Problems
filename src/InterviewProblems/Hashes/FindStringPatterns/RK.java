package InterviewProblems.Hashes.FindStringPatterns;

import java.util.ArrayList;

// This problem deals with finding patterns in
//   text using the Rabin-Karp algorithm.
// The point is that its not likely to have many hash
//   values be the same of what should be strings with
//   different characters.
// RK = Rabin-Karp
public class RK {
    /*
     * Test Cases
     * - null/0 length txt and patterns
     * - pattern = txt
     * - pattern != txt
     * - pattern is in txt
     * - same hashes, different strings
     *
     **/

    // For now, do not assume the length of strings in patterns are all the same.
    // Q: What happens if they have different lengths?
    public static void findPatternExists (String txt, String[] patterns, int size) {
        // Check if any of the inputs are invalid.
        if (txt == null || txt.length() < size ||
                patterns == null || patterns.length == 0) {
            System.out.println("Error: txt is null or size > txt.length().");
            return;
        }

        ArrayList<String> valid_patterns = new ArrayList<String>();
        for (int i = 0; i < patterns.length; i++) {
            if (patterns[i].length() == size) {
                valid_patterns.add(patterns[i]);
            }
        }

        // Pick a few initial numbers.
        int mult = 256; // # of caracters in alphabet
        int mod = 101;  // some prime number

        // Calculate each pattern's hash value.
        int[] pattern_hashes =
                RK.getPatternHashes(valid_patterns, patterns, size, mult, mod);

        // Calculate the hashes for txt.
        int[] txt_hashes = RK.getTxtHashes(txt, size, mult, mod);

        // Compare the hashes of the patterns and the txt.
        RK.compareHashes(size, pattern_hashes, txt_hashes, valid_patterns, txt);
    }

    // This function gets the hashes for the patterns.
    public static int[] getPatternHashes
            (ArrayList<String> valid_patterns, String[] patterns, int size,
     int mult, int mod) {
        // Initialize the array to store the hashes.
        int [] pattern_hashes = new int[patterns.length];

        // For each pattern:
        for (int i = 0; i < patterns.length; i++) {
            // Calculate the hash value.
            pattern_hashes[i] = RK.getInitHash(patterns[i], size, mult, mod);
        }

        // Return the array of hashes.
        return pattern_hashes;
    }

    // This function gathers the hashes for the large string.
    public static int[] getTxtHashes (String txt, int size, int mult, int mod) {
        // Get the multiplied mod number.
        int cumulative_mult = 1;
        for (int i = 0; i < size; i++) {
            cumulative_mult = (cumulative_mult * mult) % mod;
        }

        // Initialize the hash values array.
        int possible_spots = txt.length() - size + 1;
        int[] txt_hashes = new int[possible_spots];

        // Get the initial hash of the string limited to the size
        //   of the pattern elements.
        txt_hashes[0] = RK.getInitHash(txt, size, mult, mod);

        // Get the subsequent hash values based off of the initial.
        int first_index = 0;
        for (int i = 1; i < possible_spots; i++) {
            int f_ch = txt.charAt(first_index + i - 1);
            int e_ch = txt.charAt(size - 1 + i);
            txt_hashes[i] =
                    ((txt_hashes[i - 1] - (f_ch * cumulative_mult)) + e_ch) % mod;
        }

        // Return the array of hashes.
        return txt_hashes;
    }

    // This function calculates the initial hash value given some inputs.
    public static int getInitHash (String s, int size, int mult, int mod) {
        // Check if s is an invalid input.
        if (s == null || s.length() == 0 || size < 1) {
            return -1;
        }

        int hash = 0;
        for (int i = 0; i < size; i++) {
            hash = (mult * hash + s.charAt(i)) % mod;
        }

        return hash;
    }

    // This function compares the pattern hashes with the txt hashes.
    public static void compareHashes
             (int size, int[] pattern_hashes, int[] txt_hashes,
              ArrayList<String> valid_patterns, String txt) {
        // Check if any input values are invalid.
        if (pattern_hashes == null || txt_hashes == null) {
            return;
        }

        // For each pattern:
        for (int i = 0; i < pattern_hashes.length; i++) {
            // Check each txt hash.
            for (int j = 0; j < txt_hashes.length; j++) {
                // If they are equal, then check each individual character.
                if (pattern_hashes[i] == txt_hashes[j]) {
                    System.out.println("Hashes match!");
                    System.out.println("Hash value: " + pattern_hashes[i]);
                    System.out.println("pattern: " + pattern_hashes[i]);
                    System.out.println("txt: " + pattern_hashes[i]);

                    // Get the two substrings to compare.
                    String current_pattern = valid_patterns.get(i);
                    String txt_substr = txt.substring(j, j + size);
                    if (current_pattern.equals(txt_substr)) {
                        System.out.println("Strings match as well!");
                    }

                    System.out.println();
                }
            }
        }
    }

}
