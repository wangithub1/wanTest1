package google.drive.domain;

import google.drive.domain.*;
import google.drive.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class FileDelete extends AbstractEvent {

    private Long id;
    // keep

}
