package otros;

import Estructura.*;
import HelloWorld.*;
import Message.Message;
import Observer.MonitorService;
import Observer.Traffic;


public class RingActor extends Actor {
    private ActorProxy next= null;

    public RingActor(){
        super();
    }

    @Override
    public void process(Message message) throws InterruptedException {
        //System.out.println(message.getMessage());
        traffic++;
        MonitorService.getInstance().putAllMessages(this,message);
        MonitorService.getInstance().putReceivedMessage(this,message);

        if(message.getFrom().getSourceActor().equals(this)){
            System.out.println("Fin de la vuelta "+ Integer.parseInt(message.getMessage()));
        }else {
            MonitorService.getInstance().putSentMessage(this,message);
            next.send(message);
        }
    }

    public ActorProxy getNext() {
        return next;
    }

    public void setNext(ActorProxy next) {
        this.next = next;
    }
}