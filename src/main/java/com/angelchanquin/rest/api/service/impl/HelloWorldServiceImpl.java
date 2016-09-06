package com.angelchanquin.rest.api.service.impl;

import com.angelchanquin.rest.api.service.HelloWorldService;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * --------------------------------------------------------------------------------------
 * HelloWorldServiceImpl
 * --------------------------------------------------------------------------------------
 * Sample service.
 * -­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-‐
 * Change History
 * --------------------------------------------------------------------------------------
 * Version | Date       | Developer       | Changes
 * 1.0     | 09/06/2016 | Angel Chanquin  | Initial Creation
 * --------------------------------------------------------------------------------------
 */
@Component(
        label = "Sample Hello World Service",
        description = "Sample Hello World Service",
        immediate = true,
        metatype = true
)
@Service(value = HelloWorldService.class)
@Properties({
    @Property(
            name = HelloWorldServiceImpl.GREETING_PROPERTY_NAME,
            label = "Greeting",
            description = "If you want to say anyting to the world",
            value = HelloWorldServiceImpl.GREETING_PROPERTY_DEFAULT_VALUE
    )
})
public class HelloWorldServiceImpl implements HelloWorldService {
    static final String GREETING_PROPERTY_NAME = "rest.api.helloworld.greeting";
    static final String GREETING_PROPERTY_DEFAULT_VALUE = "Hello World!";

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    private String greeting;

    @Activate
    protected void activate(final Map<String, String> config){
        this.greeting = PropertiesUtil.toString(config.get(GREETING_PROPERTY_NAME), GREETING_PROPERTY_DEFAULT_VALUE);
        LOGGER.info("{} activated.", HelloWorldService.class.getName());
    }

    @Deactivate
    protected void deactivate(){
        this.greeting = StringUtils.EMPTY;
        LOGGER.info("{} deactivated.", HelloWorldService.class.getName());
    }

    /**
     * {@inheritDoc}
     */
    public String getGreeting() {
        return this.greeting;
    }
}
