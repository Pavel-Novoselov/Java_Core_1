package extra;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoursCalc {
    private int day;

    private void numToday(){
        Calendar newCal = new GregorianCalendar();
        day = newCal.get( Calendar.DAY_OF_WEEK );
    }

    public void calcHours(){
        numToday();
        for (DWeek d: DWeek.values()){
            //  System.out.println(d.getHours());//test print
            if (d.getdNum()==day && d.getHours()==0){
                System.out.println("Today is a weekend!");
            }
            else if (d.getdNum()==day && d.getHours()!=0){
                System.out.println("Today is a "+d.name());
                System.out.println(d.getHours()+" hours left till the end of the working week");
            }
        }
    }

}
