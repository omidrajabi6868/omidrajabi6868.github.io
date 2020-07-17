package project.assistant;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import project.assistant.Services.Service.StorageService;
import project.assistant.Services.Utility.StorageProperties;


@EnableJpaRepositories("project.assistant.Repositories")
@EntityScan("project.assistant.Model.Beans")
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@Import(value = { WebMvcConfig.class })
public class AssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistantApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx , StorageService storageService) {
        return args -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
