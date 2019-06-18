package org.onap.sdc.dcae.composition.restmodels.ruleeditor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

public class BaseCopyAction extends UnaryFieldAction {

	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}


	public String fromValue() {
		if(null == getFrom()) {
			return null;
		}
		return StringUtils.isNoneBlank(getFrom().getValue()) ? getFrom().getValue() : StringUtils.join(fromValues(), "");
	}


	public String regexValue() {
		return getFrom().getRegex();
	}

	//using 'other != this' rather than '!this.equals(other)' because technically it is possible to have two identical actions that are not the same action
	@Override
	public boolean referencesTarget(BaseAction other) {
		return getFrom().referencesTarget(other.strippedTarget()) ||
				other != this && !CollectionUtils.isEmpty(getFrom().getValues()) && getFrom().getValues().stream().anyMatch(p -> p.referencesTarget(other.strippedTarget()));
	}

	@Override
	public String strippedTarget() {
		return target.startsWith("${") && target.endsWith("}") ? target.substring(2, target.length()-1) : target;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && Objects.equals(target, ((BaseCopyAction)obj).target);
	}

	@Override
	public int hashCode(){
		return Objects.hash(this.target);
	}

}