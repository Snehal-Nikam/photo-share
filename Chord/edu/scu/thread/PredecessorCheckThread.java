package edu.scu.thread;

import edu.scu.core.Node;
import edu.scu.util.Util;

import java.net.InetSocketAddress;

public class PredecessorCheckThread extends Thread {

    private final Node local;
    private boolean alive;

    public PredecessorCheckThread(Node local) {
        this.local = local;
        alive = true;
    }

    @Override
    public void run() {
        while (alive) {
            InetSocketAddress predecessor = local.getPredecessor();
            if (predecessor != null) {
                String response = Util.sendRequest(predecessor, "KEEP");
                if (response == null || !response.equals("ALIVE")) {
                    local.clearPredecessor();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void toDie() {
        alive = false;
    }
}
