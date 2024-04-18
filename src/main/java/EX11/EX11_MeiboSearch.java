public class EX11_MeiboSearch {
    int[] arrayNumber;
    String[] arrayName;
    String[] arrayBirth;
    String[] arrayAddress;

    public static void main(String[] args) {
        KeyIn ki = new KeyIn();
        String command;
        String date = "日付";
        boolean flag = false;

        System.out.println("<EX_MeiboSearch>**********　名簿照会処理　**********" + date
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
}
