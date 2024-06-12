import java.util.*;

/**
 * EX11_MeiboSearchは、表示したメニューに基づいて名簿データの照会処理を行います。<br>
 * 読み込んだ名簿データや照会結果は、クラス変数としてクラス全体で扱います。
 * 
 * @author Aki Tanimoto
 */
public class EX11_MeiboSearch {
    static FileIn fi = new FileIn();
    static String date = GetDate.getFormattedCurrentDate();
    static String[] arrayNumber;
    static String[] arrayName;
    static String[] arrayBirthday;
    static String[] arrayAddress;
    static int[] arrayHit;
    static int hitCount;

    /** コンストラクタ */
    EX11_MeiboSearch(){
    }

    /**
     * mainメソッド 照会メニューを画面に表示します。<br>
     * また、照会処理や結果の表示に必要なメソッドを呼び出します。
     * 
     * @param args コマンドライン引数。txtデータ名を入力し、filename引数に入力した文字列を渡します。<br>
     * filename引数は、fileCountメソッドとstoresElementsメソッドに渡されます。
     */
    public static void main(String[] args) {
        ConsolePassword co = new ConsolePassword();
        KeyIn ki = new KeyIn();
        String command;
        String search = null;
        String filename = args[0];
        int count;
        boolean flag = true;

        count = fileCount(filename);
        generateArray(count);
        storesElements(filename);

        System.out.println("<EX_MeiboSearch> **********　名簿照会処理　********** (" + date + "現在)"
                + "\n" +"-----------------------------------------------------------"
                + "\n" + "メニュー　１）出席番号　２）名前　３）生年月日　Q）終了"
                + "\n" +"-----------------------------------------------------------");

        do{
            flag = true;
            Arrays.fill(arrayHit,-1); //arrayHitのすべての要素に"-1"を格納する
            command = ki.readKey("メニューNo. :");

            switch (command) {
                case "1":
                    System.out.println("出席番号で検索します");
                    search = ki.readKey("出席番号　：");
                    numberSearch(search);
                    break;
        
                case "2":
                    System.out.println("名前で検索します");
                    search = ki.readKey("名前　：");
                    nameSearch(search);
                    break;

                case "3":
                    System.out.println("生年月日で検索します");
                    search = ki.readKey("生年月日（年）　：");
                    search = search + "." + ki.readKey("生年月日（月）　：");
                    search = search + "." + ki.readKey("生年月日（日）　：");
                    birthdaySearch(search);
                    break;
                
                case "q":
                /* break文無し */
                case "Q":
                    System.out.println("**********　名簿照会処理を終了しました　**********");
                    return ;

                default:
                    System.out.println("メニューNO.エラー！");
                    flag = false;
                    break;
            }
            if(flag == true){
                resultDisplay(search);
                co.enter();
                System.out.println("-----------------------------------------------------------"
                        + "\n" + "メニュー　１）出席番号　２）名前　３）生年月日　Q）終了"
                        + "\n" +"-----------------------------------------------------------");
            }
        } while(true);
    }

    /**
     * fileCountメソッドは読み込んだ名簿データの件数を数えます。
     * 
     * @param filename 開く名簿（txtファイル）名
     * @return 数えた名簿の件数をint型で返します
     * 
     */
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

    /**
     * generateArayメソッドはfileCountメソッドで数えた名簿データ件数を基に<br>
     * 名簿データを格納する配列をクラス変数で生成します。
     * 
     * @param count データ件数
     */
    public static void generateArray(int count){
        arrayNumber = new String[count];
        arrayName = new String[count];
        arrayBirthday = new String[count];
        arrayAddress = new String[count];
        arrayHit = new int[count];
        return;
    }

    /**
     * storesElementsメソッドはgenerateArayメソッドで生成した配列に名簿データを格納します。
     * 
     * @param filename 名簿データ名
     */
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
            arrayName[i] = element[SURNAME_POSITION];
            arrayName[i] = arrayName[i] + element[FIRSTNAME_POSITION];
            arrayBirthday[i] = element[YEAR_POSITION];
            arrayBirthday[i] = arrayBirthday[i] + "." + element[MONTH_POSITION];
            arrayBirthday[i] = arrayBirthday[i] + "." + element[DAY_POSITION];
            arrayAddress[i] = element[ADDRESS_POSITION];
            buff = fi.readLine();
        }
        fi.close();
        return;
    }

    /**
     * numberSearchメソッドは出席番号を参照して検索を行います。（完全一致のみ）<br>
     * 検索がヒットすると、ヒット数とヒットデータが格納されている配列番号を記録します。
     * 
     * @param number 検索する出席番号。mainメソッドから呼び出す際に入力を求められます。
     */
    public static void numberSearch(String number){
        int search = 0;
        hitCount = 0;
        while ((search < arrayNumber.length)&&(!(number.equals(arrayNumber[search]))) ) {
            search++;
        }

        if (search < arrayNumber.length) {
            arrayHit[hitCount] = search;
            hitCount++;
        }
        return;
    }

    /**
     * nameSearchメソッドは名前を参照して検索を行います。（完全一致のみ）<br>
     * 検索がヒットすると、ヒット数とヒットデータが格納されている配列番号を記録します。
     * 
     * @param name 検索する名前。mainメソッドから呼び出す際に入力を求められます。
     */
    public static void nameSearch(String name){
        hitCount = 0;
        for(int i = 0; i < arrayName.length; i++){
            if(name.equals(arrayName[i])){
                arrayHit[hitCount] = i;
                hitCount++;
            }
        }
        return;
    }

    /**
     * birthdaySearchメソッドは生年月日を参照して検索を行います。（完全一致のみ）<br>
     * 検索がヒットすると、ヒット数とヒットデータが格納されている配列番号を記録します。
     * 
     * @param birthday 検索する生年月日。mainメソッドから呼び出す際に入力を求められます。
     */
    public static void birthdaySearch(String birthday){
        hitCount = 0;
        for(int i = 0; i < arrayBirthday.length; i++){
            if(birthday.equals(arrayBirthday[i])){
                arrayHit[hitCount] = i;
                hitCount++;
            }
        }
        return;
    }

    /**
     * resultDisplayメソッドは各検索メソッドの検索結果を画面に表示します。<br>
     * また、検索がヒットしなかった場合は、該当者なしと表示します。
     * 
     * @param search 検索条件。検索メソッドに渡したキーワードを受け取り、結果と一緒に表示します。
     */
    public static void resultDisplay(String search){
        System.out.println("<EX11_MeiboSearch>\t名簿データ検索表示\t(" + date + "現在)");
        System.out.println("(検索条件＝" + search + "、検索件数＝" + hitCount + "件)");
        System.out.println("出席番号\t氏名　　　　\t生年月日\t住所\n" +
                           "--------\t------------\t----------\t-----");
        if(hitCount == 0){
            System.out.println("\t\t該　当　者　な　し");
        }else{
            for(int i=0;i < hitCount;i++){
                System.out.println(arrayNumber[arrayHit[i]] +"\t\t"+ arrayName[arrayHit[i]] +"\t"+ arrayBirthday[arrayHit[i]] + "\t" + arrayAddress[arrayHit[i]]);
            }
        }
        System.out.println("-----------------------------------------------------");
    }
}
