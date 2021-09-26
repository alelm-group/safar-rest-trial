package rest_api.safar.modern_standard_arabic.util.benchmark.morphology.stemmer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkStemmerRequestBody {

    private String[] stemmers;
    private String corpus;
    private String stemmer;
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public List<String> getStemmersList() {
        List<String> chars = new ArrayList<>();
        Collections.addAll(chars, stemmers);
        return chars;
    }

    public String getStemmer() {
        return stemmer;
    }

    public void setStemmer(String stemmer) {
        this.stemmer = stemmer;
    }

    public String[] getStemmers() {
        return stemmers;
    }

    public void setStemmers(String[] stemmers) {
        this.stemmers = stemmers;
    }

    public String getCorpus() {
        return corpus;
    }

    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }


}
