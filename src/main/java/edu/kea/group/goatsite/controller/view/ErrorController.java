package edu.kea.group.goatsite.controller.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    // Error handling
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(@org.jetbrains.annotations.NotNull HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            /*
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error-501";
            }
            else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                return "error-405";
            }
            */
        }
        return "error";
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }
}
