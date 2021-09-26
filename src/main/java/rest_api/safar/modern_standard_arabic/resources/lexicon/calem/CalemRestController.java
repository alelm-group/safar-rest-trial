package rest_api.safar.modern_standard_arabic.resources.lexicon.calem;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.resources.lexicon.calem.impl.CalemServiceKhalidImpl;
import safar.modern_standard_arabic.resources.lexicon.calem.interfaces.ICalemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CalemRestController {
    public static String[] options = new String[]{"stem", "lemma"};
    private static ICalemService calemService;

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.calem}")
    public List<String> get_special_verb(@RequestBody CalemRequestBody req,HttpServletResponse response) throws Exception {
        List<String> result = new ArrayList();
        try {
            if (req.getText() == null) throw new Exception("text is empty !!");
            if (req.getOption() == null) req.setOption("stem");
            if (!ArrayUtils.contains(options, req.getOption()))
                throw new Exception("option accept two values [stem,lemma]");

            if (req.getOption().equals("lemma")) {
                if (calemService.isLemma(req.getText())) {
                    result = calemService.getLemmaStems(req.getText());
                }
            } else if (req.getOption().equals("stem")) {
                if (calemService.isStem(req.getText())) {
                    // todo
                    result = calemService.getStemLemmas(req.getText());
                }
            }
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @Bean
    public ICalemService getCalemService() {
        //calemService = CalemFactory.getStemLemmaImplementation();
        calemService = new CalemServiceKhalidImpl();
        return calemService;
    }

}
