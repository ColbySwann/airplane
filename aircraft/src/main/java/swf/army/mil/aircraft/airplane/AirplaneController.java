package swf.army.mil.aircraft.airplane;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AirplaneController {

    private final AircraftService aircraftService;

    public AirplaneController(AircraftService aircraftService){
        this.aircraftService = aircraftService;
    }

    @PostMapping
    public Aircraft createAircraft(@RequestBody Aircraft newAircraft){
        return aircraftService.saveAircraft(newAircraft);

    }

    @GetMapping
    public ArrayList<Aircraft> getAllAircraft(){
        return aircraftService.findAllAircraft();

    }
}
