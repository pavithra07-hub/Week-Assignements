import java.util.*;

public class Problem4 {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();
    private int N = 5;

    public List<String> generateNGrams(String text) {

        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }
            ngrams.add(sb.toString().trim());
        }

        return ngrams;
    }

    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        for (String ng : ngrams) {
            ngramIndex.putIfAbsent(ng, new HashSet<>());
            ngramIndex.get(ng).add(docId);
        }
    }

    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String ng : ngrams) {

            if (ngramIndex.containsKey(ng)) {

                for (String otherDoc : ngramIndex.get(ng)) {

                    if (!otherDoc.equals(docId)) {
                        matchCount.put(otherDoc,
                                matchCount.getOrDefault(otherDoc, 0) + 1);
                    }
                }
            }
        }

        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);
            double similarity = (matches * 100.0) / ngrams.size();

            System.out.println("Matched with " + doc +
                    " → Similarity: " + similarity + "%");
        }
    }

    public static void main(String[] args) {

        Problem4 detector = new Problem4();

        detector.addDocument("essay_089",
                "this system detects plagiarism using n gram hashing technique");

        detector.addDocument("essay_092",
                "this system detects plagiarism using n gram hashing technique effectively");

        detector.analyzeDocument("essay_123",
                "this system detects plagiarism using n gram hashing technique");
    }
}