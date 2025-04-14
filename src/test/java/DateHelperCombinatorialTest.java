import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

public class DateHelperCombinatorialTest {

    @Test
    public void testValidDates() {
        String[] dates = {"01/01/2020", "31/12/1999", "29/02/2020", "15/08/1947"};
        for (String date : dates) {
            long timestamp = com.fastaccess.tfl.helper.DateHelper.getDateOnly(date);
            String formatted = com.fastaccess.tfl.helper.DateHelper.getDateOnly(timestamp);
            assertEquals(date, formatted, "Round-trip failed for: " + date);
        }
    }
}
