package com.ctco.testSchool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReainCheck {

}

    class FirstTask {
        public static String concatenate(int first, int second, String third) {
            return ++first + third + second;               // ++first means +1. E.g.: 6+1
        }
    }

class SecondTask {

    public static Boolean isEven(Integer x) {
        return x % 2 == 0;
    }
}

class ThirdTask {
        public int getElementPosition(String[] words, String word) {
            for(int i=0; i<words.length; i++) {
                if(words[i].equals(word)) {
                    return i;
                }
            }
            return -1;
        }
    }

    class FourthTask {
        public double a;
        public double b;
        public FourthTask(int a, int b) {  // eto konstruktor kotorij sozdajot objekti.
            this.a = a;
            this.b = b;
        }
        public double getPerimeter() {
            return 2*a + 2*b;
        }
    }

    class FifthTask {
        public String isRectangle(FourthTask fourthTask) {
            if(fourthTask.getPerimeter()==(2*fourthTask.a+2*fourthTask.b)) {
                return "true";
            } return "false";
        }
    }

    class SixthTask {
        public static String isNumberPositive(int number) {
            while(number>0) {
                return "positive";  // esli ubratj etot return to proga zaciklitsja. While tut po suti rabotajet kak IF
            } return "negative";
        }
    }

    class SeventhTask {
        static class Dog {
            private String name;
            public String breed;
            public Dog(String name) {
                this.name = name;
            }
        }
        public static String getDogBreed(Dog dog) {
            return dog.breed;
        }
    }

    class EightTask {
        List<String> myList = new ArrayList<>();
        public List<String> addStringsToList(String... args) {
            for(String s : args) {
                myList.add(s);
            }
            return myList;
        }
    }

    class NinthTask {
        //Method merges two dates into one by choosing the largest value of each component
        public static LocalDateTime merge(String firstDate, String secondDate) {
            final LocalDateTime firstDateTime = LocalDateTime.parse(firstDate);
            final LocalDateTime secondDateTime = LocalDateTime.parse(firstDate);
            return LocalDateTime.of(
                    Math.max(firstDateTime.getYear(), secondDateTime.getYear()),
                    Math.max(firstDateTime.getMonthValue(), secondDateTime.getMonthValue()),
                    Math.max(firstDateTime.getDayOfMonth(), secondDateTime.getDayOfMonth()),
                    Math.max(firstDateTime.getHour(), secondDateTime.getHour()),
                    Math.max(firstDateTime.getMinute(), secondDateTime.getMinute()),
                    Math.max(firstDateTime.getSecond(), secondDateTime.getSecond())
            );
        }
    }
