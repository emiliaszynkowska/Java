package comp1206.sushi.server;

import comp1206.sushi.common.*;
import java.io.*;

public class DataPersistence {

    Server server;
    ObjectInputStream input;
    File file;

    public DataPersistence(Server server) {
        this.server = server;
        read();
        DataPersistenceWriter dataPersistenceWriter = new DataPersistenceWriter(server);
        Thread writer = new Thread(dataPersistenceWriter);
        writer.start();
    }

    public void read() {
        server.loadConfiguration("data.txt");
    }

}
