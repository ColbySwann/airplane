package swf.army.mil.aircraft.airplane;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swf.army.mil.aircraft.pilot.Pilot;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirplaneController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class AirplaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AircraftService aircraftService;
    Pilot snoopy = new Pilot(1L, "Snoopy", "Doo", 33);
    Pilot garfield = new Pilot(2L, "Garfield", "Cat", 45);
    Aircraft doghouse = new Aircraft(1L, "doghouse", snoopy);
    Aircraft cathouse = new Aircraft(2L, "cathouse", garfield);
    ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateAircraft() throws Exception{
        Mockito.when(aircraftService.saveAircraft(any(Aircraft.class))).thenReturn(doghouse);
        String doghouseJson = objectMapper.writeValueAsString(doghouse);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(doghouseJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airframe").value("doghouse"));
//                .andExpect(jsonPath("$.pilot").value(snoopy));

        Mockito.verify(aircraftService).saveAircraft(any(Aircraft.class));
    }

    @Test
    void shouldGetAnAircraft() throws Exception{
        aircrafts.add(doghouse);
        aircrafts.add(cathouse);
        Mockito.when(aircraftService.findAircraft(1L)).thenReturn(doghouse);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/aircraft/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldGetAllAircraft() throws Exception{
        aircrafts.add(doghouse);
        aircrafts.add(cathouse);
        Mockito.when(aircraftService.findAllAircraft()).thenReturn(aircrafts);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray());
    }

    @Test
    void shouldDeleteAircraft() throws Exception{
        aircrafts.add(doghouse);
        aircrafts.add(cathouse);
        Mockito.when(aircraftService.removeAircraft(1L)).thenReturn(aircrafts);
    }

}
