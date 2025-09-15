package swf.army.mil.aircraft.pilot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilot")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService){
        this.pilotService = pilotService;
    }

    @PostMapping
    public Pilot createPilot(@RequestBody Pilot newPilot){
        return pilotService.savePilot(newPilot);
    }

    @GetMapping
    public List<Pilot> getAllPilots(){
        return pilotService.findAllPilots();
    }

    @GetMapping("/{id}")
    public Pilot getPilotById(@PathVariable Long id){
        return pilotService.findPilotById(id);
    }
}
