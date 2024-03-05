public class ConvertKelToFar {
    public double kelvinToFahrenheit(double kelvin) {
        double result = (kelvin - 273.15) * 9/5 + 32;
        System.out.println("The temperature in Fahrenheit is: " + result);
        return result;
    }
}
