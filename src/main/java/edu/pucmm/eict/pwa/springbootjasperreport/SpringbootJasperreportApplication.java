package edu.pucmm.eict.pwa.springbootjasperreport;

import edu.pucmm.eict.pwa.springbootjasperreport.entidades.Estudiante;
import edu.pucmm.eict.pwa.springbootjasperreport.repositorios.EstudianteRepositorio;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
public class SpringbootJasperreportApplication implements ApplicationRunner {

    private EstudianteRepositorio estudianteRepositorio;
    @Value("${app.cantidad-estudiantes}")
    private int cantidadEstudiante;
    private static Logger LOG = LoggerFactory.getLogger(SpringbootJasperreportApplication.class);

    public SpringbootJasperreportApplication(EstudianteRepositorio estudianteRepositorio) {
        this.estudianteRepositorio = estudianteRepositorio;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJasperreportApplication.class, args);
    }

    @Controller
    @RequestMapping(path = "/")
    public static class IndexController{

        @RequestMapping(path = "/")
        public RedirectView index(){
            return new RedirectView("/mvc/");
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("Cargando la información de los estudiantes: "+cantidadEstudiante);
        Faker faker = new Faker();
        for (int i = 0; i < cantidadEstudiante; i++) {
            estudianteRepositorio.save(new Estudiante(faker.number().randomNumber(8, true), faker.name().firstName(), faker.name().lastName()));
        }

    }
}
