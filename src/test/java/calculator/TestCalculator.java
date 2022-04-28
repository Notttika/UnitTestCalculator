package calculator;

import java.util.InputMismatchException;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class TestCalculator {


   private Calculator calculator;

     @BeforeAll
     static void initBeforeAll() {
         System.out.println("Before all called");
     }
     @BeforeEach
     void init() {
        System.out.println("before each called");
         calculator = new Calculator();
     }
    @Test
    @DisplayName("Test for sum")
    void testSum() {
        //GIVEN
        int first = 3;
        int second = 7;
        String operation = "+";
        String expectedResult = "10.00";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);

    }

    @Test
    @DisplayName("Test for multi")
    void testMultiplication() {
        //GIVEN
        int first = 2;
        int second = 6;
        String operation = "*";
        String expectedResult = "12.00";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);

    }

    @Test
    @DisplayName("Test for sub")
    void testSubtraction() {
        //GIVEN
        int first = 7;
        int second = 9;
        String operation = "-";
        String expectedResult = "-2.00";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);

    }
    @Test
    @DisplayName("Test for division")
    void testDivision() {
        //GIVEN
        int first = 5;
        int second = 3;
        String operation = "/";
        String expectedResult = "1.67";

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);

    }


    @ParameterizedTest
    @MethodSource("allOperations")
    void testSum_paramMethod(int first, int second, String operation, String expectedResult) {
        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> allOperations() { //provideValidData()
        return Stream.of(
                Arguments.of(4,4, "*", "16.00"),
                Arguments.of(4,4,"/","1.00"),
                Arguments.of(4,4,"+","8.00"),
                Arguments.of(4,4,"-","0.00")
        );
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/TestData.csv")
    void testSum_paramCsv(String operation, String expectedResult) {
        //GIVEN
        int second = 5;
        int first = 5;

        //WHEN
        String actualResult = calculator.calculate(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void  testCheckExceptionIsThrown(){
        //GIVEN
        int first = 7;
        int second = 0;
        String operation = "/";
        String expectedExceptionMessage = "Division by zero is not allowed";

        //WHEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(first, second, operation));
        //THEN
        assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());

    }
    @Test
    void  testCheckExceptionIsNegativeFirst(){
        //GIVEN
        int first = -5;
        int second = 6;
        String operation = "+";
        String expectedExceptionMessage = "Negative number";

        //WHEN
        InputMismatchException inputMismatchException = assertThrows(InputMismatchException.class,
                () -> calculator.calculate(first, second, operation));
        //THEN

        assertEquals(expectedExceptionMessage, inputMismatchException.getMessage());

    }
    @Test
    void  testCheckExceptionIsNegativeSecond(){
        //GIVEN
        int first = 5;
        int second = -6;
        String operation = "+";
        String expectedExceptionMessage = "Negative number";

        //WHEN
        InputMismatchException inputMismatchException = assertThrows(InputMismatchException.class,
                () -> calculator.calculate(first, second, operation));
        //THEN

        assertEquals(expectedExceptionMessage, inputMismatchException.getMessage());

    }
    @Test
    void  testCheckExceptionInputData(){
        //GIVEN
        int first = 3;
        int second = 5;
        String operation = "g";
        String expectedExceptionMessage = "Wrong input data!";

        //WHEN
        NumberFormatException numberFormatException = assertThrows(NumberFormatException.class,
                () -> calculator.calculate(first, second, operation));
        //THEN

        assertEquals(expectedExceptionMessage,numberFormatException.getMessage());

    }
}