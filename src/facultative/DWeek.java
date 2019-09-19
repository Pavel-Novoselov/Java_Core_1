package facultative;

public enum DWeek{
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