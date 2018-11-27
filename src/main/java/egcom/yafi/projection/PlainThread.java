package egcom.yafi.projection;

public interface PlainThread {
     String getContent();
     TopicSummary getTopic();
     YafiUserSummary getYafiUser();
     Long getLikeCount();
     String getCreatedOn();

     interface YafiUserSummary {
         String getUsername();
     }

     interface TopicSummary {
         String getName();
         Long getId();
     }
}
