package facultative;

import java.util.Calendar;
import java.util.GregorianCalendar;

enum DWeek{
    MONDAY (40,1), TUESDAY(32,2), WEDNESDAY(24,3), THURSDAY(16,4), FRIDAY(8,5), SATURDAY(0,6), SONDAY(0,7);

    private int hours; //hours till the end of working week
    private int dNum; //the number of week-day

    DWeek(int hours, int dNum) {
        this.hours = hours;
        this.dNum = dNum;
    }

    public int getHours() {
        return hours;
    }

    public int getdNum() {
        return dNum;
    }

}

public class DayOfWeek {
    public static void main(String[] args) {
        Calendar newCal = new GregorianCalendar();
        int day = newCal.get( Calendar.DAY_OF_WEEK );
        //System.out.println(day);

        for (DWeek d: DWeek.values()){
          //  System.out.println(d.getHours());
            if (d.getdNum()==day && d.getHours()==0){
                System.out.println("Today is a weekend!");
            }
            else if (d.getdNum()==day && d.getHours()!=0){
                System.out.println(d.getHours()+" hours left till the end of the working week");
            }
        }


    }
}
