import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateHelperMetamorphicTest {

    @Test
    public void testTodayToTomorrow() {
        String today = com.fastaccess.tfl.helper.DateHelper.getToday();
        String tomorrow = com.fastaccess.tfl.helper.DateHelper.getTomorrow();
        long todayTime = com.fastaccess.tfl.helper.DateHelper.getDateOnly(today);
        long tomorrowTime = com.fastaccess.tfl.helper.DateHelper.getDateOnly(tomorrow);
        assertEquals(1, (tomorrowTime - todayTime) / (1000 * 60 * 60 * 24),
                "Tomorrow should be one day after today.");
    }

    @Test
    public void testFormatParseInvariant() {
        long now = System.currentTimeMillis();
        String formatted = com.fastaccess.tfl.helper.DateHelper.getDesiredFormat(com.fastaccess.tfl.helper.DateHelper.DateFormats.D_DDMMYYYY, now);
        long parsed = com.fastaccess.tfl.helper.DateHelper.parseDate(formatted, com.fastaccess.tfl.helper.DateHelper.DateFormats.D_DDMMYYYY);
        assertEquals(com.fastaccess.tfl.helper.DateHelper.getDateOnly(now), com.fastaccess.tfl.helper.DateHelper.getDateOnly(parsed),
                "Format → parse should return original date.");
    }
}
