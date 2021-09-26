package rest_api.safar.modern_standard_arabic.resources.lexicon.calem;

public class CalemRequestBody {


    private String text;
    private String option;

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

    @Override
    public String toString() {
        return "CalemRequestBody [text=" + text + ", option=" + option + "]";
    }

}
