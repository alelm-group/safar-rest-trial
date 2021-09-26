package rest_api.safar.morrocan_arabic.resources.lexicon.moralex;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import rest_api.safar.morrocan_arabic.resources.lexicon.moralex.request_models.ByMorpheme;
import rest_api.safar.morrocan_arabic.resources.lexicon.moralex.request_models.ByPos;
import safar.moroccan_arabic.resources.lexicon.moralex.factory.Moralex;
import safar.moroccan_arabic.resources.lexicon.moralex.interfaces.IMoralexService;
import safar.moroccan_arabic.resources.lexicon.moralex.model.LexicalEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/morrocan-arabic/lexicon")

public class MoralexRestController {

    public static IMoralexService moralexImpl;

    @RequestMapping(method = RequestMethod.POST, path = "/moralex", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllLexicalEntries(@RequestBody PaginationRequestBody req, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return req.getPaginationData(moralexImpl.getAllLexicalEntries());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/moralex/by-morpheme", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<LexicalEntry> getAllLexicalEntriesByMorphme(@RequestBody ByMorpheme req, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            if (req.getMorpheme() == null || req.getMorpheme() == "") throw new Exception("morpheme is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that morpheme is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        return moralexImpl.getLexicalEntriesByMorphme(req.getMorpheme());

    }

    @RequestMapping(method = RequestMethod.POST, path = "/moralex/by-pos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<LexicalEntry> getLexicalEntriesByPOS(@RequestBody ByPos req, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            if (req.getPos() == null || req.getPos() == "") throw new Exception("pos is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that pos is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        return moralexImpl.getLexicalEntriesByPOS(req.getPos());
    }

    @Bean
    public IMoralexService getMoralexImpl() {
        moralexImpl = Moralex.getmoralexImplementation();
        return moralexImpl;
    }
}
