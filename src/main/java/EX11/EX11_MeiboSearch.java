import java.util.*;

public class EX11_MeiboSearch {
    static FileIn fi = new FileIn();
    static String[] arrayNumber;
    static String[] arraySurname;
    static String[] arrayFirstname;
    static String[] arrayBirthyear;
    static String[] arrayBirthmonth;
    static String[] arrayBirthday;
    static String[] arrayAddress;

    public static void main(String[] args) {
        KeyIn ki = new KeyIn();
        String command;
        String filename = args[0];
        String date = GetDate.getFormattedCurrentDate();
        int count;
        boolean flag = false;

        count = fileCount(filename);
        generateArray(count);
        storesElements(filename);

        System.out.println("<EX_MeiboSearch> **********　名簿照会処理　********** (" + date + "現在)"
                + "\n" +"-----------------------------------------------------------"
                + "\n" + "メニュー　１）出席番号　２）名前　３）生年月日　Q）終了"
                + "\n" +"-----------------------------------------------------------");
        do{
            command = ki.readKey("メニューNo. :");
            switch (command) {
                case "1":
                    System.out.println("出席番号の処理");
                    break;
        
                case "2":
                    System.out.println("名前の処理");
                    break;

                case "3":
                    System.out.println("生年月日の処理");
                    break;
                
                case "q":
                /* break文無し */
                case "Q":
                    System.out.println("**********　名簿照会処理を終了しました　**********");
                    flag = true;
                    break;

                default:
                    System.out.println("エラー！");
                    break;
            }
        } while(flag == false);
    }

    public static int fileCount(String filename){
        String buff;
        int count = 0;
        fi.open(filename);
        buff = fi.readLine();
        while (buff != null) {
            count++;
            buff = fi.readLine();
        }
        fi.close();
        return count;
    }

    public static void generateArray(int count){
        arrayNumber = new String[count];
        arraySurname = new String[count];
        arrayFirstname = new String[count];
        arrayBirthyear = new String[count];
        arrayBirthmonth = new String[count];
        arrayBirthday = new String[count];
        arrayAddress = new String[count];
        return;
    }

    public static void storesElements(String filename){
        final int NUMBER_POSITION = 0;
        final int SURNAME_POSITION = 1;
        final int FIRSTNAME_POSITION = 2;
        final int YEAR_POSITION = 4;
        final int MONTH_POSITION = 5;
        final int DAY_POSITION = 6;
        final int ADDRESS_POSITION = 7;

        fi.open(filename);
        String buff = fi.readLine();

        for (int i = 0; i < arrayNumber.length;i++){
            String[] element = buff.split(",");
            arrayNumber[i] = element[NUMBER_POSITION];
            arraySurname[i] = element[SURNAME_POSITION];
            arrayFirstname[i] = element[FIRSTNAME_POSITION];
            arrayBirthyear[i] = element[YEAR_POSITION];
            arrayBirthmonth[i] = element[MONTH_POSITION];
            arrayBirthday[i] = element[DAY_POSITION];
            arrayAddress[i] = element[ADDRESS_POSITION];
            buff = fi.readLine();
        }
        fi.close();
        return;
    }
}
