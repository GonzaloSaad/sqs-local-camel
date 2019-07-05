package org.poc;

import java.util.Optional;

public class QueueURL {

    private static QueueURL instance;

    private String queueURL;

    private QueueURL(){
    }

    public static QueueURL getInstance(){
        if(instance == null){
            instance = new QueueURL();
        }
        return instance;
    }

    public Optional<String> getQueueURL() {
        return Optional.ofNullable(queueURL);
    }

    public void setQueueURL(String queueURL) {
        this.queueURL = queueURL;
    }
}
