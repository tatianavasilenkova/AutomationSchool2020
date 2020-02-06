package com.ctco.testSchool;

import java.util.ArrayList;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {


        for (Planet planet : Planet.values()) {
            if (planet.mass() > 5.0e+24 && planet.radius() > 6.0e+7) {
                System.out.println(planet);
            }
        }



        /**
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        while (scanner.hasNext()) {
            String input = null;
            try {
                if ("0".equals(scanner.nextLine())) {
                    break;
                }
                input = scanner.next();
                Integer result = Integer.parseInt(input) * 10;
                String str = Integer.toString(result);
                arrayList.add(str);
            } catch (Exception e) {
                arrayList.add("Invalid user input: " + input);
            }
        }
         */
    }

    /**
     * Scanner scanner = new Scanner(System.in);
     *         ArrayList<Integer> arrayList = new ArrayList<>();
     *         while (scanner.hasNext()) {
     *             String input = null;
     *             try {
     *                 if ("0".equals(scanner.nextLine())) {
     *                     break;
     *                 }
     *                 input = scanner.next();
     *                 Integer result = Integer.parseInt(input) * 10;
     *                 arrayList.add(result);
     *             } catch (Exception e) {
     *                 System.out.println("Invalid user input: " + input);
     *             }
     *
     */





    //list.add(scanner.toString());
    //scanner.next();

    //System.out.println(list);

    //try {System.out.println(a*10);} catch (Exception e) {System.out.println("Invalid user input: " + a);}

    /**
     String s1 = scanner.next();
     String s2 = scanner.next();
     String s3 = scanner.next();
     String s4 = scanner.next();

     try {
     int a = Integer.parseInt(s1);
     System.out.println(a * 10);
     } catch (Exception e) {
     System.out.println("Invalid user input: " + s1);
     }
     try {
     int b = Integer.parseInt(s2);
     System.out.println(b * 10);
     } catch (Exception e) {
     System.out.println("Invalid user input: " + s2);
     }
     try {
     int c = Integer.parseInt(s3);
     System.out.println(c * 10);
     } catch (Exception e) {
     System.out.println("Invalid user input: " + s3);
     }
     try {
     int d = Integer.parseInt(s4);
     System.out.println(d * 10);
     } catch (Exception e) {
     System.out.println("Invalid user input: " + s4);
     }
     */
}



