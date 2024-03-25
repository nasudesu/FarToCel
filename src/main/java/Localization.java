import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Localization {
    public static void main(String[] args) {

        //Locale locale_UK = new Locale("en", "UK");
        //Locale locale_FI = new Locale("fi", "FI");
        //Locale locale_JP = new Locale("ja", "JP");
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale_JP);
        //System.out.println(resourceBundle.getString("greetings"));

        Locale locale = Locale.getDefault();

        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayName());
        System.out.println(locale.getISO3Country());
        System.out.println(locale.getISO3Language());
        System.out.println(locale.getLanguage());
        System.out.println(locale.getCountry());

        Locale defaultLocale = new Locale("en", "UK");
        Date currentDate = new Date();

        //DateFormat timeFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
        //System.out.println(timeFormat.format(currentDate));

        System.out.println(defaultLocale.getCountry() + "  " + defaultLocale.getLanguage());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", defaultLocale);



        String timeFormatP = resourceBundle.getString("timeFormatPattern");
        DateFormat dt = new SimpleDateFormat(timeFormatP);
        String greetings = resourceBundle.getString("greetings");
        String goodbye = resourceBundle.getString("goodbye");

        System.out.println(greetings);
        System.out.println(goodbye);
        System.out.println(dt.format(currentDate));

    }
}
