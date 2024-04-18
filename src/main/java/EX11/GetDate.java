import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * このクラスは現在の日付を取得し、返すことができます。
 */
public class GetDate {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    /**
     * getFormattedCurrentDateメソッド
     * @return 現在の日付を特定のフォーマット('yyyy.MM.dd')に変換し、文字列で返します。
     */
    public static String getFormattedCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(DATE_FORMATTER);
    }

}
