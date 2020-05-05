package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UnitOfMeasureServiceImplTest {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository repository;

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

        when(repository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = service.listAllUoms();
        //then
        assertEquals(2, unitOfMeasureCommands.size());
        verify(repository).findAll();
    }
}