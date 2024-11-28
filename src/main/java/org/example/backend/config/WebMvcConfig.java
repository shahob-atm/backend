package org.example.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configure CORS settings to allow cross-origin requests.
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }

    /**
     * Configure static resource handlers for specific routes.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Map<String, String> routes = Map.of(
                "/order", "classpath:/static/",
                "/home", "classpath:/static/",
                "/login", "classpath:/static/",
                "/register", "classpath:/static/"
        );

        // Dynamically configure resource handlers for each route
        routes.forEach((endpoint, location) -> {
            registry.addResourceHandler(formatEndpointPatterns(endpoint))
                    .addResourceLocations(formatLocation(location))
                    .resourceChain(true)
                    .addResolver(new SpaResourceResolver());
        });
    }

    /**
     * Format endpoint patterns for consistent matching.
     */
    private String[] formatEndpointPatterns(String endpoint) {
        return new String[]{
                endpoint.endsWith("/") ? endpoint.substring(0, endpoint.length() - 1) : endpoint,
                endpoint.endsWith("/") ? endpoint : endpoint + "/",
                endpoint + "/"
        };
    }

    /**
     * Ensure the location path ends with a forward slash.
     */
    private String formatLocation(String location) {
        return location.endsWith("/") ? location : location + "/";
    }

    /**
     * Custom resource resolver to fallback to index.html for unmatched routes (SPA support).
     */
    private static class SpaResourceResolver extends PathResourceResolver {
        @Override
        public Resource resolveResource(HttpServletRequest request, String requestPath,
                                        List<? extends Resource> locations, ResourceResolverChain chain) {
            Resource resource = super.resolveResource(request, requestPath, locations, chain);
            return nonNull(resource) ? resource : super.resolveResource(request, "/index.html", locations, chain);
        }
    }

    /**
     * Configure view controllers for unmatched routes to route to index.html.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{spring:[a-zA-Z0-9-_]+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/**/{spring:[a-zA-Z0-9-_]+}")
                .setViewName("forward:/index.html");
    }
}
