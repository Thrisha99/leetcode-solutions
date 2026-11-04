
import java.util.*;

// LeetCode 49: Group Anagrams
// Link: https://leetcode.com/problems/group-anagrams/
// Difficulty: Medium
// Topic: Strings, Hashing
// Pattern: Frequency Counting + HashMap
//
// OPTIMAL APPROACH:
// Use a frequency array to generate a unique key for each
// character-frequency pattern. Store words with the same key
// together in a List.
//
// Time Complexity: O(N * K)
// Auxiliary Space: O(K) for the current frequency array.
// Output/Stored Space: O(N * K) for keys and grouped words.
// N = number of strings, K = maximum string length.
// The alphabet size (26) is constant.

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        // STEP 1: Create the HashMap.
        //
        // Key   -> String (frequency signature)
        // Value -> List<String> (words with that signature)
        //
        // Example:
        // "1#0#0#..." -> ["eat", "tea", "ate"]

        HashMap<String, List<String>> map = new HashMap<>();

        // STEP 2: Traverse every word in the input array.
        // i represents the current word's index.

        for (int i = 0; i < strs.length; i++) {

            // STEP 3: Store the current word.
            //
            // i = 0 -> word = "eat"
            // i = 1 -> word = "tea"
            // i = 2 -> word = "tan"

            String word = strs[i];

            // STEP 4: Create a frequency array.
            //
            // freq[0] -> frequency of 'a'
            // freq[1] -> frequency of 'b'
            // ...
            // freq[25] -> frequency of 'z'
            //
            // Every new word gets a fresh frequency array.

            int[] freq = new int[26];

            // STEP 5: Count the characters in the current word.
            // j represents the current character's index.

            for (int j = 0; j < word.length(); j++) {

                // Convert the character into an array index.
                //
                // Example:
                // 'a' - 'a' = 0
                // 'e' - 'a' = 4
                // 't' - 'a' = 19

                int index = word.charAt(j) - 'a';

                // Increase the frequency of that character.
                freq[index]++;

                // Example for "eat":
                //
                // j = 0 -> 'e' -> freq[4] = 1
                // j = 1 -> 'a' -> freq[0] = 1
                // j = 2 -> 't' -> freq[19] = 1
            }

            // STEP 6: Convert the frequency array into a String key.
            //
            // StringBuilder is used to construct the signature.
            // Initially sb = ""

            StringBuilder sb = new StringBuilder();

            // STEP 7: Traverse all 26 frequency positions.
            // k represents the current frequency-array index.

            for (int k = 0; k < freq.length; k++) {

                // Append the frequency followed by a delimiter.
                //
                // Example:
                // freq[0] = 1 -> sb = "1#"
                // freq[1] = 0 -> sb = "1#0#"
                // freq[2] = 0 -> sb = "1#0#0#"
                //
                // Delimiter '#' prevents ambiguous signatures.

                sb.append(freq[k]).append("#");
            }

            // STEP 8: Convert StringBuilder into String.
            //
            // Before:
            // sb  -> StringBuilder containing the signature
            //
            // After:
            // key -> String containing the same signature
            //
            // toString() does not modify sb.

            String key = sb.toString();

            // STEP 9: Check whether this signature already exists.
            //
            // containsKey(key) returns:
            // true  -> This anagram group already exists.
            // false -> This is a new anagram group.

            if (map.containsKey(key)) {

                // EXISTING KEY:
                //
                // map.get(key) retrieves the existing List<String>.
                // .add(word) adds the current word to that same list.
                //
                // Example:
                // Before: ["eat", "tea"]
                // Add:    "ate"
                // After:  ["eat", "tea", "ate"]

                map.get(key).add(word);

            } else {

                // NEW KEY:
                //
                // Create a new list because this signature
                // has not appeared before.

                List<String> list = new ArrayList<>();

                // Add the current word to the new list.
                // Example: list = ["tan"]

                list.add(word);

                // Insert the new key and its corresponding list.
                //
                // map.put(key, list)
                // Key   -> frequency signature
                // Value -> newly created list

                map.put(key, list);
            }
        }

        // STEP 10: Return all the groups.
        //
        // map.values() retrieves all inner lists.
        //
        // new ArrayList<>(map.values()) creates a new outer list.
        //
        // Final structure:
        // List<List<String>>
        //
        // Example:
        // [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]

        return new ArrayList<>(map.values());
    }
}
