package id.my.hendisantika.echoservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-http-client-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 16/03/25
 * Time: 04.38
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class EchoController {
    @GetMapping("/echo/{message}")
    public Echo echo(@PathVariable String message) {
        return new Echo("Echo:" + message);
    }
}
