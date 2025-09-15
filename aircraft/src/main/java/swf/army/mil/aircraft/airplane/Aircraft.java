package swf.army.mil.aircraft.airplane;

import jakarta.persistence.*;
import swf.army.mil.aircraft.pilot.Pilot;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airframe;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    public Aircraft(Long id, String airframe, Pilot pilot) {
        this.id = id;
        this.airframe = airframe;
        this.pilot = pilot;
    }

    public Aircraft(){
        this.id = null;
        this.airframe = null;
        this.pilot = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirframe() {
        return airframe;
    }

    public void setAirframe(String airframe) {
        this.airframe = airframe;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", airframe='" + airframe + '\'' +
                ", pilot='" + pilot + '\'' +
                '}';
    }
}
