package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_particles;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest_api.pagination.PaginationRequestBody;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.factory.NativeParticleFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_particles.interfaces.INativeParticleService;

import java.util.Map;

@RestController
@CrossOrigin
public class ArabicParticlesRestController {

    public final INativeParticleService nativeParticleService;

    public ArabicParticlesRestController() {

        nativeParticleService = NativeParticleFactory.getParticleImplementation();

    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.simple_particles.get_all}")
    public Map getSimpleParticles(@RequestBody PaginationRequestBody req) {

        return req.getPaginationData(nativeParticleService.getSimpleParticles());
    }

}

