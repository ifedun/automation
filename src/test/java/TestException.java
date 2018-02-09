import java.io.IOException;
import java.util.Scanner;

public class TestException {

    public void checkValue() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int value = scanner.nextInt();
        if (value < 3) {
            throw new java.io.IOException("Number is not acceptable");
        }
    }

    public static void main(String[] args) {

        TestException test = new TestException();

        try {
            test.checkValue();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}





