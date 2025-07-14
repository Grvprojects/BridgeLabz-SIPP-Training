
public class WordSearch {
    public static String findSentence(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) return sentence;
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {"Java is awesome", "I love coding", "Hello world"};
        System.out.println(findSentence(sentences, "love")); 
    }
}
