package google.drive.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import google.drive.config.kafka.KafkaProcessor;
import google.drive.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    IndexRepository indexRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FileUploaded'"
    )
    public void wheneverFileUploaded_MakeIndex(
        @Payload FileUploaded fileUploaded
    ) {
        FileUploaded event = fileUploaded;
        System.out.println(
            "\n\n##### listener MakeIndex : " + fileUploaded + "\n\n"
        );

        // Sample Logic //
        Index.makeIndex(event);
    }

    @Autowired
    google.drive.external.IndexService indexService;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FileDeleted'"
    )
    public void wheneverFileDeleted_DeleteIndex(
        @Payload FileDeleted fileDeleted
    ) {
        FileDeleted event = fileDeleted;
        System.out.println(
            "\n\n##### listener DeleteIndex : " + fileDeleted + "\n\n"
        );
        // REST Request Sample

        // indexService.getIndex(/** mapping value needed */);

        // Sample Logic //

    }
    // keep

}
