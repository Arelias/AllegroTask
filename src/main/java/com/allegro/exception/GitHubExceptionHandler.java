package com.allegro.exception;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class GitHubExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubExceptionHandler.class);

    private class JsonResponse {
        String message;
        String endpointPath;

        public JsonResponse() {
        }

        public JsonResponse(String message) {
            this.message = message;
        }

        public JsonResponse(String message, String error) {
            super();
            this.message = message;
            this.endpointPath = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getendpointPath() {
            return endpointPath;
        }

        public void setError(String endpointPath) {
            this.endpointPath = endpointPath;
        }
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<JsonResponse> handleException(
            HttpServletRequest request,
            HttpServletResponse response, Exception e){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+e.getMessage());
        HttpStatus temp;
        switch (response.getStatus()){
            case 404:
                temp = HttpStatus.NOT_FOUND;
            default:
                temp = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(
                new JsonResponse(e.getMessage(), request.getRequestURI()), temp);
    }
}
