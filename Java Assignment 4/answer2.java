public class answer2 {

	public static void main(String[] args) {
        int integerVal = -1;
        System.out.print("The initial integer value is: ");
        System.out.println(integerVal);

        byte byteVal = (byte)integerVal;
        System.out.print("Integer to byte conversion gives us: ");
        System.out.println(byteVal);

        char charVal = (char)byteVal;
        System.out.print("Byte to char conversion gives us: ");
        System.out.println(charVal);
        
        int finalInteger = (int)charVal;
        System.out.print("Converting from char to integer finally gives us: ");
        System.out.println(finalInteger);
    }
}

