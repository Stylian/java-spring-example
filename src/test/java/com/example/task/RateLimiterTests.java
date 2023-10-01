package com.example.task;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateLimiterTests {
//    @RegisterExtension
//    static WireMockExtension EXTERNAL_SERVICE = WireMockExtension.newInstance()
//            .options(WireMockConfiguration.wireMockConfig()
//                    .port(9090))
//            .build();
}
