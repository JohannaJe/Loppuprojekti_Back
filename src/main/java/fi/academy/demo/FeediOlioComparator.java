package fi.academy.demo;

import java.util.Comparator;

public class FeediOlioComparator implements Comparator<Feedi> {

    @Override
    public int compare(Feedi o1, Feedi o2) {
        return o1.getAikaleima().compareTo(o2.getAikaleima());
    }
}