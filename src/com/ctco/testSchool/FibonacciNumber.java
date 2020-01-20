package com.ctco.testSchool;

public class FibonacciNumber {

    int firstNumber = 1;

    int secondNumber = 2;

    public int getSequenceNumberByIndex(int sequenceNumber) {
        //TODO: implement this method to return actual sequence number F(n), where n = sequenceNumber.  I.e. return F(n) instead of 1
        int previousNumber = firstNumber;
        int nextNumber = secondNumber;

        if (sequenceNumber == 1) return firstNumber;                 // if given Fibonachi sequence number is 1, return F(1) = 1
        if (sequenceNumber == 2) return secondNumber;                // if given Fibonachi sequence number is 2, return F(2) = 2

        for (int i = 3; i <= sequenceNumber; i++) {                  // do until we reach given sequence number. Start to count from F(3)
            int fiboNumber = previousNumber + nextNumber;            // count next Fibonachi number E.g.: F(3) = F(1) + F(2)
            previousNumber = nextNumber;                             // assign new Fibonachi numbers E.g.: F(1) now equals F(2)
            nextNumber = fiboNumber;                                 // assign new Fibonachi numbers E.g.: F(2) now equals F(3)
        }

        for (int i = 0; i >= sequenceNumber; i--) {
            int fiboNumber = previousNumber - nextNumber;            // count next Fibonachi number E.g.: F(-1) = F(0) - F(1)
            previousNumber = nextNumber;                             // assign new Fibonachi numbers E.g.: F(1) now equals F(2)
            nextNumber = fiboNumber;
        }
        return nextNumber;                                           // return result of found Fibonachi number - F(n)
    }

    public int getSequenceNumberLargerThan(int number) {
        //TODO: implement this method to return F(n) which is greater than input number.

        int a = 0;
       // int compareWith = getSequenceNumberByIndex(number);   // count Fibonachi number of the given sequence number
        int result;

        if (firstNumber == 0 && secondNumber == 0) {
            return 0;}

        do {
            result = getSequenceNumberByIndex(a);             // count next Fibonachi number and compare with given
            a++;
        } while (result <= number);       // stop the loop when next Fibonachi number become larger than given




        return result;                         // return next Fibonachi number
    }

    @Step("Method to return previous F(n) of the given Fibo number.")
    public int getPreviousFiboNumber(int fiboNumber) {
        int n = getSequenceNumber(fiboNumber);
        return getSequenceNumberByIndex(n-1);
    }

    @Step("Return sequence number of the given Fibonachi number")
    public int getSequenceNumber(int fiboNumber) {
        // F(n)=F(n-1)+F(n-2)
        // n = round{alfa * log(Fn) + beta}
        // alfa = 1/log(eta) = 2.07807
        // beta = log(sqrt5)/log(eta) = 1.672276
        // int nextFibo = firstNumber + secondNumber;  // count next fibo number
        float n = (float) (2.07807F * Math.log(fiboNumber) + 1.672276F);                     // count given Fibanachi number - sequence number E.g.: given F(n)=55 then it's sequence number n=10
        return Math.round(n);
    }
}