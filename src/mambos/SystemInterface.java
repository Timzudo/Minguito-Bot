package mambos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public interface SystemInterface {

    String getFrase();

    LinkedList<Iterator<String>> getTeams(String[] names);

}
