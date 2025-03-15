package id.my.hendisantika.blockingcaller.restclient;

import id.my.hendisantika.blockingcaller.Echo;
import id.my.hendisantika.blockingcaller.EchoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-http-client-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 04.48
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/rc")
public class RestClientController {

    private final RestClient restClient;
    private final EchoService echoService;

    public RestClientController(RestClient restClient, @Qualifier("rc") EchoService echoService) {
        this.restClient = restClient;
        this.echoService = echoService;
    }

    @GetMapping("/echo/{message}")
    public Echo echo(@PathVariable String message) {
        return restClient
                .get()
                .uri("/echo/" + message)
                .retrieve()
                .body(Echo.class);
    }

    @GetMapping("/interface/echo/{message}")
    public Echo echoInterface(@PathVariable String message) {
        return echoService.echo(message);
    }
}
