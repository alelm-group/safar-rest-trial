package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByTranslitRequestBody {

    private String translit;
    private int type;

    @Override
    public String toString() {
        return "GetLetterByTranslitRequestBody [translit=" + translit + ", type=" + type + "]";
    }

    public String getTranslit() {
        return translit;
    }

    public void setTranslit(String translit) {
        this.translit = translit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
