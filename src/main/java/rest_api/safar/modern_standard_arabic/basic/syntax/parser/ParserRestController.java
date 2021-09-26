package rest_api.safar.modern_standard_arabic.basic.syntax.parser;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.basic.syntax.parser.factory.ParserFactory;
import safar.modern_standard_arabic.basic.syntax.parser.interfaces.IParser;
import safar.modern_standard_arabic.basic.syntax.parser.model.SentenceParsingAnalysis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin

public class ParserRestController {

    private static IParser farasaParser;
    private static IParser stanfordParser;
    private static final String[] implementations = {"STANFORD", "FARASA"};

    @PostMapping("${safar.modern_standard_arabic.basic.syntax.parser}")
    public SentenceParsingAnalysis safar_parser(@RequestBody ParserRequestBody req,
                                                HttpServletResponse response) throws IOException {

        SentenceParsingAnalysis lemmas = new SentenceParsingAnalysis();

        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");
            else if (req.getImplementation().isEmpty() ||
                    !ArrayUtils.contains(implementations, req.getImplementation())) {
                throw new Exception("IMPLEMENTATION is empty or not in [FARASA, STANFORD]");
            }


            switch (req.getImplementation()) {
                case "FARASA":
                    lemmas = farasaParser.parse(req.getText());
                    System.out.println(lemmas.getListOfParsinganalysis().size());

                    break;

                default:
                    lemmas = stanfordParser.parse(req.getText());
                    break;
            }

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [FARASA, STANFORD]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return lemmas;
    }

    @Bean
    public IParser getFarasa_parser() {
        farasaParser = ParserFactory.getFARASAParserImplementation();
        return farasaParser;
    }

    @Bean
    public IParser getStanfordParser() {
        stanfordParser = ParserFactory.getStanfordParserImplementation();
        return stanfordParser;
    }
}
