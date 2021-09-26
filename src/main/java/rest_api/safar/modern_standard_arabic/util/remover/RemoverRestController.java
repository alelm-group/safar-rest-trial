package rest_api.safar.modern_standard_arabic.util.remover;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Todo (check this out) this function is no longer exit in safar V3 ??

@RestController
@CrossOrigin
public class RemoverRestController {

    @PostMapping("${safar.modern_standard_arabic.util.normalization.remove_diacritics}")
    public Map<String, String> removeDiacritics(@RequestBody RemoverRequestBody req,
                                                HttpServletResponse response) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!!");
            //String result = Remover.removeDiacritics(req.getText());
            // map.put("textWithoutDiacritics", result);
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return map;
    }

}
