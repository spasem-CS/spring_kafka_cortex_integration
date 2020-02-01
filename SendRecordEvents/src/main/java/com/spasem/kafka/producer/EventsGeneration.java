package com.spasem.kafka.producer;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import java.io.FileWriter;
import java.io.IOException;

public class EventsGeneration {
    public static void main(String[] args) throws IOException {
        Faker faker = new Faker();
        FileWriter fw = new FileWriter("events.log");
        for(int i=0; i<10000; i++ ){
            fw.write(new User( faker.name().firstName(), faker.name().lastName(), faker.address().city(), faker.job().title() ).toString());
            fw.write( "\n" );
        }
        fw.close();
    }

    static class User{
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String occupation;

        public User(String firstName, String lastName, String address, String occupation) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = firstName + "."+lastName+"@yoomail.com";
            this.address = address;
            this.occupation = occupation;
        }

        @Override
        public String toString() {
            return
                    "firstName=" + firstName  +
                    ", lastName=" + lastName +
                    ", email=" + email +
                    ", address=" + address +
                    ", occupation=" + occupation;
        }
    }
}
