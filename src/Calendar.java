import java.util.HashMap;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
// 2번 수정 보기
public class Calendar {
    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_YEAR = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isLeapYear(int year){
        if((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static int getMaxDaysofMonth(int year, int month){
        if((isLeapYear(year))){
            return LEAP_YEAR[month];
        }
        else {
            return MAX_DAYS[month];
        }
    }

    public static int getWeekDay(int year, int month,int day){
        int syear = 1970;
        final int STANDARD_WEEKDAY = 4;
        int count = 0;

        for(int i = syear; i < year; i++){
            int delta = isLeapYear(i) ? 366 : 365;
            count += delta;
        }
        for(int i = 1; i < month; i++){
            int delta = getMaxDaysofMonth(year,i);
            count += delta;
        }

        count = day - 1; //이게 있으면 2칸이 더 밀림 그래서 배열의 0번째에 0을 넣어주고 month - 1을 month로 바꿔줌

        int weekday = (count + STANDARD_WEEKDAY) % 7;

        return weekday;
    }


    public void printCalendar(int year, int month){
        int weekday = getWeekDay(year, month, 1);
        int count = 7 - weekday;
        int delim = count < 7 ? count : 0;
        System.out.printf("    <<%d년 %d월>>\n",year, month);
        System.out.println(" SU MO TU WE TH FR SA");
        System.out.println("---------------------");

        for(int i = 0; i < weekday; i++){
            System.out.print("   ");
        }

        int maxDay = getMaxDaysofMonth(year,month);
        for(int i = 1; i <= count; i++){
            System.out.printf("%3d", i);
        }
        System.out.println();
        count++;
        for (int i = count; i <= maxDay; i++) { // 달력의 두번째줄
            System.out.printf("%3d", i);
            if (i % 7 == delim) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void registerPlan(String strDate, String plan) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        planMap.put(date, plan);
        System.out.println(date);
    }

    private static HashMap<Date, String> planMap;

    public Calendar() {
        planMap = new HashMap<Date, String>();
    }

    public String searchPlan(String strDate) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        String plan = planMap.get(date);
        return plan;
    }
}


