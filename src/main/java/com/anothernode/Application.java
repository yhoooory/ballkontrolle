/**
 * Copyright (c) 2015 Moritz Reiter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.anothernode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * TODO
 */
@SpringBootApplication
public class Application {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner loadData(PlayerRepository repository) {
        return args -> {
            // save a couple of customers
            repository.save(new Player("Ludwig", "Wittgenstein"));
            repository.save(new Player("Hannah", "Arendt"));
            repository.save(new Player("Ayn", "Rand"));
            repository.save(new Player("Friedrich", "Nietzsche"));
            repository.save(new Player("Jean-Paul", "Sartre"));

            // fetch all customers
            LOGGER.info("Players found with findAll():");
            LOGGER.info("-------------------------------");
            for (Player player : repository.findAll()) {
                LOGGER.info(player.toString());
            }
            LOGGER.info("");

            // fetch an individual customer by ID
            Player player = repository.findOne(1L);
            LOGGER.info("Customer found with findOne(1L):");
            LOGGER.info("--------------------------------");
            LOGGER.info(player.toString());
            LOGGER.info("");

            // fetch customers by last name
            LOGGER.info("Customer found with findByLastNameStartsWithIgnoreCase('Nietzsche'):");
            LOGGER.info("--------------------------------------------");
            for (Player nietzsche : repository
                    .findByLastNameStartsWithIgnoreCase("Nietzsche")) {
                LOGGER.info(nietzsche.toString());
            }
            LOGGER.info("");
        };
    }
}
