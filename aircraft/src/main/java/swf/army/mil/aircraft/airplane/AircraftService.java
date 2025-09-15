package swf.army.mil.aircraft.airplane;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository){
        this.aircraftRepository = aircraftRepository;
    }

    public Aircraft saveAircraft(Aircraft aircraft){
        return aircraftRepository.save(aircraft);
    }

    public Aircraft findAircraft(Long id){
       return aircraftRepository.findById(id).get();
    }

    public List<Aircraft> findAllAircraft(){
        return aircraftRepository.findAll();
    }

    public List<Aircraft> removeAircraft(Long id){
        return aircraftRepository.deleteById(id);

    }
}
