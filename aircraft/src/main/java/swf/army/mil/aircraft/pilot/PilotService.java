package swf.army.mil.aircraft.pilot;

import org.springframework.stereotype.Service;
import swf.army.mil.aircraft.airplane.Aircraft;

import java.util.List;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;

    public PilotService(PilotRepository pilotRepository){
        this.pilotRepository = pilotRepository;
    }

    public Pilot savePilot(Pilot pilot){
        return pilotRepository.save(pilot);
    }

    public Pilot findPilotById(Long id){
        return pilotRepository.findById(id).get();
    }

    public List<Pilot> findAllPilots(){
        return pilotRepository.findAll();
    }

}
