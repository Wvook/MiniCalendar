import java.text.ParseException;
import java.util.Scanner;
// 클래스로 구혀하기 부터 시작하면 된다.
public class Prompt {

    private void cmdCal(Scanner s, Calendar c) {
        int month = 1;
        int year = 2020;

        System.out.println("연도를 입력하세요.(exit: -1)");
        System.out.print("YEAR> ");
        year = s.nextInt();

        System.out.println("월을 입력하세요:");
        System.out.print("MONTH> ");
        month = s.nextInt();

        if (month >12 || month <1) {
            System.out.println("잘못된 입력 입니다.");

        } else{ c.printCalendar(year, month);;

            System.out.println("");
        }
    }


    public void runPrompt() throws ParseException {

        printMenu();
        Scanner scanner = new Scanner(System.in);
        Calendar cal = new Calendar();

        boolean isLoop = true;
        while (isLoop) {

            String cmd = scanner.next();

            switch(cmd) {
                case "1" :
                    cmdRegister(scanner, cal);
                    break;
                case "2" :
                    cmdSearch(scanner, cal);
                    break;
                case "3" :
                    cmdCal(scanner, cal);
                    break;
                case "h" :
                    printMenu();
                    break;
                case "q" :
                    isLoop = false;
                    break;
                default :
                    printMenu();
                    break;
            }
        }
        System.out.println("Thanks, Bye~");
        scanner.close();
    }



    private void printMenu() {
        System.out.println("+----------------------+");
        System.out.println("| 1. 일정 등록");
        System.out.println("| 2. 일정 검색");
        System.out.println("| 3. 달력 보기");
        System.out.println("| h. 도움말 q. 종료");
        System.out.println("+----------------------+");
    }

    private void cmdSearch(Scanner s, Calendar c) throws ParseException {
        System.out.println("[일정 검색]");
        System.out.println("날짜를 입력해주세요 (yyyy-MM-dd)");
        String date = s.next();
        String plan = c.searchPlan(date);

        System.out.println(plan);
    }

    private void cmdRegister(Scanner s, Calendar c) throws ParseException {
        System.out.println("[새 일정 등록]");
        System.out.println("날짜를 입력해 주세요 (yyyy-MM-dd).");
        String date = s.next();
        String text = "";
        s.nextLine(); //ignore one newline
        System.out.println("일정을 입력해 주세요.");
        text = s.nextLine();

        c.registerPlan(date, text);
    }

    public static void main(String[] args) throws ParseException {
        Calendar cal = new Calendar();
        Prompt p = new Prompt();
        p.runPrompt();

        cal.registerPlan("2020-07-08", "Let's hava dinner!");


    }
}

