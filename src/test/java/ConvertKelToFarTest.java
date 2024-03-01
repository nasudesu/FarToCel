import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertKelToFarTest {

    @Test
    void kelToFar() {
        ConvertKelToFar convertKelToFar = new ConvertKelToFar();
        assertEquals(32, convertKelToFar.kelvinToFahrenheit(273.15));
    }
}