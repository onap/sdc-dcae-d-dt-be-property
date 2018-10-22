package org.onap.sdc.dcae.composition.restmodels.ruleeditor;

public class ImportPhaseRequest extends RuleEditorRequest {

	private String groupId;
	private String phase;
	private String version;
	private String eventType;
	private MappingRulesResponse payload;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public MappingRulesResponse getPayload() {
		return payload;
	}

	public void setPayload(MappingRulesResponse payload) {
		this.payload = payload;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}