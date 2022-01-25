/*import com.urjc.es.helseVITA.Entities.Admin;
import com.urjc.es.helseVITA.HelseVitaApplication;
import com.urjc.es.helseVITA.Repositories.AdminRepository;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import com.urjc.es.helseVITA.Services.PatientService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
*/


/*
public class Tests {


    @Autowired
            AdminRepository adminRepository;



    public void TestCreateUSer() {
        @Bean
        adminRepository.saveAndFlush(new Admin("Nico", new BCryptPasswordEncoder().encode("ponnosun10")));

    }

    public void TestDeleteUser() {

    }
    //hacer un test en el que mandemos una peticion de un usuario que no existe y luego comrpbamos si da error 404
    public void TestAssignedDoctor() {
        //Crear un usuario y comprobar que efectivamnete el doctopr asignado es clara por defecto, como hemos puesto en el codigo
    }


}*/


import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;


@SpringBootConfiguration
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)


public class TestRepository {
    @Autowired
    PatientRepository patientRepository;
    @Test
    public void savePatientCheck() {
        Patient patient = new Patient("davide", "Davide1234", "davide@urjc.es",
                "1234567E", "Davide", "Apellido1", "Apellido2", 30);
        patientRepository.save(patient);
        Optional<Patient> davide = patientRepository.findByUsername("davide");
        if (davide.isPresent()){
            System.out.println("El usuario Davide se ha creado correctamente");
        } else {
            System.out.println("Lloremos");
        }
    }
}
