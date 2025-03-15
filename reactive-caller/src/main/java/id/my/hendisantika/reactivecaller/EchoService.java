package id.my.hendisantika.reactivecaller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-http-client-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 04.59
 * To change this template use File | Settings | File Templates.
 */
@HttpExchange(url = "/echo")
public interface EchoService {

    @GetExchange("/{message}")
    Mono<Echo> echo(@PathVariable String message);
}
