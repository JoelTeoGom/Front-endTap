package Frontend;

import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldMessage;
import Insult.AddInsultMessage;
import Insult.GetAllInsultMessage;
import Insult.GetInsultMessage;
import Insult.InsultActor;
import Message.Message;
import RingActor.RingActor;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    private static Controller instance;

    private Controller() {}

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
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


    public void sendHelloWorldMessage(SendReceive frame) throws InterruptedException {
        ActorProxy actorProxyFrom = ActorContext.getInstance().spawnActor(frame.getFrom().getText(),new InsultActor());
        ActorProxy actorProxyTo = ActorContext.getInstance().spawnActor(frame.getTo().getText(),new InsultActor());
        int nMensajes = Integer.parseInt(frame.getMessageNum().getText());
        String text = frame.getTextMessage().getText();
        for(int i= 1; i<=nMensajes; i++)
            actorProxyTo.send(new HelloWorldMessage(actorProxyFrom,text+i));
    }

    public void sendAddInsultMessage(SendReceive frame) throws InterruptedException {
        ActorProxy actorProxyFrom = ActorContext.getInstance().spawnActor(frame.getFrom().getText(),new InsultActor());
        ActorProxy actorProxyTo = ActorContext.getInstance().spawnActor(frame.getTo().getText(),new InsultActor());
        int nMensajes = Integer.parseInt(frame.getMessageNum().getText());
        String text = frame.getTextMessage().getText();
        for(int i= 1; i<=nMensajes; i++)
            actorProxyTo.send(new AddInsultMessage(actorProxyFrom,text+i));
    }

    public void sendGetInsultMessage(SendReceive frame) throws InterruptedException {
        ActorProxy actorProxyFrom = ActorContext.getInstance().spawnActor(frame.getFrom().getText(),new InsultActor());
        ActorProxy actorProxyTo = ActorContext.getInstance().spawnActor(frame.getTo().getText(),new InsultActor());
        String receive = actorProxyFrom.receive().getMessage();

    }

    public void sendGetAllInsultMessage(SendReceive frame) throws InterruptedException {
        ActorProxy actorProxyFrom = ActorContext.getInstance().spawnActor(frame.getFrom().getText(),new InsultActor());
        ActorProxy actorProxyTo = ActorContext.getInstance().spawnActor(frame.getTo().getText(),new InsultActor());

        actorProxyTo.send(new GetAllInsultMessage(actorProxyFrom));
        String receive = actorProxyFrom.receive().getMessage();
    }


}
