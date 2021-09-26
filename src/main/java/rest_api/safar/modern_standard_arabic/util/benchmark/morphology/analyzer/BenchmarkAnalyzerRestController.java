package rest_api.safar.modern_standard_arabic.util.benchmark.morphology.analyzer;

import common.constants.Analyzer;
import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.benchmark.morphology.analyzer.MorphologyAnalyzerBenchmark;
import safar.modern_standard_arabic.util.benchmark.morphology.analyzer.MorphologyAnalyzerMetrics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class BenchmarkAnalyzerRestController {
    public static String[] corpus = {"NAFIS", "ONTONOTES", "SAWALHA"};

    Map<String, String> analyzerImpl = new HashMap<String, String>();


    @PostMapping("${safar.modern_standard_arabic.util.benchmark.analyzer}")
    public List<MorphologyAnalyzerMetrics> benchmark_analyzer(@RequestBody BenchmarkAnalyzerRequestBody req,
                                                              HttpServletRequest request,
                                                              HttpServletResponse response) throws IOException {
        /*
         *  corpus in corpus && stemmer in stemmers
         *  if copus is null use NAFIS by default
         *  if stemmer or stemmers not in  stemmerImpl
         */
        List<MorphologyAnalyzerMetrics> result = new ArrayList<MorphologyAnalyzerMetrics>();

        try {
            if ((req.getAnalyzer() != null && req.getAnalyzers() != null)
                    || (req.getAnalyzer() == null && req.getAnalyzers() == null))
                throw new Exception("you should to specify just one analyzer type: analyzers:List or analyzer:String,"
                        + "analyzer or analyzers values should be is [ALKHALIL, ALKHALIL2, BAMA, MADAMIRA] !!");


            if ((req.getCorpus()).equals(null) || (req.getCorpus()).equals("")) req.setCorpus("NAFIS");

            if (!ArrayUtils.contains(corpus, req.getCorpus()))
                throw new Exception("Corpus should be in [NAFIS , SAWALHA , ONTONOTES] ,"
                        + " NAFIS is chosen by default if you don't specify the Corpus param !!!");


            if (req.getAnalyzer() != null && req.getAnalyzers() == null) {
                if (!analyzerImpl.containsKey(req.getAnalyzer()))
                    throw new Exception("analyzer value should be in [ALKHALIL, ALKHALIL2, BAMA, MADAMIRA] !!!");
                else {
                    result.add(MorphologyAnalyzerBenchmark.compare(analyzerImpl.get(req.getAnalyzer()), req.getCorpus()));
                }
            } else if (req.getAnalyzer() == null && req.getAnalyzers() != null) {
                if (req.getAnalyzers().length == 0)
                    throw new Exception("you should to specify analyzers in your analyzer's array !!!"
                            + "value should be in [ALKHALIL, ALKHALIL2, BAMA, MADAMIRA] !!!");
                else {

                    for (int i = 0; i < req.getAnalyzers().length; i++) {
                        if (!analyzerImpl.containsKey(req.getAnalyzers()[i]))
                            throw new Exception("analyzer's values should "
                                    + "be in [ALKHALIL, ALKHALIL2, BAMA, MADAMIRA] !!!");
                        else {
                            req.getAnalyzers()[i] = analyzerImpl.get(req.getAnalyzers()[i]);
                        }
                    }
                    result = MorphologyAnalyzerBenchmark.compare(req.getAnalyzerList(), req.getCorpus());
                }
            }
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @Bean
    public Map<String, String> getAnalyzerImpl() {
        analyzerImpl.put("ALKHALIL", Analyzer.ALKHALIL);
        analyzerImpl.put("ALKHALIL2", Analyzer.ALKHALIL2);
        analyzerImpl.put("BAMA", Analyzer.BAMA);
        analyzerImpl.put("MADAMIRA", Analyzer.MADAMIRA);
        return analyzerImpl;
    }

}
