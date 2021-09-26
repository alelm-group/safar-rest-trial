package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_clitics;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_clitics.request_models.ByWord;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.factory.CliticFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.interfaces.ICliticService;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.interfaces.IEncliticService;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.interfaces.IProcliticService;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.model.Clitic;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.model.EnC;
import safar.modern_standard_arabic.resources.lexicon.arabic_clitics.model.ProC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ArabicCliticsRestController {
    private static IProcliticService procliticImpl;
    private static ICliticService cliticImpl;
    private static IEncliticService encliticImpl;

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_clitics.clitics.get_all}")
    public Map getAllClitics(@RequestBody PaginationRequestBody req) {

        return req.getPaginationData(cliticImpl.getAllClitics());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_clitics.clitics.by_word}")
    public List<Clitic> getClitics(@RequestBody ByWord req, HttpServletResponse response) throws IOException {
        try {
            if ((req.getWord()).equals(null) || (req.getWord()).equals(""))
                throw new Exception("word value is empty!!");

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the word is not empty !!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        return cliticImpl.getClitics(req.getWord());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_clitics.proclitics.get_all}")
    public Map getAllProclitics(@RequestBody PaginationRequestBody req) {

        return req.getPaginationData(procliticImpl.getAllProclitics());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_clitics.proclitics.by_word}")
    public List<ProC> getProclitics(@RequestBody ByWord req, HttpServletResponse response) throws IOException {
        try {
            if (req.getWord() == null || req.getWord() == "") throw new Exception("word value is empty!!");

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the word is not empty !!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        return procliticImpl.getProclitics(req.getWord());
    }


    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_clitics.enclitics.get_all}")
    public Map getAllEnclitics(@RequestBody PaginationRequestBody req ) {


        return req.getPaginationData(encliticImpl.getAllEnclitics());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_clitics.enclitics.by_word}")
    public List<EnC> getEnclitics(@RequestBody ByWord req, HttpServletResponse response) throws IOException {
        try {
            if (req.getWord() == null || req.getWord() == "") throw new Exception("word value is empty!!");

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the word is not empty !!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        return encliticImpl.getEnclitics(req.getWord());
    }


    @Bean
    void setBeans(){
        procliticImpl = CliticFactory.getProcliticImplementation();
        cliticImpl = CliticFactory.getCliticImplementation();
        encliticImpl = CliticFactory.getEncliticImplementation();
    }
}

