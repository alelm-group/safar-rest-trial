package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByCodeRequestBody {

    private String code;
    private int encoding;


    @Override
    public String toString() {
        return "GetLetterByCodeRequestBody [code=" + code + ", encoding=" + encoding + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEncoding() {
        return encoding;
    }

    public void setEncoding(int encoding) {
        this.encoding = encoding;
    }


}
