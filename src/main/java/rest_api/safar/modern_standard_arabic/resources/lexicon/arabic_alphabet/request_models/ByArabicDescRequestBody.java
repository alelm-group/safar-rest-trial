package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByArabicDescRequestBody {
    private String arabicDesc;

    @Override
    public String toString() {
        return "getLetterByArabicDescRequestBody [arabicDesc=" + arabicDesc + "]";
    }

    public String getArabicDesc() {
        return arabicDesc;
    }

    public void setArabicDesc(String arabicDesc) {
        this.arabicDesc = arabicDesc;
    }

}
