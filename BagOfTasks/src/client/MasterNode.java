package client;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaces.*;
import objects.Task;


public class MasterNode {

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "src/client/client.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String TaskID = "T";
        int TaskNum = 0;

        Task[] ImgBoT = new Task[10];
        for (int i = 0; i < ImgBoT.length; i++){
            Task t = new Task(TaskID+TaskNum, "ImageProcessing", 5*(i+1));
            ImgBoT[i] = t;
            TaskNum++;
        }
        Task[] DataBoT = new Task[20];
        for (int i = 0; i < DataBoT.length; i++){
            Task t = new Task(TaskID+TaskNum, "DataMining", 5*(i+1));
            DataBoT[i] = t;
            TaskNum++;
        }
        Task[] BioBoT = new Task[15];
        for (int i = 0; i < BioBoT.length; i++){
            Task t = new Task(TaskID+TaskNum, "Bioinformatics", 5*(i+1));
            BioBoT[i] = t;
            TaskNum++;
        }

        try {
            //String serverAddress = "?.?.?.?";
            String serverAddress = "localhost";
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address args[0]

            Hilo ImgThread = new Hilo(ImgBoT, registry);
            ImgThread.start();
            Hilo DataThread = new Hilo(DataBoT, registry);
            DataThread.start();
            Hilo BioThread = new Hilo(BioBoT, registry);
            BioThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * THREAD CLASS
     */
    private static class Hilo extends Thread implements Serializable {
        Task[] BoT;
        Registry registry;

        public Hilo(Task[] BoT,Registry registry) {
            this.BoT = BoT;
            this.registry = registry;
        }

        @Override
        public void run() {
            String RequirementID = BoT[0].getRequirementID();
            switch (RequirementID) {
                case "DataMining":
                    try {
                        DataMining data = (DataMining) registry.lookup(RequirementID);
                        for (Task task : BoT) {
                            if (data.executeDataTask(task)) System.out.println(task);
                            else System.out.println("Task failed");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } break;
                case "ImageProcessing":
                    try {
                        ImageProcessing img = (ImageProcessing) registry.lookup(RequirementID);
                        for (Task task : BoT) {
                            if (img.executeImgTask(task)) System.out.println(task);
                            else System.out.println("Task failed");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } break;
                case "Bioinformatics":
                    try {
                        Bioinformatics bio = (Bioinformatics) registry.lookup(RequirementID);
                        for (Task task : BoT) {
                            if (bio.executeBioTask(task)) System.out.println(task);
                            else System.out.println("Task failed");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } break;
            }
        }

    }
}
