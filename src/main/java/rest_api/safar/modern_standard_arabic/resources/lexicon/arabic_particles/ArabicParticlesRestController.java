package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_particles;


import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_clitics.request_models.ByWord;
import rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_particles.request_models.ByForm;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.factory.NativeParticleFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.factory.ParticleFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.factory.SpecialNounsFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.factory.SpecialVerbsFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.interfaces.INativeParticleService;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.interfaces.INounsParticleService;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.interfaces.IParticleService;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.interfaces.ISpecialVerbService;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.model.NounParticle;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.model.Particle;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.model.SpecialVerb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ArabicParticlesRestController {

    public static IParticleService particleService;
    public static INativeParticleService nativeParticleService;
    public static INounsParticleService nounParticleService;
    public static ISpecialVerbService specialVerbService;

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.get_all}")
    public Map getVowledParticles(@RequestBody PaginationRequestBody req)  {
        return req.getPaginationData(particleService.getVowledParticles());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.simple_particles.get_all}")
    public Map getSimpleParticles(@RequestBody PaginationRequestBody req) {

        return req.getPaginationData(nativeParticleService.getSimpleParticles());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.simple_particles.by_word}")
    public List<Particle> getSimpleParticles(@RequestBody ByWord req, HttpServletResponse response) throws Exception {

        List<Particle> result = null;
        try {
            if ((req.getWord()).equals(null) || (req.getWord()).equals("")) throw new Exception("word is empty !!");
            result = nativeParticleService.getSimpleParticlesByString(req.getWord());
            System.out.println(result.toString());
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.vowled_particles}")
    public NounParticle getVowledParticle(@RequestBody ByForm req, HttpServletResponse response) throws Exception {

        try {
            if (req.getForm() == null || req.getForm() == "") throw new Exception("word is empty !!");
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return nounParticleService.getVoweledParticleByUnvoweledForm(req.getForm());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.special_verbs.by_unvowled_form}")
    public SpecialVerb getSpecialVerb(@RequestBody ByForm req, HttpServletResponse response) throws Exception {
        try {
            if (req.getForm() == null || req.getForm() == "") throw new Exception("word is empty !!");
        } catch (Exception e) {
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return specialVerbService.getSpecialVerbByInfinitiveUnvoweledForm(req.getForm());
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.special_verbs.get_all}")
    public Map getSpecialVerb(@RequestBody PaginationRequestBody req) {

        return req.getPaginationData(specialVerbService.getSpecialVerbsList());
    }

    @Bean
    public IParticleService getParticleService() {
        particleService = ParticleFactory.getParticleImplementation();
        return particleService;
    }

    @Bean
    public INativeParticleService getNativeParticleService() {
        nativeParticleService = NativeParticleFactory.getParticleImplementation();
        return nativeParticleService;
    }

    @Bean
    public INounsParticleService getNounsParticleService() {
        nounParticleService = SpecialNounsFactory.getNounsParticleImplementation();
        return nounParticleService;
    }

    @Bean
    public ISpecialVerbService getSpecialVerbService() {
        specialVerbService = SpecialVerbsFactory.getSpecialVerbImplementation();
        return specialVerbService;
    }

}

