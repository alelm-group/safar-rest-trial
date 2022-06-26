package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_particles;


import org.springframework.context.annotation.Bean;
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

    public  INativeParticleService nativeParticleService;

    @Bean
    public INativeParticleService getNativeParticleService() {
        this.nativeParticleService = NativeParticleFactory.getParticleImplementation();
        return this.nativeParticleService;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_particles.simple_particles.get_all}")
    public Map getSimpleParticles(@RequestBody PaginationRequestBody req) {

        return req.getPaginationData(nativeParticleService.getSimpleParticles());
    }

}

