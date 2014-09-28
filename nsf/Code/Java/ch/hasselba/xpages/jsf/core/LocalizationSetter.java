package ch.hasselba.xpages.jsf.core;
 
import javax.faces.context.FacesContext;
import javax.faces.application.Application;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.component.UIViewRoot;
import java.util.Locale;
import java.util.Map;
 
public class LocalizationSetter implements PhaseListener {
 
    private static final long serialVersionUID = -1L;
    private static final String scopeVarName = "Language";
    private static final String scopeName = "sessionScope"; 
 
    public void afterPhase(PhaseEvent event) {}
 
    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        UIViewRoot view = facesContext.getViewRoot();
        view.setLocale( getLanguage(facesContext) ) ;
    }
 
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
 
    private Locale getLanguage( FacesContext facesContext ){
        try{
            Application app = facesContext.getApplication();
            Object obj = app.getVariableResolver().resolveVariable(facesContext, scopeName );
            Object lang = ((Map) obj).get( scopeVarName );
            if( lang != null ){
                return new Locale((String) lang);
            }
        }catch(Exception e){}
 
        return Locale.getDefault();
    }
}