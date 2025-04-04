package id.my.hendisantika.blockingcaller.webclient;

import id.my.hendisantika.blockingcaller.Echo;
import id.my.hendisantika.blockingcaller.EchoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-http-client-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 04.53
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/wc")
public class WebClientController {

    private final WebClient webClient;
    private final EchoService echoService;

    public WebClientController(WebClient webClient, @Qualifier("wc") EchoService echoService) {
        this.webClient = webClient;
        this.echoService = echoService;
    }

    @GetMapping("/echo/{message}")
    public Echo echo(@PathVariable String message) {
        return webClient
                .get()
                .uri("/echo/" + message)
                .retrieve()
                .bodyToMono(Echo.class)
                .block();
    }

    @GetMapping("/interface/echo/{message}")
    public Echo echoInterface(@PathVariable String message) {
        return echoService.echo(message);
    }
}
