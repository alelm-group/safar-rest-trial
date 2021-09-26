package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByWikiTranslationRequestBody {
    private String wiki;

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return "GetLetterByWikiTranslationRequestBody [wiki=" + wiki + "]";
    }


}
