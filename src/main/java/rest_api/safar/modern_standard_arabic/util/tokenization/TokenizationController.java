package rest_api.safar.modern_standard_arabic.util.tokenization;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.tokenization.impl.SAFARTokenizer;
import safar.modern_standard_arabic.util.tokenization.interfaces.ITokenizer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class TokenizationController {

    private static ITokenizer tokenizer;

    @PostMapping("${safar.modern_standard_arabic.util.tokenization}")
    public Map<Integer, String> tokenize(@RequestBody TokenizationRequestBody req,
                                         HttpServletResponse response) throws IOException {
        try {
            if (req.getText().isEmpty()) throw new Exception("Test is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty, if you want to get the set of tokens you should to set withUniqueTokens to True !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        Map<Integer, String> map = new HashMap<Integer, String>();
        String[] result;
        int n = 1;

        if (req.isWithUniqueTokens() == false) {
            result = tokenizer.tokenize(req.getText());
        } else {
            result = tokenizer.tokenize(req.getText(), true);
        }

        for (String i : result) {
            map.put(n++, i);
        }

        return map;

    }


    @Bean
    public ITokenizer getTokenizer() {
        tokenizer = new SAFARTokenizer();
        return tokenizer;
    }


}
