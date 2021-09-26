package rest_api.safar.modern_standard_arabic.resources.lexicon.dictionnary;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import safar.modern_standard_arabic.resources.lexicon.dictionnary.factory.DictFactory;
import safar.modern_standard_arabic.resources.lexicon.dictionnary.interfaces.IDictService;
import safar.modern_standard_arabic.resources.lexicon.dictionnary.model.LexicalEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DictionaryRestController {
    public static String[] options = new String[]{"entrance", "root"};
    private static IDictService alwassitDictService;
    private static IDictService moassirDictService;

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.dictionnary.alwasit.get_all}")
    public Map getEntriesAlwassit(@RequestBody PaginationRequestBody req) {
        return req.getPaginationData(alwassitDictService.getAllLexicalEntries());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.dictionnary.alwasit.by_text}")
    public List<LexicalEntry> getEntriesAlwassitByText(@RequestBody DictionnaryRequestBody req, HttpServletResponse response) throws Exception {
        List<LexicalEntry> result = new ArrayList<>();
        try {
            if (req.getText() == null) throw new Exception("text is empty !!");
            if (req.getOption() == null) req.setOption("root");
            if (!ArrayUtils.contains(options, req.getOption()))
                throw new Exception("no root no entrance !!");   /*throw new Exception("option accept two values [entrance,root]");*/

            if (req.getOption().equals("entrance")) {
                result = alwassitDictService.getLexicalEntriesByUnvoweledLemma(req.getText());
            } else if (req.getOption().equals("root")) {
                result = alwassitDictService.getLexicalEntriesByRoot(req.getText());
            }
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.dictionnary.moassir.get_all}")
    public Map getEntriesMoassir(@RequestBody PaginationRequestBody req) {
        return req.getPaginationData(moassirDictService.getAllLexicalEntries());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.dictionnary.moassir.by_text}")
    public List<LexicalEntry> getEntriesMoassirByText(@RequestBody DictionnaryRequestBody req, HttpServletResponse response) throws Exception {
        List<LexicalEntry> result = new ArrayList<>();
        try {
            if (req.getText() == null) throw new Exception("text is empty !!");
            if (req.getOption() == null) req.setOption("root");
            if (!ArrayUtils.contains(options, req.getOption()))
                throw new Exception("no root no entrance !!");   /*throw new Exception("option accept two values [entrance,root]");*/

            if (req.getOption().equals("entrance")) {
                result = alwassitDictService.getLexicalEntriesByUnvoweledLemma(req.getText());
            } else if (req.getOption().equals("root")) {
                result = alwassitDictService.getLexicalEntriesByRoot(req.getText());
            }
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @Bean
    public IDictService getAlwassit() {
        alwassitDictService = DictFactory.getWassitImplementation();
        return alwassitDictService;
    }

    @Bean
    public IDictService getMoassir() {
        moassirDictService = DictFactory.getMoassirImplementation();
        return moassirDictService;
    }

}
