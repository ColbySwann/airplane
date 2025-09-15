package swf.army.mil.aircraft.airplane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import swf.army.mil.aircraft.pilot.Pilot;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {

    @Mock
    AircraftRepository aircraftRepository;

    @InjectMocks
    AircraftService aircraftService;
    Pilot snoopy = new Pilot(1L, "Snoopy", "Doo", 33);

    Aircraft doghouse = new Aircraft(1L,"doghouse", snoopy);
    List<Aircraft> flightList;

    @BeforeEach
    void setUp(){
        flightList = new ArrayList<>(List.of(doghouse));
    }

    @Test
    void shouldSaveAircraft() {
        // Arrange
        when(aircraftRepository.save(doghouse)).thenReturn(doghouse);
        // Act
        Aircraft actualAircraft = aircraftService.saveAircraft(doghouse);
        //Assert
        verify(aircraftRepository, times (1)).save(any(Aircraft.class));
        assertThat(actualAircraft).isEqualTo(doghouse);

    }

    @Test
    void findAircraft() {
        //Arrange
        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(doghouse));
        Aircraft foundAircraft = aircraftService.findAircraft(1L);
        assertThat(foundAircraft).isEqualTo(doghouse);
    }

    @Test
    void findAllAircraft() {
        flightList.add(doghouse);
        when(aircraftRepository.findAll()).thenReturn(flightList);
        List<Aircraft> listAllAircraft = aircraftService.findAllAircraft();
        assertThat(listAllAircraft).isEqualTo(flightList);
    }
}