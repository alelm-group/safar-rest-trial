package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByEnglishDescRequestBody {
    private String englishDesc;

    @Override
    public String toString() {
        return "GetLetterByEnglishDescRequestBody [englishDesc=" + englishDesc + "]";
    }

    public String EnglishDesc() {
        return englishDesc;
    }

    public String getEnglishDesc() {
        return englishDesc;
    }

    public void setEnglishDesc(String englishDesc) {
        this.englishDesc = englishDesc;
    }

}
