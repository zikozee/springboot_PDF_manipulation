package com.zikozee.pdf_manipulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PdfManipulationApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(PdfManipulationApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(PdfManipulationApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}
@Order(value=3)
@Component
class ApplicationStartupRunnerOne implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(ApplicationStartupRunnerOne.class);

    @Override
    public void run(String... args) throws Exception {
        LOG.info("ApplicationStartupRunnerOne run method Started !!");
    }
}

@Order(value=2)
@Component
class ApplicationStartupRunnerTwo implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(ApplicationStartupRunnerTwo.class);

    @Override
    public void run(String... args) throws Exception {
        LOG.info("ApplicationStartupRunnerTwo run method Started !!");
    }
}

//@SpringBootApplication
//public class PdfManipulationApplication{
//    public static void main(String[] args) {
//        SpringApplication.run(PdfManipulationApplication.class, args);
//    }
//}