package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UnitOfMeasureServiceImplTest {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureReactiveRepository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        uom1.setDescription("ounce");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");
        uom2.setDescription("cup");

        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasures.add(uom1);
        unitOfMeasures.add(uom2);

        when(repository.findAll()).thenReturn(Flux.just(uom1, uom2));

        //when
        List<UnitOfMeasureCommand> unitOfMeasureCommands = service.listAllUoms().collectList().block();

        //then
        assertEquals(2, unitOfMeasureCommands.size());
        verify(repository).findAll();
    }
}