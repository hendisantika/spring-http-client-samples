package id.my.hendisantika.blockingcaller.resttemplate;

import id.my.hendisantika.blockingcaller.EchoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-http-client-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 04.51
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/rt")
public class RestTemplateController {

    private final RestTemplate restTemplate;
    private final EchoService echoService;

    public RestTemplateController(RestTemplate restTemplate, @Qualifier("rt") EchoService echoService) {
        this.restTemplate = restTemplate;
        this.echoService = echoService;
    }
}
