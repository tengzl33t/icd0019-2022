package generics.connection;

import java.util.*;

public class ConnectionFinder {
    private List<Connection> cons = new ArrayList<>();

    public void addAll(List<Connection> connections) {
        cons.addAll(connections);
    }

    public void add(Connection connection) {
        cons.add(connection);
    }

    public List<Connection> getCons() {
        return cons;
    }

    public boolean hasConnection(String a, String b) {
        if (cons.toArray().length == 1){
            return true;
        }

//        System.out.println(cons);
//
//        for (int i = 1; i < cons.toArray().length; i++) {
//            var prevTo = cons.get(i - 1).getTo();
//            var currFrom = cons.get(i).getFrom();
//
//            System.out.println("current values: " + prevTo + " " + currFrom);
//
//            if (!currFrom.equals(prevTo)){
//                return false;
//            }
//        }


        System.out.println(findFirstValue(a));
        // (a, b)

        System.out.println(findNextValue(findFirstValue(a)));


        return true;
    }


    private Connection findFirstValue(String value){
        for (int i = 0; i < cons.toArray().length; i++) {
            if (cons.get(i).getFrom().equals(value)){
                return cons.get(i);
            }
        }
        return null;
    }

    private Object findNextValue(Connection values){
        for (int i = 0; i < cons.toArray().length; i++) {

        }
        return null;
    }

    public List<String> findConnection(String a, String b) {
        throw new RuntimeException("not implemented yet");
    }
}
