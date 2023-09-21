package ru.ydubovitsky.yeapenglish.configs;

import com.thedeanda.lorem.LoremIpsum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoremUtils {

    @Bean
    LoremIpsum loremIpsum() {
        return new LoremIpsum();
    }

}
