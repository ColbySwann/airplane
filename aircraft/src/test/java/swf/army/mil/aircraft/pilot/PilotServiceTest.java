package swf.army.mil.aircraft.pilot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PilotServiceTest {

    @Mock
    PilotRepository pilotRepository;

    @InjectMocks
    PilotService pilotService;
    Pilot topGun = new Pilot(1L, "Top", "Gun", 27);
    List<Pilot> pilotList;

    @BeforeEach
    void setUp(){
        pilotList = new ArrayList<>(List.of(topGun));
    }

    @Test
    void shouldSavePilot() {
        when(pilotRepository.save(topGun)).thenReturn(topGun);

        Pilot actualPilot = pilotService.savePilot(topGun);

        verify(pilotRepository, times(1)).save(any(Pilot.class));
        assertThat(actualPilot).isEqualTo(topGun);
    }

    @Test
    void findAllAircraft() {
        pilotList.add(topGun);
        when(pilotRepository.findAll()).thenReturn(pilotList);
        List<Pilot> listAllPilots = pilotService.findAllPilots();
        assertThat(listAllPilots).isEqualTo(pilotList);
    }

    @Test
    void findPilotById() {
        when(pilotRepository.findById(1L)).thenReturn(Optional.of(topGun));
        Pilot foundPilot = pilotService.findPilotById(1L);
        assertThat(foundPilot).isEqualTo(topGun);
    }
}