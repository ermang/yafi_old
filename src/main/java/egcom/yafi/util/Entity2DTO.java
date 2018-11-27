package egcom.yafi.util;

import egcom.yafi.dto.ThreadDTO;
import egcom.yafi.dto.TopicDTO;
import egcom.yafi.entity.Thread;
import egcom.yafi.entity.Topic;
import egcom.yafi.projection.PlainThread;


import java.time.format.DateTimeFormatter;

public class Entity2DTO {

    public ThreadDTO thread2ThreadDTO(Thread t) {
        ThreadDTO tDTO= new ThreadDTO();
        //tDTO.id = t.getId();
        tDTO.content = t.getContent();
        tDTO.username = t.getYafiUser().getUsername();
        tDTO.topicName = t.getTopic().getName();
        tDTO.likeCount = t.getLikeCount();
        tDTO.createdOn = t.getCreatedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return tDTO;

    }

    public TopicDTO topic2TopicDTO(Topic t) {
        TopicDTO topicDTO = new TopicDTO(t.getName());
//        topicDTO.name = t.getName();
//        topicDTO.createdBy = t.getYafiUser().getUsername();

        return topicDTO;
    }

//    public TopicDTO topicOnly2TopicDTO(TopicOnly t) {
//        TopicDTO topicDTO = new TopicDTO();
//        topicDTO.name = t.getName();
//
//        return topicDTO;
//    }

    public ThreadDTO plainThread2ThreadDTO(PlainThread t) {
        ThreadDTO tDTO= new ThreadDTO();
        //tDTO.id = t.getId();
        tDTO.content = t.getContent();
        //tDTO.username = t.getYafiUser().getUsername();
        //tDTO.topicName = t.getTopic().getName();
        tDTO.likeCount = t.getLikeCount();
        //tDTO.createdOn = t.getCreatedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return tDTO;
    }
}
