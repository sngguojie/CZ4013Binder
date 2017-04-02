package com.cz4013.binder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by danielseetoh on 4/1/17.
 */
public class CommunicationModule extends Thread{

    protected boolean isRunning = true;
    protected DatagramSocket socket = null;
    protected InetAddress serverAddress;
    protected int port;
    private final int MAX_BYTE_SIZE = 1024;
    private ObjectReferenceTable objRefTable;

    /**
     * Constructor that creates a socket with the given port
     * @param binderPort
     * @throws IOException
     */
    public CommunicationModule(int binderPort) throws IOException {
        super("CommunicationModule");
        socket = new DatagramSocket(new InetSocketAddress(binderPort));
        String[] localHostString = InetAddress.getLocalHost().toString().split("/");
        System.out.println(localHostString[localHostString.length - 1]);
        this.port = binderPort;
    }

    /**
     * Continuously wait for any packet to arrive
     */
    public void waitForPacket () {
        while (this.isRunning) {
            try {
                byte[] buf = new byte[MAX_BYTE_SIZE];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                handlePacketIn(packet.getData(), address, port);
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
    }

    /**
     * Gets called when start() is called. Starts waitForPacket().
     */
    public void run () {
        System.out.println("CommunicationModule Running");
        waitForPacket();
        socket.close();
    }

    /**
     * Handles incoming packets. If packets begin with "GET", retrieve reference from ObjectReferenceTable. If packets begin with "ADD"
     * add reference to ObjectReferenceTable.
     * @param packetBytes
     * @param address
     * @param port
     * @throws IOException
     */
    public void handlePacketIn(byte[] packetBytes, InetAddress address, int port) throws IOException{
        String result = new String(packetBytes);
        String[] arguments = result.split(" ");
        String action = arguments[0];
        String response = "";
        if (action.contains("GET")){
            System.out.println("Arguments are: " + arguments[0] + " " + arguments[1].trim());
            try {
                response = objRefTable.getObjectReference(arguments[1].trim());
            } catch (NameDoesNotExistException e){
                response = e.getMessage();
            }
        } else if (action.contains("ADD")){
            System.out.println("Arguments are: " + arguments[0] + " " + arguments[1] + " " + arguments[2].trim());
            try {
                response = objRefTable.addObjectReference(arguments[1], arguments[2].trim());
            } catch (NameExistsException e){
                response = e.getMessage();
            }
        }
        byte[] payload = new byte[MAX_BYTE_SIZE];
        System.arraycopy(response.getBytes(), 0, payload, 0, response.getBytes().length);
        sendResponsePacketOut(payload, address, port);
    }

    /**
     * Sends a response back out.
     * @param payload
     * @param address
     * @param port
     * @throws IOException
     */
    public void sendResponsePacketOut(byte[] payload, InetAddress address, int port) throws IOException{
        DatagramPacket packet = new DatagramPacket(payload, payload.length, address, port);
        socket.send(packet);
    }

    /**
     * Set the ObjectReferenceTable for use in this module
     * @param ort
     */
    public void setObjectReferenceTable(ObjectReferenceTable ort){
        this.objRefTable = ort;
    }


}
