package lt.codeacademy.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomTests {

    private String name;

    List<Integer> ints = Arrays.asList(1, 15, -5, 60, 7, 25);

    @BeforeEach
    public void setUp() {
        name = "ABC";
    }

    @DisplayName("Should return 2 when 2 strings are length of 2")
    @Test
    public void shouldReturnTwoWhenTwoStringsAreOfLengthTwo() {
        List<String> words = Arrays.asList("a", "bb", "ccc", "dd");

        List<String> result = words.stream()
                .filter(w -> w.length() == 2)
                .collect(Collectors.toList());

        Assertions.assertTrue(result.size() == 2);
        Assertions.assertEquals(Arrays.asList("bb", "dd"), result);
    }

    @Disabled("Not working correctly")
    @Test
    public void shouldGetThirdCharacter() {
        Character c = name.charAt(2);
        Assertions.assertNotNull(c);
    }

    @Test
    public void shouldRemoveLastCharacter() {
        name = name.substring(0, 2);
        Assertions.assertEquals(2, name.length());
    }

    // Užduotis:
    // Turime List<Integer> ints = Arrays.asList(1, 15, -5, 60, 7, 25);
    // 1. Patikrinti ar liste yra lyginis skaičius skaičių
    @Test
    public void shouldBeEvenNumberOfElements() {
        Assertions.assertTrue(ints.size() % 2 == 0);
    }

    // 2. Patikrinti, kad liste būtų minimum du skaičiai kurie dalinasi iš 5
    @Test
    public void shouldBeMinimumOfTwoElementsThatIsDivisibleBy5() {
        long countOfDivisible = ints.stream()
                .filter(e -> e % 5 ==0)
                .count();

        Assertions.assertEquals(4L, countOfDivisible);
    }

    // 3. Patikrinti, kad jei bandome pasiekti neegzistuojantį indexą išmestų ArrayOutOfBoundsException
    @Test
    public void shouldThrowExceptionIfIndexBig() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> ints.get(155));
    }

    // 4. Patikrinti, kad liste nėra null reikšmės;
    @Test
    public void shouldNotHaveNullValue() {
        Assertions.assertTrue(!ints.contains(null));
    }

    @Test
    public void shouldThrowRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> throwException());
    }

    private void throwException() {
        throw new RuntimeException();
    }
}
