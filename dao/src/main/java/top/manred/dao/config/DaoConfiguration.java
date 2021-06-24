package top.manred.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.manred.dao.core.ProxyRegister;

@Configuration
public class DaoConfiguration {
    @Bean
    public ProxyRegister proxyRegister() {
        return new ProxyRegister("top.manred.dao.data.dao");
    }
}
