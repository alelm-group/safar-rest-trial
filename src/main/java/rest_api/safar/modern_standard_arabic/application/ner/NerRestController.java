package rest_api.safar.modern_standard_arabic.application.ner;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.ner.factory.NerFactory;
import safar.modern_standard_arabic.util.ner.interfaces.INer;
import safar.modern_standard_arabic.util.ner.model.NerAnalysis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class NerRestController {

    public static String[] implementations = {"FARASA", "POLYGLOT", "STANZA", "CAMELTOOLS"};
    private INer farasaNer;

    @PostMapping("${safar.modern_standard_arabic.application.named_entity_recegnition}")
    public List<NerAnalysis> tag(@RequestBody NerReqeuestBody req,
                                 HttpServletResponse response) throws IOException {
        List<NerAnalysis> nerAnalyses = new ArrayList<>();
        try {
            if (req.getText().isEmpty()) throw new Exception("text is empty !!");
            else if (req.getImplementation().isEmpty()
                    || !ArrayUtils.contains(implementations, req.getImplementation()))
                throw new Exception("IMPLEMENTATION is empty or not in [STANZA,POLYGLOT,STANZA,CAMELTOOLS]");
            switch (req.getImplementation()) {
                case "FARASA":
                    nerAnalyses = farasaNer.tag(req.getText());
                    break;
                case "STANZA":
                    break;
                case "CAMELTOOLS":
                    break;
                case "POLYGLOT":
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [STANZA,POLYGLOT,STANZA,CAMELTOOLS]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return nerAnalyses;
    }

    @Bean
    public INer getFarasaNer() {
        farasaNer = NerFactory.getFarasaNerImplementation();
        return farasaNer;
    }

}
