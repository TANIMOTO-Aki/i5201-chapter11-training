import java.io.*;

public class FileIn {
    BufferedReader br = null;       //BufferedReaderクラス

    /* ファイルのオープンを行うメソッド */
    public boolean open(String filename) {
        boolean sts = true;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            System.out.println("ファイル名に誤りがあります\n" + e);
            sts = false;
        }
        return sts;
    }

    /* ファイルからデータの読み込みを行うメソッド */
    public String readLine() {
        String buff;
        try {
            buff = br.readLine();
        } catch (IOException e) {
            System.out.println("読み込みエラー\n" + e);
            buff = null;
        }
        return buff;
    }

    /* ファイルのクローズを行うメソッド */
    public boolean close() {
        boolean sts = true;
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("ファイルクローズエラー\n" + e);
            sts = false;
        }
        return sts;
    }
}
