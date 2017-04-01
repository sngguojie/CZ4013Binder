package com.cz4013.binder;

import java.io.IOException;

/**
 * Instantiate
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int port;

        // set port number via user input or default number
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        } else {
            port = 2219;
        }

        ObjectReferenceTable ort = new ObjectReferenceTable();
        CommunicationModule cm = new CommunicationModule(port);
        cm.setObjectReferenceTable(ort);
        cm.start();


    }
}
