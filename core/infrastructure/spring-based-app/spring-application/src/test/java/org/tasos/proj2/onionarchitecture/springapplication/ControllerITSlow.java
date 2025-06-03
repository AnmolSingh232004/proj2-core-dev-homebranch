package org.tasos.proj2.onionarchitecture.springapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
        "server.port=8083"
        })
@ActiveProfiles("test")
//@Sql({"/integration-test/scripts/createSchufaTables.sql"})
//@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="/integration-test/scripts/populateSchufaTables.sql")
public class ControllerITSlow {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetActivityId1_UpperChestS() throws Exception {
        System.out.println("ControllerITSlow testGetActivityId1_UpperChestS starts...");
//        RestTemplate resTmpl = new RestTemplate();
//        ClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        resTmpl = new RestTemplate(httpRequestFactory);
        ResponseEntity<Object> actual =
                testRestTemplate.getForEntity("http://localhost:8083/home/api/proj2/activities/1", Object.class);

        Object object = actual.getBody();

//        List retList = Arrays.stream(objects)
//                .map(object -> mapper.convertValue(object, ActivityResponse.class))
//                .map(ActivityResponse::getTitle)
//                .collect(Collectors.toList());


        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);

        ActivityResponse actResp = mapper.convertValue(object, ActivityResponse.class);

        assertThat(actResp.getTitle()).isEqualTo("UpperChest_S");
    }

}