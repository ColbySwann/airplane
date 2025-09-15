package swf.army.mil.aircraft.airplane;

import jakarta.persistence.Id;
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
    public List<Aircraft> getAllAircraft(){
        return aircraftService.findAllAircraft();

    }

    @GetMapping("/{id}")
    public Aircraft getAircraft(@PathVariable Long id){
        return aircraftService.findAircraft(id);
    }



}
