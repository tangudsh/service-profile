package com.equinix.serviceprofile;

import com.equinix.serviceprofile.entity.Metro;
import com.equinix.serviceprofile.entity.ServiceProfile;
import com.equinix.serviceprofile.entity.Speed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceProfileApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    static final String SERVICE_PROFILE_PATH = "serviceprofile";
    static final String SPEED_PATH = "speed/";
    static final String METRO_PATH = "metro/";

    @Test
    public void testServiceProfile() throws Exception {

        String serviceProfileURI = "http://localhost:" + port + "/" + SERVICE_PROFILE_PATH;
        String speedURI = "http://localhost:" + port + "/" + SPEED_PATH;
        String metroURI = "http://localhost:" + port + "/" + METRO_PATH;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity requestEntity = new HttpEntity(DataFactory.getSpeedCreatePayload(), httpHeaders);
        List<Speed> speeds = this.restTemplate.exchange(speedURI, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Speed>>() {
        }).getBody();

        assertThat(speeds).first().hasNoNullFieldsOrProperties();

        HttpEntity<String> metroEntity = new HttpEntity<>(DataFactory.getMetroPayload(), httpHeaders);
        List<Metro> metros = this.restTemplate.exchange(metroURI, HttpMethod.POST, metroEntity, new ParameterizedTypeReference<List<Metro>>() {
        }).getBody();

        assertThat(metros).first().hasNoNullFieldsOrProperties();

        HttpEntity<String> serviceProfileEntity = new HttpEntity<>(DataFactory.getServiceProfilePayload(), httpHeaders);
        ServiceProfile profile = this.restTemplate.exchange(serviceProfileURI, HttpMethod.POST, serviceProfileEntity, ServiceProfile.class).getBody();

        assertThat(profile).hasNoNullFieldsOrProperties();

        //test delete
        assertThat(this.restTemplate.exchange(serviceProfileURI +"/"+ profile.getId(), HttpMethod.DELETE, null, Void.class).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}