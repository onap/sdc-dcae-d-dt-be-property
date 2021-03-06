/*-
 * ============LICENSE_START=======================================================
 * sdc-dcae-d-dt-be-property
 * ================================================================================
 * Copyright (C) 2017 - 2019 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.sdc.dcae.composition.restmodels.ruleeditor;

import java.util.Objects;

public class LogTextAction extends BaseAction {

	private LogText logText;

	private class LogText {
		private String name;
		private String level;
		private String text;

		public String getName() {
			return name;
		}

		public String getLevel() {
			return level;
		}

		public String getText() {
			return text;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (null == obj || getClass() != obj.getClass()) {
				return false;
			}
			LogText other = (LogText) obj;
			return Objects.equals(name, other.name) && Objects.equals(level, other.level) && Objects.equals(text, other.text);
		}

		@Override
		public int hashCode(){
			return Objects.hash(name, level, text);
		}
	}

	public LogText getLogText() {
		return logText;
	}

	public void setLogText(LogText logText) {
		this.logText = logText;
	}

	public String logName() {
		return logText.name;
	}

	public String logLevel() {
		return logText.level;
	}

	public String logText() {
		return logText.text;
	}

	public void setLogText(String name, String level, String text) {
		LogText logTextInput = new LogText();
		logTextInput.name = name;
		logTextInput.level = level;
		logTextInput.text = text;
		this.logText = logTextInput;
	}

	//TODO the text field supports ${} notation - should we check if it references another action target?
	public boolean referencesTarget(BaseAction other){
		return false;
	}

	public String strippedTarget(){return "";}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && Objects.equals(logText, ((LogTextAction)obj).logText);
	}

	@Override
	public int hashCode(){
		return Objects.hash(logText);
	}

}
