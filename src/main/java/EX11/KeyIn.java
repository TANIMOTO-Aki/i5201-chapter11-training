import java.io.*;
import java.nio.charset.Charset;

public class KeyIn {
    String buf = null;
    int i;
    BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in,
                    Charset.forName(System.getProperty("native.encoding"))));
    
    /* キーボードから入力した文字列を返す */
    public String readKey() {
        try {
            buf = br.readLine();
        } catch (IOException e ) {
            System.out.println(e);
            System.exit(1);
        }
        return buf;
    }

    /* msgで指定された文字列をプロンプトとして表示する */
    public String readKey(String msg) {
        System.out.print(msg + ">");
        return readKey();
    }

    /* キーボードで入力した整数を返す */
    public int readInt() {
        try {
            i = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        } catch (NumberFormatException e){
            System.out.println("入力した値に不正があります:"+ e);
            System.exit(1);
        }
        return i;
    }

    public int readInt(String msg) {
        System.out.print(msg + ">");
        return readInt();
    }
}