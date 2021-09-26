package rest_api.safar.modern_standard_arabic.util.benchmark.morphology.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkAnalyzerRequestBody {
    private String[] analyzers;
    private String corpus;
    private String analyzer;

    public List<String> getAnalyzerList() {
        List<String> chars = new ArrayList<>();
        Collections.addAll(chars, analyzers);
        return chars;
    }

    public String[] getAnalyzers() {
        return analyzers;
    }

    public void setAnalyzers(String[] analyzers) {
        this.analyzers = analyzers;
    }

    public String getCorpus() {
        return corpus;
    }

    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }
}
