package com.dinesh.apimocker.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public  class Constants
{
    @Value("${port.number}")
    public String PORT_NUMBER;

    @Value("${mapping.directory}")
    public String MAPPING_DIRECTORY;
}
