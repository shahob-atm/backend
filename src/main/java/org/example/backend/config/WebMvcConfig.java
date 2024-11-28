package org.example.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import java.util.List;

import static java.util.Objects.nonNull;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }

    /**
     * Configure static resource handlers and serve the SPA's index.html for unmatched routes.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        serveStaticResources(registry, "/", "classpath:/static/");
    }

    /**
     * Helper method to configure resource handlers for a specific endpoint and location.
     */
    private void serveStaticResources(ResourceHandlerRegistry registry, String endpoint, String location) {
        registry.addResourceHandler(formatEndpointPatterns(endpoint))
                .addResourceLocations(formatLocation(location))
                .resourceChain(true) // Enable resource chain for caching in production
                .addResolver(new SpaResourceResolver());
    }

    /**
     * Format endpoint patterns for consistent matching.
     */
    private String[] formatEndpointPatterns(String endpoint) {
        if (endpoint.endsWith("/")) {
            return new String[]{endpoint.substring(0, endpoint.length() - 1), endpoint, endpoint + ""};
        } else {
            return new String[]{endpoint, endpoint + "/", endpoint + "/"};
        }
    }

    /**
     * Ensure the location path ends with a forward slash.
     */
    private String formatLocation(String location) {
        return location.endsWith("/") ? location : location + "/";
    }

    /**
     * Custom resource resolver to fallback to index.html for SPA routing.
     */
    private static class SpaResourceResolver extends PathResourceResolver {
        @Override
        public Resource resolveResource(HttpServletRequest request, String requestPath,
                                        List<? extends Resource> locations, ResourceResolverChain chain) {
            Resource resource = super.resolveResource(request, requestPath, locations, chain);
            return nonNull(resource) ? resource : super.resolveResource(request, "/index.html", locations, chain);
        }
    }
}
