package rest_api.safar.modern_standard_arabic.util.stopwords;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import safar.modern_standard_arabic.application.stopwords_analyzer.factory.SWAnalyserFactory;
import safar.modern_standard_arabic.application.stopwords_analyzer.interfaces.ISWAnalyserService;
import safar.modern_standard_arabic.util.stop_words.factory.StopWordFactory;
import safar.modern_standard_arabic.util.stop_words.impl.DomainIndependentSWsService;
import safar.modern_standard_arabic.util.stop_words.interfaces.ISWsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class StopwordsController {
    private static ISWAnalyserService SWA;
    private ISWsService sWFactory;

    @PostMapping("${safar.modern_standard_arabic.util.stop_words.remove}")
    public Map<String, String> removeStopWords(@RequestBody StopwordsRequestBody req,
                                                HttpServletResponse response) throws IOException {
        String sWLst = "";

        try {
            if (req.getText().isEmpty()) throw new Exception("Text is Empty !!");
            sWLst = sWFactory.removeDomainIndependantStopWordsFromString(req.getText());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("textWithoutSW", sWLst);
        return map;
    }
    @PostMapping("${safar.modern_standard_arabic.util.stop_words.by_text}")
    public Map<String, List<String>> getStopWords(@RequestBody StopwordsRequestBody req,
                                                   HttpServletResponse response) throws IOException {

        List<String> sWLst = new ArrayList<String>();
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is Empty !!");
            sWLst = sWFactory.getDomainIndependantStopWordsFromString(req.getText());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("DomainIndependantSw", sWLst);
        return map;
    }
    @PostMapping("${safar.modern_standard_arabic.util.stop_words.is_stop_word}")
    public Map isStopword(@RequestBody StopwordsRequestBody req,
                           HttpServletResponse response) throws IOException {


        try {
            if (req.getText().isEmpty()) throw new Exception("Text is Empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        Map map = new HashMap();
        boolean isSW = sWFactory.isStopWord(req.getText());

        map.put("Word", req.getText());
        map.put("isStopWord", isSW);
        return map;
    }
    @PostMapping("${safar.modern_standard_arabic.util.stop_words.get_all}")
    public Map getAllStopwords(@RequestBody PaginationRequestBody req
                                 )  {

        DomainIndependentSWsService service = new DomainIndependentSWsService();

        return req.getPaginationData(service.getDomainIndependentSWs());
    }

    @PostMapping("${safar.modern_standard_arabic.util.stop_words.analyze}")
    public Map<String, String> analyseStopWords(@RequestBody StopwordsRequestBody req,
                                          HttpServletResponse response) throws IOException {

        try {
            if (req.getText().isEmpty()) throw new Exception("Text is Empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        Map<String, String> results = SWA.analyze(req.getText());

        return results;
    }

    @Bean
    public ISWsService getsWFactory() {
        sWFactory = StopWordFactory.getSWsImplementation();
        return sWFactory;
    }

    @Bean
    public ISWAnalyserService getSWA() {
        SWA = SWAnalyserFactory.getSWAnalyserImplementation();
        return SWA;
    }
}
