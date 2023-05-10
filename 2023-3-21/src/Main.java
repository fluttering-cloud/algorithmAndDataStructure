public class Main {

    public static void main(String[] args) {
        System.out.println(validateIdNumber("431024199911252114"));
    }

    public static boolean validateIdNumber(String idNumber) {
        if (idNumber == null || idNumber.length() != 18) {
            return false;
        }
        char[] chars = idNumber.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = Integer.parseInt(chars[i] + "");
        }
        int[] coefficientArr = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        int[] remainderArr = { 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int sum = 0;
        for (int i = 0; i < coefficientArr.length; i++) {
            sum += coefficientArr[i] * ints[i];
        }
        int remainder = sum % 11;
        String lastCode = idNumber.substring(idNumber.length() - 1);
        String lastChar = remainderArr[remainder] == 10 ? "X" : remainderArr[remainder] + "";
        return lastCode.equalsIgnoreCase(lastChar);
    }
}
