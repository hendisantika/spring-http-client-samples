package id.my.hendisantika.reactivecaller.webclient;

import id.my.hendisantika.reactivecaller.Echo;
import id.my.hendisantika.reactivecaller.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-http-client-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 05.09
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/wc")
public class WebClientController {

    private final WebClient webClient;
    private final EchoService echoService;

    public WebClientController(WebClient webClient, EchoService echoService) {
        this.webClient = webClient;
        this.echoService = echoService;
    }

    @GetMapping("/echo/{message}")
    public Mono<Echo> echo(@PathVariable String message) {
        return webClient
                .get()
                .uri("/echo/" + message)
                .retrieve()
                .bodyToMono(Echo.class);
    }
}
