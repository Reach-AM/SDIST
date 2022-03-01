package server;

import java.rmi.registry.LocateRegistry;

public class Deployer {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "src/server/server.policy");
            if(System.getSecurityManager() == null){
                System.setSecurityManager(new SecurityManager());
            }

            LocateRegistry.createRegistry(1099);

            //Declarar los 3 esclavos
            SlaveNode BioServer = new SlaveNode();
            SlaveNode DataServer = new SlaveNode();
            SlaveNode ImgServer = new SlaveNode();
            BioServer.start("Bioinformatics");
            DataServer.start("DataMining");
            ImgServer.start("ImageProcessing");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
