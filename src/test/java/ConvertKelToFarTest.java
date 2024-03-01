import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertKelToFarTest {

    @Test
    void kelToFar() {
        ConvertKelToFar convertKelToFar = new ConvertKelToFar();
        assertEquals(32, convertKelToFar.kelvinToFahrenheit(273.15));
        System.out.println("Test 1 passed");
    }
}