package Insult;

import Estructura.Actor;
import Estructura.ActorProxy;
import Estructura.ProxyProxy;
import Message.*;
import Observer.Event;
import Observer.MonitorService;

import java.util.ArrayList;
import java.util.List;

public class InsultActor extends Actor {

    private List<String> listaInsultos = new ArrayList<>();

    /**
     * constructor InsultActor
     */
    public InsultActor() {
        super();
    }

    /**
     * metode process: mirem quin tipus de classe insult es i actuem depenent d'aquesta
     * @param m
     */

    public void process(Message m) throws InterruptedException {
        Message message = null;
        MonitorService.getInstance().publish(Event.RECEIVE,this,m);
        if( m instanceof AddInsultMessage) {
            listaInsultos.add(m.getMessage());
        }else if(m instanceof GetInsultMessage) {
                int numeroAleatorio = (int) (Math.random() * (listaInsultos.size()) + 0);
                ProxyProxy auxProxy = new ProxyProxy(m.getFrom());
                message = new Message(new ActorProxy(this), listaInsultos.get(numeroAleatorio));
                MonitorService.getInstance().publish(Event.SEND, this, message);
                auxProxy.send(message);
        }else if( m instanceof GetAllInsultMessage){
            ProxyProxy aux = new ProxyProxy(m.getFrom());
            message = new Message(new ActorProxy(this), listaInsultos.toString());
            MonitorService.getInstance().publish(Event.SEND, this, message);
            aux.send(message);
        }else if (m instanceof QuitMessage){
                MonitorService.getInstance().publish(Event.STOPPED,this,m);
                System.out.println("Oh hell naw!!!");
                exit = true;

        }else {
            System.out.println("No s'ha enviat cap missatge");
        }


    }

    public List<String> getListaInsultos() {
        return listaInsultos;
    }

    public void setListaInsultos(List<String> listaInsultos) {
        this.listaInsultos = listaInsultos;
    }
}
