package rest_api.safar.modern_standard_arabic.util.benchmark.morphology.stemmer;

import common.constants.Stemmer;
import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.benchmark.morphology.stemmer.StemmerBenchmark;
import safar.modern_standard_arabic.util.benchmark.morphology.stemmer.StemmerMetrics;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class BenchmarkStemmerRestController {

    public static String[] corpus = {"NAFIS", "ONTONOTES", "SAWALHA"};

    Map<String, String> stemmerImpl = new HashMap<String, String>();

    @PostMapping("${safar.modern_standard_arabic.util.benchmark.stemmer.root_based}")
    public List<StemmerMetrics> benchmarkStemmerRootBased(@RequestBody BenchmarkStemmerRequestBody req,
                                                             HttpServletResponse response) throws IOException {
        /*
         *  corpus in corpus && stemmer in stemmers
         *  if copus is null use NAFIS by default
         *  if stemmer or stemmers not in  stemmerImpl
         *
         */
        List<StemmerMetrics> result = new ArrayList<StemmerMetrics>();

        try {
            if ((req.getStemmer() != null && req.getStemmers() != null)
                    || (req.getStemmer() == null && req.getStemmers() == null))
                throw new Exception("you should to specify just one stemmer type: List or String,"
                        + "stemmer or stemmers values should be is [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT ] !!");

            /*
             * if (req.getParam() == null || !ArrayUtils.contains(new String[]{"root",
             * "stem"}, req.getParam())) throw new
             * Exception("param is null or not in [stem , root]!!");
             *
             * if (!ArrayUtils.contains(params, req.getParam())) throw new
             * Exception("param should be in [stem, root, \"\" ] !!!");
             */

            if ((req.getCorpus()).equals(null) || (req.getCorpus()).equals("")) req.setCorpus("NAFIS");

            if (!ArrayUtils.contains(corpus, req.getCorpus()))
                throw new Exception("Corpus should be in [NAFIS , SAWALHA , ONTONOTES] ,"
                        + " NAFIS is chosen by default if you don't specify the Corpus param !!!");


            if (req.getStemmer() != null && req.getStemmers() == null) {
                if (!stemmerImpl.containsKey(req.getStemmer()))
                    throw new Exception("stemmer value should be in [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT] !!!");
                else {
                    result.add(StemmerBenchmark.compare(stemmerImpl.get(req.getStemmer()), req.getCorpus(), "root"));
                }
            } else if (req.getStemmer() == null && req.getStemmers() != null) {
                if (req.getStemmers().length == 0)
                    throw new Exception("you should to specify stemmers in your stemmer's array !!!"
                            + "value should be in [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT] !!!");
                else {

                    for (int i = 0; i < req.getStemmers().length; i++) {
                        if (!stemmerImpl.containsKey(req.getStemmers()[i]))
                            throw new Exception("stemmer's values should "
                                    + "be in [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT] !!!");
                        else {
                            req.getStemmers()[i] = stemmerImpl.get(req.getStemmers()[i]);
                        }
                    }
                    result = StemmerBenchmark.compare(req.getStemmersList(), req.getCorpus(), "root");
                }
            }
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }


    @PostMapping("${safar.modern_standard_arabic.util.benchmark.stemmer.stem_based}")
    public List<StemmerMetrics> benchmarkStemmerStemBased(@RequestBody BenchmarkStemmerRequestBody req,
                                                             HttpServletResponse response) throws IOException {
        /*
         *  corpus in corpus && stemmer in stemmers
         *  if copus is null use NAFIS by default
         *  if stemmer or stemmers not in  stemmerImpl
         *
         */
        List<StemmerMetrics> result = new ArrayList<StemmerMetrics>();

        try {
            if ((req.getStemmer() != null && req.getStemmers() != null)
                    || (req.getStemmer() == null && req.getStemmers() == null))
                throw new Exception("you should to specify just one stemmer type: List or String,"
                        + "stemmer or stemmers values should be is [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT ] !!");

            /*
             * if (req.getParam() == null || !ArrayUtils.contains(new String[]{"root",
             * "stem"}, req.getParam())) throw new
             * Exception("param is null or not in [stem , root]!!");
             *
             * if (!ArrayUtils.contains(params, req.getParam())) throw new
             * Exception("param should be in [stem, root, \"\" ] !!!");
             */

            if ((req.getCorpus()).equals(null) || (req.getCorpus()).equals("")) req.setCorpus("NAFIS");

            if (!ArrayUtils.contains(corpus, req.getCorpus()))
                throw new Exception("Corpus should be in [NAFIS , SAWALHA , ONTONOTES] ,"
                        + " NAFIS is chosen by default if you don't specify the Corpus param !!!");


            if (req.getStemmer() != null && req.getStemmers() == null) {
                if (!stemmerImpl.containsKey(req.getStemmer()))
                    throw new Exception("stemmer value should be in [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT] !!!");
                else {
                    result.add(StemmerBenchmark.compare(stemmerImpl.get(req.getStemmer()), req.getCorpus(), "stem"));
                }
            } else if (req.getStemmer() == null && req.getStemmers() != null) {
                if (req.getStemmers().length == 0)
                    throw new Exception("you should to specify stemmers in your stemmer's array !!!"
                            + "value should be in [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT] !!!");
                else {

                    for (int i = 0; i < req.getStemmers().length; i++) {
                        if (!stemmerImpl.containsKey(req.getStemmers()[i]))
                            throw new Exception("stemmer's values should "
                                    + "be in [MOTAZ , LIGHT10 , SAFAR ,ISRI, TASHAPHYNE, TASHAPHYNE_ROOT] !!!");
                        else {
                            req.getStemmers()[i] = stemmerImpl.get(req.getStemmers()[i]);
                        }
                    }
                    result = StemmerBenchmark.compare(req.getStemmersList(), req.getCorpus(), "stem");
                }
            }
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }


    @Bean
    public Map<String, String> getStemmerImpl() {
        stemmerImpl.put("ISRI", Stemmer.ISRI_STEMMER);
        stemmerImpl.put("KHOJA", Stemmer.KHOJA_STEMMER);
        stemmerImpl.put("LIGHT10", Stemmer.LIGHT10_STEMMER);
        stemmerImpl.put("MOTAZ", Stemmer.MOTAZ_STEMMER);
        stemmerImpl.put("SAFAR", Stemmer.SAFAR_STEMMER);
        stemmerImpl.put("TASHAPHYNE_ROOT", Stemmer.TASHAPHYNE_ROOT_STEMMER);
        stemmerImpl.put("TASHAPHYNE", Stemmer.TASHAPHYNE_STEMMER);

        return stemmerImpl;
    }
}
