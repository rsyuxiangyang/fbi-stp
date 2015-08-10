package skyline.security;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugPhaseListener implements PhaseListener {
    public void afterPhase(PhaseEvent pe) {
        System.out.print("\n   after - " + pe.getPhaseId().toString());
        if (pe.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            System.out.println("\n===Done with Request!\n");
        }
    }
    public void beforePhase(PhaseEvent pe) {
        if (pe.getPhaseId() == PhaseId.RESTORE_VIEW) {
            String url = pe.getFacesContext().getExternalContext().getRequestServletPath();
            System.out.print("\n===Processing new Request! " +
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " " +url);
        }
        System.out.print("\n   before - " + pe.getPhaseId().toString());
    }
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
