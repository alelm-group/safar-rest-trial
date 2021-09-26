package rest_api.safar.modern_standard_arabic.util.splitter;

public class SplitterRequestBody {

    private String text;
    private String delimiter;
    private String specialCases;


    public SplitterRequestBody() {
        // TODO Auto-generated constructor stub
    }


    public SplitterRequestBody(String text, String delimiter, String specialCases) {
        super();
        this.text = text;
        this.delimiter = delimiter;
        this.specialCases = specialCases;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getSpecialCases() {
        return specialCases;
    }

    public void setSpecialCases(String specialCases) {
        this.specialCases = specialCases;
    }

    @Override
    public String toString() {
        return "splitterRequestBody [text=" + text + ", delimiter=" + delimiter + ", specialCases=" + specialCases
                + "]";
    }


}
