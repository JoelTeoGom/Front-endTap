package Frontend;

import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldActor;
import HelloWorld.HelloWorldMessage;
import Insult.AddInsultMessage;
import Insult.GetAllInsultMessage;
import Insult.GetInsultMessage;
import Insult.InsultActor;
import Message.Message;
import Observer.MonitorService;
import RingActor.RingActor;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller {

    private ActorProxy from;
    private ActorProxy to;
    private static Controller instance;

    private Controller() {}

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public String llistaActor(){
        return ActorContext.getInstance().getNames().toString();
    }

    public void ringActorApp() throws InterruptedException {
        ArrayList<RingActor> ring = new ArrayList<>();
        ring.add(0, new RingActor());
        int i = 1;
        while (i < 20) {
            RingActor anterior = ring.get(i - 1);
            RingActor actual = new RingActor();
            anterior.setNext(ActorContext.getInstance().spawnActor("Ring" + i, actual));
            ring.add(i, actual);
            if (i == 19)
                actual.setNext(ActorContext.getInstance().spawnActor("Primero", ring.get(0)));
            i++;
        }
        for (i = 1; i <= 20; i++) {
            RingActor inicial = ring.get(1);
            inicial.process(new Message(ActorContext.getInstance().lookup("Primero"), "" + i));
        }

    }


    public void sendHelloWorldMessage(ArrayList<String> frame) throws InterruptedException {
        if (ActorContext.getInstance().getActorLibrary().containsKey(frame.get(0))){
            from = ActorContext.getInstance().lookup(frame.get(0));
        }else{
            from = ActorContext.getInstance().spawnActor(frame.get(0),new InsultActor());
        }
        if (ActorContext.getInstance().getActorLibrary().containsKey(frame.get(1))){
            to = ActorContext.getInstance().lookup(frame.get(1));
        }else{
            to = ActorContext.getInstance().spawnActor(frame.get(1),new InsultActor());
        }
        int nMensajes = Integer.parseInt(frame.get(2));
        String text = frame.get(3);
        for(int i= 1; i<=nMensajes; i++)
            to.send(new HelloWorldMessage(from,text+i));
    }

    public void sendAddInsultMessage(ArrayList<String> frame) throws InterruptedException {
        if (ActorContext.getInstance().getActorLibrary().containsKey(frame.get(0))){
            from = ActorContext.getInstance().lookup(frame.get(0));
        }else{
            from = ActorContext.getInstance().spawnActor(frame.get(0),new InsultActor());
        }
        if (ActorContext.getInstance().getActorLibrary().containsKey(frame.get(1))){
            to = ActorContext.getInstance().lookup(frame.get(1));
        }else{
            to = ActorContext.getInstance().spawnActor(frame.get(1),new InsultActor());
        }
        int nMensajes = Integer.parseInt(frame.get(2));
        String text = frame.get(3);
        for(int i= 1; i<=nMensajes; i++)
            to.send(new AddInsultMessage(from,text+i));
    }

    public String sendGetInsultMessage(ArrayList<String> frame) throws InterruptedException {
        to.send(new GetInsultMessage(from));
        return from.receive().getMessage();
    }

    public String sendGetAllInsultMessage(ArrayList<String> frame) throws InterruptedException {
        to.send(new GetAllInsultMessage(from));
        return from.receive().getMessage();
    }


    public void ringActor(String name){
        ActorContext.getInstance().spawnActor(name,new RingActor());
    }
    public void ringActor(int n){
        ArrayList<RingActor> ring = new ArrayList<>();
        ring.add(0,new RingActor());
        int i = 1;
        while (i < n){
            RingActor anterior = ring.get(i-1);
            RingActor actual = new RingActor();
            anterior.setNext(ActorContext.getInstance().spawnActor("Ring"+i, actual));
            ring.add(i, actual);
            if(i == n-1)
                actual.setNext(ActorContext.getInstance().spawnActor("Primero",ring.get(0)));
            i++;
        }
    }

    public void insultActor(String name){
        ActorContext.getInstance().spawnActor(name,new InsultActor());
    }
    public void insultActor(int n){
        int size = ActorContext.getInstance().getActorLibrary().size();
        if(n != 0 ){
            for(int i = 1; i<=n; i++){
                int offset = size + i;
                ActorContext.getInstance().spawnActor("insult"+offset, new InsultActor());
            }
        }
    }

    public void helloWorldActor(String name){
        ActorContext.getInstance().spawnActor(name,new HelloWorldActor());
    }

    public void helloWorldActor(int n){
        int size = ActorContext.getInstance().getActorLibrary().size();
        if(n != 0 ){
            for(int i = 1; i<=n; i++){
                int offset = size + i;
                ActorContext.getInstance().spawnActor("helloWorld"+offset, new HelloWorldActor());
            }
        }
    }

    public String allMessages(String name){
        ArrayList<Message> messages = MonitorService.getInstance().getNumberOfMessages(ActorContext.getInstance().getActorLibrary().get(name));
        ArrayList<String> mText = new ArrayList<>();
        int i = 0;
        for (Message m : messages ) {
            mText.add(messages.get(i).getMessage());
            i++;
        }
        return mText.toString();
    }
    public String receiveMessages(String name){
        ArrayList<Message> messages = MonitorService.getInstance().getReceivedMessages(ActorContext.getInstance().getActorLibrary().get(name));
        ArrayList<String> mText = new ArrayList<>();
        int i = 0;
        for (Message m : messages ) {
            mText.add(messages.get(i).getMessage());
            i++;
        }
        return mText.toString();
    }
    public String sendMessages(String name){
        ArrayList<Message> messages = MonitorService.getInstance().getSentMessages(ActorContext.getInstance().getActorLibrary().get(name));
        ArrayList<String> mText = new ArrayList<>();
        int i = 0;
        for (Message m : messages ) {
            mText.add(messages.get(i).getMessage());
            i++;
        }
        return mText.toString();
    }

}
