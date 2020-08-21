import java.util.*;

public class ReplaceWords {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.scan);
        System.out.println("Enter your paragraph");
        String paragraph = scan.nextLine();

        final Vector<String> v1 = new Vector<String>();
        v1.add("Happy");
        v1.add("Hello");
        v1.add("World");
        ReplaceWords repObj = new ReplaceWords();
        String ans = repObj.replaceWords(paragraph, v1);
        System.out.println("The new paragraph is:\n" + ans);
        scan.close();
    }

    public String replaceWords(String paragraph, Vector<String> v1) {
        String replacedString = "";
        for (String s : v1) {
            replacedString = replaceString(s);
            paragraph = paragraph.replaceAll(s, replacedString);
        }
        return paragraph;
    }

    public String replaceString(String str) {
        String replaced;
        char first = str.charAt(0);
        replaced = String.valueOf(first);
        for (int i = 0; i < str.length() - 1; i++) {
            replaced = replaced + "*";
        }
        return replaced;
    }
}