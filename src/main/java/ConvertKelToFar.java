public class ConvertKelToFar {
    public double kelvinToFahrenheit(double kelvin) {
        double result = (kelvin - 273.15) * 9/5 + 32;
        System.out.println("The " + kelvin + " kelvins in Fahrenheit is: " + result);
        return result;
    }

    public static void main(String[] args) {
        ConvertKelToFar convertKelToFar = new ConvertKelToFar();
        convertKelToFar.kelvinToFahrenheit(273.15);
    }
}
