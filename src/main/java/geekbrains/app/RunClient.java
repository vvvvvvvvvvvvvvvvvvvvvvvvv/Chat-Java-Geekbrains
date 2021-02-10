package geekbrains.app;

import geekbrains.client.ClientGuiController;

/**
 * @author Abubakar Musanipov
 */
public class RunClient {
    public static void main(String[] args) {
        ClientGuiController clientGuiController = new ClientGuiController();
        clientGuiController.run(clientGuiController);
    }
}
