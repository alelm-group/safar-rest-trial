package rest_api.safar.modern_standard_arabic.resources.lexicon.dictionnary;

public class DictionnaryRequestBody {

    private String text;
    private String option;

    @Override
    public String toString() {
        return "DictionnaryRequestBody [text=" + text + ", option=" + option + "]";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
