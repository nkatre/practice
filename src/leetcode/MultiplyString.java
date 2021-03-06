package leetcode;

import java.util.Scanner;

/**
 * Created by z001ktb on 10/13/16.
 */
public class MultiplyString {
//    public static String multiply(String num1, String num2) {
//        if(num1.equals("0") || num2.equals("0")) return "0";
//        StringBuilder sb = new StringBuilder();
//        if(num1.isEmpty()|| num2.isEmpty()) return sb.toString();
//        int col = num1.length() + num2.length();
//        int[] grid = new int[col];
//        if(num1.length() < num2.length()){
//            String s = num1;
//            num1 = num2;
//            num2 = s;
//        }
//        int carry = 0;
//        int k = 0;
//        int colIndex = col-1-k;
//        for(int i = num2.length() - 1; i >=0; i--){
//            int n2 = Character.getNumericValue(num2.charAt(i));
//            for(int j = num1.length() - 1; j >=0; j--){
//                int n1 = Character.getNumericValue(num1.charAt(j));
//                grid[colIndex] += (carry + n1*n2);
//                carry = grid[colIndex]/10;
//                grid[colIndex] %= 10;
//                colIndex--;
//            }
//            if(carry != 0) {
//                grid[colIndex] = carry;
//                carry = 0;
//            }
//            k++;
//            colIndex = col-1-k;
//        }
//
//        if(grid[0] != 0) sb.append(grid[0]);
//        for(int i = 1; i < grid.length; i++){
//            sb.append(grid[i]);
//        }
//        return sb.toString();
//    }

    public static String multiply(String str1, String str2) {
        if(str1 == null || str1.isEmpty()) return str2;
        if(str2 == null || str2.isEmpty()) return str1;
        if(str1.length() < str2.length()) {
            String tmp = str1;
            str1 = str2;
            str2 = tmp;
        }

        String result = "";
        int carry = 0;
        for(int j = str2.length() - 1; j >= 0; j--) {
            int p1 = Character.getNumericValue(str2.charAt(j));
            StringBuilder local = new StringBuilder();
            for(int k = 0; k < str2.length() - 1 - j; k++) {
                local.append(0);
            }
            for(int i = str1.length() - 1; i >= 0; i--) {
                int p2 = Character.getNumericValue(str1.charAt(i));
                local.insert(0,(p1 * p2 + carry) % 10);
                carry = (p1 * p2 + carry) / 10;
            }
            if(carry != 0) {
                local.insert(0, carry);
            }
            result = add(local.toString(), result);
            carry = 0;
        }
        return result;
    }

    private static String add(String str1, String str2) {
        if(str1 == null || str1.isEmpty()) return str2;
        if(str2 == null || str2.isEmpty()) return str1;
        StringBuilder sb = new StringBuilder();
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0) {
            while(i >= 0 && j >= 0) {
                int tmp = Character.getNumericValue(str1.charAt(i)) + Character.getNumericValue(str2.charAt(j)) + carry;
                sb.insert(0,tmp % 10);
                if(tmp > 9) carry = 1;
                else carry = 0;
                i--;
                j--;
            }
            if(i >= 0) {
                int tmp = Character.getNumericValue(str1.charAt(i)) + carry;
                sb.insert(0,tmp % 10);
                if(tmp > 9) carry = 1;
                else carry = 0;
                i--;
            }
            if(j >= 0) {
                int tmp = Character.getNumericValue(str2.charAt(j)) + carry;
                sb.insert(0,tmp % 10);
                if(tmp > 9) carry = 1;
                else carry = 0;
                j--;
            }
        }
        if(carry == 1) sb.insert(0,1);
        return sb.toString();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true) {
            String s1 = sc.next();
            String s2 = sc.next();
            System.out.println(multiply(s1, s2));
        }
    }
}
