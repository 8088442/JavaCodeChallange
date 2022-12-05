package coding.challenge.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@Slf4j
public class CommonModuleConfiguration {
    @PostConstruct
    public void postConstruct(){
        log.info("====== 'COMMON' module loaded.");
    }
}
