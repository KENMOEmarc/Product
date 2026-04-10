package ken.tar.Product.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerFactory {
    public Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
