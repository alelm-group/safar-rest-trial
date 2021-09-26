package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByBwTranslitRequestBody {

    private String buckwalter;

    public ByBwTranslitRequestBody() {

    }

    public ByBwTranslitRequestBody(String buckwalter) {
        super();
        this.buckwalter = buckwalter;
    }

    public String getBuckwalter() {
        return buckwalter;
    }

    public void setBuckwalter(String buckwalter) {
        this.buckwalter = buckwalter;
    }


}
