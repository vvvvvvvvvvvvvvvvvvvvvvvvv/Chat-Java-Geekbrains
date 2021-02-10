package geekbrains.app;

import geekbrains.server.ServerGuiController;

/**
 * @author Abubakar Musanipov
 */
public class RunServer {
    public static void main(String[] args) {
        ServerGuiController serverGuiController = new ServerGuiController();
        serverGuiController.run(serverGuiController);
    }
}
