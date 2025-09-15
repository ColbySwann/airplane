package swf.army.mil.aircraft.pilot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PilotController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class PilotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    PilotService pilotService;
    Pilot topGun = new Pilot(1L, "Top", "Gun", 27);
    Pilot testPilot = new Pilot(2L, "Test", "Pilot", 88);
    ArrayList<Pilot> pilots = new ArrayList<Pilot>();

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePilot() throws Exception{
        Mockito.when(pilotService.savePilot(any(Pilot.class))).thenReturn(topGun);
        String topGunJson = objectMapper.writeValueAsString(topGun);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/pilot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(topGunJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Top"));
        Mockito.verify(pilotService).savePilot(any(Pilot.class));
    }

    @Test
    void shouldGetAnPilot() throws Exception{
        pilots.add(topGun);
        pilots.add(testPilot);
        Mockito.when(pilotService.findPilotById(1L)).thenReturn(topGun);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pilot/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldGetAllPilots() throws Exception{
        pilots.add(topGun);
        pilots.add(testPilot);
        Mockito.when(pilotService.findAllPilots()).thenReturn(pilots);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pilot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray());
    }


}