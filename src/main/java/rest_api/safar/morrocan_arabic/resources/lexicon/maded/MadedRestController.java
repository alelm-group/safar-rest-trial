package rest_api.safar.morrocan_arabic.resources.lexicon.maded;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import rest_api.safar.morrocan_arabic.resources.lexicon.maded.request_models.ByLemma;
import rest_api.safar.morrocan_arabic.resources.lexicon.maded.request_models.ByPos;
import rest_api.safar.morrocan_arabic.resources.lexicon.maded.request_models.ByRoot;
import safar.moroccan_arabic.resources.lexicon.maded.factory.CorpusLidFactory;
import safar.moroccan_arabic.resources.lexicon.maded.interfaces.ILidCorpusService;
import safar.moroccan_arabic.resources.lexicon.maded.model.LexicalEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/morrocan-arabic/lexicon")
public class MadedRestController {

    public static ILidCorpusService madedImpl;

    @RequestMapping(method = RequestMethod.POST, path = "/maded", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllLexicalEntries(@RequestBody PaginationRequestBody req, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return req.getPaginationData(madedImpl.getAllLexicalEntries());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/maded/by-pos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map getLexicalEntriesByPOS(@RequestBody ByPos req, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            if (req.getPos() == null || req.getPos() == "") throw new Exception("pos is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that pos is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return req.getPagination().getPaginationData(madedImpl.getLexicalEntriesByPOS(req.getPos()));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/maded/by-root", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<LexicalEntry> getLexicalEntriesByRoot(@RequestBody ByRoot req, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            if (req.getRoot() == null || req.getRoot() == "") throw new Exception("root is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that root is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return madedImpl.getLexicalEntriesByRoot(req.getRoot());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/maded/by-lemma", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<LexicalEntry> getLexicalEntriesByUnvoweledLemma(@RequestBody ByLemma req, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            if (req.getLemma() == null || req.getLemma() == "") throw new Exception("lemma is empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that lemma is not empty!!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return madedImpl.getLexicalEntriesByUnvoweledLemma(req.getLemma());
    }
	/*
	@RequestMapping(method = RequestMethod.POST, path = "/morrocan_arabic/lexicon/maded/isLemma", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map isLemma(HttpServletRequest request,HttpServletResponse response) throws IOException {
	  boolean isLemma= madedImpl.isLemma("");
	  Map result=new HashMap();
	  result.put("result",isLemma);
	  return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/morrocan_arabic/lexicon/maded/isNoun", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map isNoun(HttpServletRequest request,HttpServletResponse response) throws IOException {
	  boolean isLemma= madedImpl.isNoun("");
	  Map result=new HashMap();
	  result.put("result",isLemma);
	  return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/morrocan_arabic/lexicon/maded/isNoun", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map isNounRoot(HttpServletRequest request,HttpServletResponse response) throws IOException {
	  boolean isNounRoot= madedImpl.isNounRoot("");
	  Map result=new HashMap();
	  result.put("result",isNounRoot);
	  return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/morrocan_arabic/lexicon/maded/isNoun", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map isNounRoot(HttpServletRequest request,HttpServletResponse response) throws IOException {
	  boolean isNounRoot= madedImpl.isNounRoot("");
	  Map result=new HashMap();
	  result.put("result",isNounRoot);
	  return result;
	}
	
	*/

    @Bean
    public ILidCorpusService getMadedImpl() {
        madedImpl = CorpusLidFactory.getMADEDImplementation();
        return madedImpl;
    }


}
