package com.fcl.uklug;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.faces.context.FacesContext;

import lotus.domino.Database;
import lotus.domino.DateTime;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;

import com.ibm.domino.xsp.module.nsf.NotesContext;

public class ControlPanel implements Serializable {
	public static final String BEAN_NAME = "controlpanelbean"; // name of the
	// bean
	private static final long serialVersionUID = 1L;

	public static ControlPanel get() {
		FacesContext context = FacesContext.getCurrentInstance();
		ControlPanel bean = (ControlPanel) context.getApplication().getVariableResolver().resolveVariable(context, BEAN_NAME);
		return bean;
	}

	private int agendaDays;
	private int agendaRooms;
	private String enableAgenda;
	private String enableExtraSessionFields;

	private String enableForum;

	private String enableRegistration;
	private String enableSubmitSession;
	private String eventName;
	private String feedbackSendTo;
	private String forgottenEmailBody;
	private String forgottenEmailFromEmail;
	private String forgottenEmailFromName;
	private String forgottenEmailSubject;
	private String registrationEmailBody;
	private String registrationEmailFromEmail;
	private String registrationEmailFromName;
	private String registrationEmailSubject;
	private String registrationNAB;
	private String registrationUsersGroup;
	private String sessionEmailAcceptedBody;
	private String sessionEmailAcceptedSubject;
	private String sessionEmailFromEmail;
	private String sessionEmailFromName;
	private String sessionEmailRejectedBody;
	private String sessionEmailRejectedSubject;
	private String sessionVoting;
	private Date startDate;
	private String[] tracks;
	private String unid;

	public ControlPanel() throws NotesException {
		init();
	}

	public int getAgendaDays() {
		return agendaDays;
	}

	public int getAgendaRooms() {
		return agendaRooms;
	}

	private Database getCurrentDatabase() {
		NotesContext nc = NotesContext.getCurrentUnchecked();
		return (null != nc) ? nc.getCurrentDatabase() : null;
	}

	public String getEnableAgenda() {
		return enableAgenda;
	}

	public String getEnableExtraSessionFields() {
		return enableExtraSessionFields;
	}

	public String getEnableForum() {
		return enableForum;
	}

	public String getEnableRegistration() {
		return enableRegistration;
	}

	public String getEnableSubmitSession() {
		return enableSubmitSession;
	}

	public String getEventName() {
		return eventName;
	}

	public String getFeedbackSendTo() {
		return feedbackSendTo;
	}

	public String getForgottenEmailBody() {
		return forgottenEmailBody;
	}

	public String getForgottenEmailFromEmail() {
		return forgottenEmailFromEmail;
	}

	public String getForgottenEmailFromName() {
		return forgottenEmailFromName;
	}

	public String getForgottenEmailSubject() {
		return forgottenEmailSubject;
	}

	public String getRegistrationEmailBody() {
		return registrationEmailBody;
	}

	public String getRegistrationEmailFromEmail() {
		return registrationEmailFromEmail;
	}

	public String getRegistrationEmailFromName() {
		return registrationEmailFromName;
	}

	public String getRegistrationEmailSubject() {
		return registrationEmailSubject;
	}

	public String getRegistrationNAB() {
		return registrationNAB;
	}

	public String getRegistrationUsersGroup() {
		return registrationUsersGroup;
	}

	public String getSessionEmailAcceptedBody() {
		return sessionEmailAcceptedBody;
	}

	public String getSessionEmailAcceptedSubject() {
		return sessionEmailAcceptedSubject;
	}

	public String getSessionEmailFromEmail() {
		return sessionEmailFromEmail;
	}

	public String getSessionEmailFromName() {
		return sessionEmailFromName;
	}

	public String getSessionEmailRejectedBody() {
		return sessionEmailRejectedBody;
	}

	public String getSessionEmailRejectedSubject() {
		return sessionEmailRejectedSubject;
	}

	public String getSessionVoting() {
		return sessionVoting;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String[] getTracks() {
		return tracks;
	}

	public String getUnid() {
		return unid;
	}

	@SuppressWarnings("unchecked")
	public void init() throws NotesException {
		Database database = getCurrentDatabase();
		View controlpanels = database.getView("Control Panels");
		Document controlpaneldoc = controlpanels.getFirstDocument();
		if (controlpaneldoc == null) {
			controlpaneldoc = database.createDocument();
			controlpaneldoc.replaceItemValue("Form", "ControlPanel");
			controlpaneldoc.computeWithForm(false, false);
			controlpaneldoc.save();
		}
		this.setUnid(controlpaneldoc.getUniversalID());
		this.setEnableRegistration(controlpaneldoc.getItemValueString("EnableRegistration"));
		this.setAgendaDays(controlpaneldoc.getItemValueInteger("AgendaDays"));
		this.setAgendaRooms(controlpaneldoc.getItemValueInteger("AgendaRooms"));
		this.setEnableAgenda(controlpaneldoc.getItemValueString("EnableAgenda"));
		this.setEnableForum(controlpaneldoc.getItemValueString("EnableForum"));
		this.setEnableSubmitSession(controlpaneldoc.getItemValueString("EnableSubmitSession"));
		this.setEventName(controlpaneldoc.getItemValueString("EventName"));
		this.setFeedbackSendTo(controlpaneldoc.getItemValueString("FeedbackSendTo"));
		this.setRegistrationNAB(controlpaneldoc.getItemValueString("RegistrationNAB"));
		this.setRegistrationUsersGroup(controlpaneldoc.getItemValueString("RegistrationUsersGroup"));
		Vector<DateTime> dates = controlpaneldoc.getItemValueDateTimeArray("StartDate");
		this.setStartDate(dates.elementAt(0).toJavaDate());
		Vector<String> v = controlpaneldoc.getItemValue("Tracks");
		this.setTracks(v.toArray(new String[v.size()]));
		this.setRegistrationEmailSubject(controlpaneldoc.getItemValueString("RegistrationEmailSubject"));
		this.setRegistrationEmailBody(controlpaneldoc.getItemValueString("RegistrationEmailBody"));
		this.setRegistrationEmailFromEmail(controlpaneldoc.getItemValueString("RegistrationEmailFromEmail"));
		this.setRegistrationEmailFromName(controlpaneldoc.getItemValueString("RegistrationEmailFromName"));
		this.setForgottenEmailSubject(controlpaneldoc.getItemValueString("ForgottenEmailSubject"));
		this.setForgottenEmailBody(controlpaneldoc.getItemValueString("ForgottenEmailBody"));
		this.setForgottenEmailFromEmail(controlpaneldoc.getItemValueString("ForgottenEmailFromEmail"));
		this.setForgottenEmailFromName(controlpaneldoc.getItemValueString("ForgottenEmailFromname"));
		this.setSessionEmailFromEmail(controlpaneldoc.getItemValueString("SessionEmailFromEmail"));
		this.setSessionEmailFromName(controlpaneldoc.getItemValueString("SessionEmailFromName"));
		this.setSessionEmailAcceptedSubject(controlpaneldoc.getItemValueString("SessionEmailAcceptedSubject"));
		this.setSessionEmailAcceptedBody(controlpaneldoc.getItemValueString("SessionEmailAcceptedBody"));
		this.setSessionEmailRejectedSubject(controlpaneldoc.getItemValueString("SessionEmailRejectedSubject"));
		this.setSessionEmailRejectedBody(controlpaneldoc.getItemValueString("SessionEmailRejectedBody"));
		this.setSessionVoting(controlpaneldoc.getItemValueString("SessionVoting"));
		this.setEnableExtraSessionFields(controlpaneldoc.getItemValueString("EnableExtraSessionFields"));
		controlpaneldoc.recycle();
		controlpanels.recycle();
	}

	public void setAgendaDays(int agendaDays) {
		this.agendaDays = agendaDays;
	}

	public void setAgendaRooms(int agendaRooms) {
		this.agendaRooms = agendaRooms;
	}

	public void setEnableAgenda(String enableAgenda) {
		this.enableAgenda = enableAgenda;
	}

	public void setEnableExtraSessionFields(String enableExtraSessionFields) {
		this.enableExtraSessionFields = enableExtraSessionFields;
	}

	public void setEnableForum(String enableForum) {
		this.enableForum = enableForum;
	}

	public void setEnableRegistration(String enableRegistration) {
		this.enableRegistration = enableRegistration;
	}

	public void setEnableSubmitSession(String enableSubmitSession) {
		this.enableSubmitSession = enableSubmitSession;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setFeedbackSendTo(String feedbackSendTo) {
		this.feedbackSendTo = feedbackSendTo;
	}

	public void setForgottenEmailBody(String forgottenEmailBody) {
		this.forgottenEmailBody = forgottenEmailBody;
	}

	public void setForgottenEmailFromEmail(String forgottenEmailFromEmail) {
		this.forgottenEmailFromEmail = forgottenEmailFromEmail;
	}

	public void setForgottenEmailFromName(String forgottenEmailFromName) {
		this.forgottenEmailFromName = forgottenEmailFromName;
	}

	public void setForgottenEmailSubject(String forgottenEmailSubject) {
		this.forgottenEmailSubject = forgottenEmailSubject;
	}

	public void setRegistrationEmailBody(String registrationEmailBody) {
		this.registrationEmailBody = registrationEmailBody;
	}

	public void setRegistrationEmailFromEmail(String registrationEmailFromEmail) {
		this.registrationEmailFromEmail = registrationEmailFromEmail;
	}

	public void setRegistrationEmailFromName(String registrationEmailFromName) {
		this.registrationEmailFromName = registrationEmailFromName;
	}

	public void setRegistrationEmailSubject(String registrationEmailSubject) {
		this.registrationEmailSubject = registrationEmailSubject;
	}

	public void setRegistrationNAB(String registrationNAB) {
		this.registrationNAB = registrationNAB;
	}

	public void setRegistrationUsersGroup(String registrationUsersGroup) {
		this.registrationUsersGroup = registrationUsersGroup;
	}

	public void setSessionEmailAcceptedBody(String sessionEmailAcceptedBody) {
		this.sessionEmailAcceptedBody = sessionEmailAcceptedBody;
	}

	public void setSessionEmailAcceptedSubject(String sessionEmailAcceptedSubject) {
		this.sessionEmailAcceptedSubject = sessionEmailAcceptedSubject;
	}

	public void setSessionEmailFromEmail(String sessionEmailFromEmail) {
		this.sessionEmailFromEmail = sessionEmailFromEmail;
	}

	public void setSessionEmailFromName(String sessionEmailFromName) {
		this.sessionEmailFromName = sessionEmailFromName;
	}

	public void setSessionEmailRejectedBody(String sessionEmailRejectedBody) {
		this.sessionEmailRejectedBody = sessionEmailRejectedBody;
	}

	public void setSessionEmailRejectedSubject(String sessionEmailRejectedSubject) {
		this.sessionEmailRejectedSubject = sessionEmailRejectedSubject;
	}

	public void setSessionVoting(String sessionVoting) {
		this.sessionVoting = sessionVoting;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTracks(String[] tracks) {
		this.tracks = tracks;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}
}