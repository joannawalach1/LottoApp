package pl.lotto;

import com.javaready.LottoApp.domain.LottoAppApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = LottoAppApplication.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc

public class BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:latest").withExposedPorts(27017);

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongoDbContainer.start();
        registry.add("spring.data.mongodb.host", mongoDbContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDbContainer::getFirstMappedPort);
    }

}
