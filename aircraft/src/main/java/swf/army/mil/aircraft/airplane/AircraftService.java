package swf.army.mil.aircraft.airplane;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {

    private ArrayList<Aircraft> aircraftList = new ArrayList<>();

    public Aircraft saveAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
        return aircraft;
    }

    public Aircraft findAircraft(Long id){
        for (Aircraft aircraft: aircraftList){
            if(aircraft.getId() == id){
                return aircraft;
            }
        }
        return null;
    }

    public ArrayList<Aircraft> findAllAircraft(){
        Aircraft aircraft1 = new Aircraft(1L, "doghouse", "Snoopy");
        Aircraft aircraft2 = new Aircraft(2L, "cathouse", "garfield");
        aircraftList.add(aircraft1);
        aircraftList.add(aircraft2);
        return aircraftList;
    }
}
