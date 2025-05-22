package experiments;

public class ExceptionExample {
    public static void main(String[] args) {
        String[] strArray = {"str1", "str2", "str3"};
        uncheckedException(strArray);
    }

    public static void uncheckedException(String[] strArray) {
        try {
            for (int i = 0; i < strArray.length; i++) {
                System.out.println(strArray[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while processing the array", e);
        }
        System.out.println("Program is working");
    }
}
