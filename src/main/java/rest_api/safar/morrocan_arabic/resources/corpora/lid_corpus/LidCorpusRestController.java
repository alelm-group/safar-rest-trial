package rest_api.safar.morrocan_arabic.resources.corpora.lid_corpus;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import rest_api.safar.morrocan_arabic.resources.corpora.lid_corpus.request_models.ByLang;
import rest_api.safar.morrocan_arabic.resources.corpora.lid_corpus.request_models.ByText;
import safar.moroccan_arabic.resources.corpora.lid_corpus.factory.CorpusLidFactory;
import safar.moroccan_arabic.resources.corpora.lid_corpus.interfaces.ICorpusLidService;
import safar.moroccan_arabic.resources.corpora.lid_corpus.model.LexicalEntry;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping(value = "/morrocan-arabic/corpora")
public class LidCorpusRestController {
    public static ICorpusLidService corpusLidImpl;

    @RequestMapping(method = RequestMethod.POST, path = "/lid-corpus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllLexicalEntries(@RequestBody PaginationRequestBody req) throws IOException {
        return req.getPaginationData(corpusLidImpl.getAllLexicalEntries());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/lid-corpus/by-text", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<LexicalEntry> getAllLexicalEntriesByText(@RequestBody ByText req, HttpServletResponse response) throws IOException {

        try {
            if (req.getText() == null || req.getText() == "") throw new Exception("text is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that text is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return corpusLidImpl.getLexicalEntriesByText(req.getText());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/lid-corpus/by-lang", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllLexicalEntriesByLang(@RequestBody ByLang req, HttpServletResponse response) throws IOException {

        try {
            if (req.getLang() == null || req.getLang() == "") throw new Exception("lang is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that lang is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return req.getPagination().getPaginationData(corpusLidImpl.getLexicalEntriesByLang(req.getLang()));
    }

    @Bean
    public ICorpusLidService getCorpusLidService() {
        corpusLidImpl = CorpusLidFactory.getCorpsuImplementation();
        return corpusLidImpl;
    }
}
