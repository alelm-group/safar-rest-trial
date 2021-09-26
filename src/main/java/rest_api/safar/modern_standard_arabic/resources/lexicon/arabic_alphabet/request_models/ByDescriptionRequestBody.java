package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByDescriptionRequestBody {
    private String desc;
    private int lang;

    @Override
    public String toString() {
        return "GetLetterByDescriptionRequestBody [desc=" + desc + ", lang=" + lang + "]";
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }


}
