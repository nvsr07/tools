package com.bms.finnr.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bms.finnr.config.ConfigurationOverride;

/**
 * Sample class that listens for application events. Each component that needs to deal with application events
 * should implement the appropriate @EventListener method. 
 * For example, the Redis component should listen for changes in configuration properties and check them to see if 
 * the Redis connection info has changed, and then deal with disconnecting / reconnecting. 
 * 
 * @author razing
 *
 */
@Component
public class BaseEventListener {
    private final static Logger logger = LoggerFactory.getLogger( BaseEventListener.class );
    
    @EventListener
    public void handleApplicationConfigurationChanged(ApplicationConfigurationEvent ace) {
        logger.debug("application configuration changed: {}", ace.getOverrideList().toString());
        for (ConfigurationOverride co: ace.getOverrideList()) {
            logger.debug("#### {}", co);
        }
    }
}
