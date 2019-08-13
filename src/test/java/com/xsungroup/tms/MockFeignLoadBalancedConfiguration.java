package com.xsungroup.tms;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 16:35
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Component
@ComponentScan("com.xsungroup.tms")
@EnableFeignClients("com.xsungroup.tms")
public class MockFeignLoadBalancedConfiguration {


    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public MockMvc mockMvc(WebApplicationContext context) {
        return MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Bean
    public Client feignClient(MockMvc mockMvc, BeanFactory beanFactory) {
        return new MockLoadBalancerFeignClient( mockMvc,objectMapper);
    }

    public static class MockLoadBalancerFeignClient implements Client {

        private final MockMvc mockMvc;
        private ObjectMapper objectMapper;

        public MockLoadBalancerFeignClient(MockMvc mockMvc, ObjectMapper objectMapper) {

            this.mockMvc = mockMvc;
            this.objectMapper = objectMapper;
        }

        @Override
        public Response execute(Request request, Request.Options options) throws IOException {
            URI asUri = URI.create(request.url());


            try {
                MockHttpServletRequestBuilder mockHttpServletRequestBuilder;
                switch (request.httpMethod()) {
                    case DELETE:
                        mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete(asUri);
                        break;
                    case GET:
                        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(asUri);
                        break;
                    case PUT:
                        mockHttpServletRequestBuilder = MockMvcRequestBuilders.put(asUri);
                        break;
                    case POST:
                        mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(asUri);
                        break;
                    case HEAD:
                    case PATCH:
                    case TRACE:
                    case CONNECT:
                    case OPTIONS:
                    default:
                        throw new RuntimeException("");

                }

                Map<String, Collection<String>> headers1 = request.headers();
                for (Map.Entry<String, Collection<String>> stringCollectionEntry : headers1.entrySet()) {
                    mockHttpServletRequestBuilder.header(stringCollectionEntry.getKey(), stringCollectionEntry.getValue().toArray());
                }

                mockHttpServletRequestBuilder.content(request.requestBody().asBytes());

                ResultActions resultActions = mockMvc
                        .perform(mockHttpServletRequestBuilder);

                resultActions.andDo(print());

                Response.Builder builder = Response.builder();
                MockHttpServletResponse response = resultActions.andReturn().getResponse();
                builder.status(response.getStatus());
                Map<String, Collection<String>> headers = new HashMap<>();
                for (String headerName : response.getHeaderNames()) {
                    headers.put(headerName, response.getHeaders(headerName));
                }
                builder.headers(headers);
                builder.request(request);
                builder.body(response.getContentAsByteArray());

                ResponseInfo responseInfo = objectMapper.readValue(response.getContentAsString(), ResponseInfo.class);
                if (responseInfo.getCode() != BusCode.SUCCESS.getCode()) {
                    throw new BussException(responseInfo.getMessage());
                }

                return builder.build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
