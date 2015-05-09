package baminsurances.data.generation;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Provides methods for generating dates and related data. Methods are added as
 * we find the need for them.
 * <p>
 * The class was introduced because several of the generator classes were in
 * need of different dates. Therefore it is now being "outsourced", instead of
 * lying in one of the other generators.
 * <p>
 * In contrast to the other generator classes, this one has only static methods
 * for the time being. This is do to the fact that the class does not load any
 * resources, and therefore does not "contain" anything.
 * 
 * @author martin
 */
public class DateGenerator {
    
    /**
     * Generates and returns a date between the two given dates (both
     * inclusive).
     * 
     * @param lower the lower limit date (inclusive)
     * @param upper the upper limit date (inclusive)
     * @return a date between the two given dates (both inclusive)
     */
    public static LocalDate generateDateInRange(LocalDate lower,
            LocalDate upper) {
        Period diff = lower.until(upper);
        int year = (int) (Math.random() * diff.getYears()) + lower.getYear();
        int month;
        int day;
        
        if (year == lower.getYear()) {
            month = (int) (Math.random() * (13 - lower.getMonthValue()))
                    + lower.getMonthValue();
            if (month == lower.getMonthValue()) {
                Calendar cal = new GregorianCalendar(year, month - 1, 1);
                int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                day = (int) (Math.random() * (maxDays - lower.getDayOfMonth()))
                        + lower.getDayOfMonth() + 1;
                if (day > maxDays) {
                    day = 1;
                    month++;
                    if (month > 12) {
                        month = 1;
                        year++;
                    }
                }
            } else {
                day = generateDay(year, month);
            }
        } else {
            month = (int) (Math.random() * 12) + 1;
            day = generateDay(year, month);
        }
        
        return LocalDate.of(year, month, day);
    }
    
    /**
     * Generates a new date before the current date, and after the given date
     * (both inclusive).
     * 
     * @param date the date
     * @return a new date before the current date, and after the given date
     * (both inclusive)
     */
    public static LocalDate generateBeforeNowAndAfter(LocalDate date) {
        return generateDateInRange(date, LocalDate.now());
    }
    
    /**
     * Generates a valid day based on a given year and month.
     * 
     * @param year the year
     * @param month the month (1-12)
     * @return a valid day based on the given year and month
     */
    public static int generateDay(Year year, Month month) {
        switch (month.getValue()) {
        case 1: case 3:
        case 5: case 7:
        case 8: case 10:
        case 12:
            return 31;
        case 4: case 6:
        case 9: case 11:
            return 30;
        case 2:
            if (year.isLeap()) {
                return 29;
            } else {
                return 28;
            }
        default:
            return -1;
        }
    }
    
    /**
     * Generates a valid day based on a given year and month.
     * 
     * @param year the year
     * @param month the month (1-12)
     * @return a valid day based on the given year and month
     */
    public static int generateDay(int year, int month) {
        return generateDay(Year.of(year), Month.of(month));
    }
}
