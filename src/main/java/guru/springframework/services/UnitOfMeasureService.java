package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by Andras Laczo 2020. 04. 02.
 */

@Service
public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();

}
