import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

public class Test {

    public static void main(String[] args) throws  Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate start = LocalDateTime.ofInstant(sdf.parse("2019-11-30").toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate end = LocalDateTime.ofInstant(sdf.parse("2020-03-01").toInstant(), ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(start, end);
        //System.err.println(period.getYears() * 12 + period.getMonths());


        Calendar c = Calendar.getInstance();
        c.set(2019,10,30);
        System.err.println(sdf.format(c.getTime()));
        c.add(Calendar.MONTH,3);
        System.err.println(sdf.format(c.getTime()));


    }
}
