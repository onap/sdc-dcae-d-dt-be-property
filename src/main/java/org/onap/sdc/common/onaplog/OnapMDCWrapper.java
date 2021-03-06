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

package org.onap.sdc.common.onaplog;

import org.onap.sdc.common.onaplog.enums.Severity;
import org.onap.sdc.common.onaplog.interfaces.IOnapMdcWrapper;
import org.onap.sdc.common.onaplog.interfaces.IStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.net.InetAddress;
import java.util.ArrayList;

public class OnapMDCWrapper implements IOnapMdcWrapper {
    private IStopWatch stopWatch;
    private ArrayList<String> mandatoryFields = new ArrayList<>();
    private ArrayList<String> optionalFields = new ArrayList<>();
    private static String hostAddress;
    private static String fqdn;

    private static OnapMDCWrapper instanceMdcWrapper = new OnapMDCWrapper(new Stopwatch());
    private static final Logger log = LoggerFactory.getLogger(OnapMDCWrapper.class.getName());


    // in package classes can instantiate this class
    // to use directly from outside the package usr the getInstance() Method
    OnapMDCWrapper(IStopWatch stopwatch) {
        this.stopWatch = stopwatch;
    }


    public static OnapMDCWrapper getInstance() {
        return instanceMdcWrapper;
    }

    static {
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
            fqdn = InetAddress.getByName(hostAddress).getCanonicalHostName();
        } catch (Exception ex) {
            log.error("failed to get machine parameters", ex);
        }
    }

    @Override
    public OnapMDCWrapper startTimer() {
        stopWatch.start();
        return this;
    }

    @Override
    public OnapMDCWrapper stopTimer() {
        try {
            stopWatch.stop();
        } catch (Exception ex) {
            log.error("StopWatch failed; probably start was not called before Stopwatch", ex);
        }
        return this;
    }

    @Override
    public OnapMDCWrapper setClassName(String className) {
        MDC.put(OnapLogConfiguration.MDC_CLASS_NAME, className);
        return this;
    }

    // automatic parameter this is optional
    @Override
    public OnapMDCWrapper setAutoServerFQDN(String serverFQDN) {
        MDC.put(OnapLogConfiguration.MDC_SERVER_FQDN, serverFQDN);
        return this;
    }

    // automatic parameter this is optional
    @Override
    public OnapMDCWrapper setAutoServerIPAddress(String serverIPAddress) {
        MDC.put(OnapLogConfiguration.MDC_SERVER_IP_ADDRESS, serverIPAddress);
        return this;
    }

    @Override
    public OnapMDCWrapper setInstanceUUID(String instanceUUID) {
        MDC.put(OnapLogConfiguration.MDC_INSTANCE_UUID, instanceUUID);
        return this;
    }

    @Override
    public OnapMDCWrapper setProcessKey(String processKey) {
        MDC.put(OnapLogConfiguration.MDC_PROCESS_KEY, processKey);
        return this;
    }

    @Override
    public OnapMDCWrapper setAlertSeverity(Severity alertSeverity) {
        MDC.put(OnapLogConfiguration.MDC_ALERT_SEVERITY, String.valueOf(alertSeverity.getSeverityType()));
        return this;
    }

    @Override
    public OnapMDCWrapper setOptCustomField1(String customField1) {
        MDC.put(OnapLogConfiguration.MDC_OPT_FIELD1, customField1);
        return this;
    }

    @Override
    public OnapMDCWrapper setOptCustomField2(String customField2) {
        MDC.put(OnapLogConfiguration.MDC_OPT_FIELD2, customField2);
        return this;
    }

    @Override
    public OnapMDCWrapper setOptCustomField3(String customField3) {
        MDC.put(OnapLogConfiguration.MDC_OPT_FIELD3, customField3);
        return this;
    }

    @Override
    public OnapMDCWrapper setOptCustomField4(String customField4) {
        MDC.put(OnapLogConfiguration.MDC_OPT_FIELD4, customField4);
        return this;
    }

    @Override
    public OnapMDCWrapper setKeyRequestId(String keyRequestId) {
        MDC.put(OnapLogConfiguration.MDC_KEY_REQUEST_ID, keyRequestId); // eg. servletRequest.getSession().getId()
        return this;
    }

    @Override
    public OnapMDCWrapper setRemoteHost(String remoteHost) {
        MDC.put(OnapLogConfiguration.MDC_REMOTE_HOST, remoteHost);
        return this;
    }

    @Override
    public OnapMDCWrapper setServiceName(String serviceName) {
        MDC.put(OnapLogConfiguration.MDC_SERVICE_NAME, serviceName);
        return this;
    }

    @Override
    public OnapMDCWrapper setStatusCode(String statusCode) {
        MDC.put(OnapLogConfiguration.MDC_STATUS_CODE, statusCode);
        return this;
    }

    @Override
    public OnapMDCWrapper setPartnerName(String partnerName) {
        MDC.put(OnapLogConfiguration.MDC_PARTNER_NAME, partnerName);
        return this;
    }

    @Override
    public OnapMDCWrapper setResponseCode(int responseCode) {
        MDC.put(OnapLogConfiguration.MDC_RESPONSE_CODE, Integer.toString(responseCode));
        return this;
    }

    @Override
    public OnapMDCWrapper setResponseDesc(String responseDesc) {
        MDC.put(OnapLogConfiguration.MDC_RESPONSE_DESC, responseDesc);
        return this;
    }

    @Override
    public OnapMDCWrapper setServiceInstanceId(String serviceInstanceId) {
        MDC.put(OnapLogConfiguration.MDC_SERVICE_INSTANCE_ID, serviceInstanceId);
        return this;
    }

    @Override
    public OnapMDCWrapper setTargetEntity(String targetEntity) {
        MDC.put(OnapLogConfiguration.MDC_TARGET_ENTITY, targetEntity);
        return this;
    }

    @Override
    public OnapMDCWrapper setTargetServiceName(String targetServiceName) {
        MDC.put(OnapLogConfiguration.MDC_TARGET_SERVICE_NAME, targetServiceName);
        return this;
    }

    @Override
    public OnapMDCWrapper setTargetVirtualEntity(String targetVirtualEntity) {
        MDC.put(OnapLogConfiguration.MDC_TARGET_VIRTUAL_ENTITY, targetVirtualEntity);
        return this;
    }

    @Override
    public OnapMDCWrapper setErrorCode(int errorCode) {
        MDC.put(OnapLogConfiguration.MDC_ERROR_CODE, Integer.toString(errorCode));
        return this;
    }

    @Override
    public OnapMDCWrapper setErrorDescription(String errorDescription) {
        MDC.put(OnapLogConfiguration.MDC_ERROR_DESC, errorDescription);
        return this;
    }

    @Override
    public void validateMandatoryFields() {
        // this method only checks if the mandatory fields have been initialized
        String missingFieldNames = checkMandatoryFieldsExistInMDC();

        if (MDC.getCopyOfContextMap() == null || MDC.getCopyOfContextMap().isEmpty()) {
            writeLogMDCEmptyError();
            return;
        }

        if (!"".equalsIgnoreCase(missingFieldNames)) {
            writeLogMissingFieldsError(missingFieldNames);
        }
    }

    private void writeLogMissingFieldsError(String filedNameThatHasNotBeenInitialized) {
        log.warn("mandatory parameters for EELF logging, missing fields: {}", filedNameThatHasNotBeenInitialized);
    }

    private void writeLogMDCEmptyError() {
        log.error("write to log when MDC is empty error");
    }

    @Override
    public OnapMDCWrapper clear() {
        mandatoryFields.forEach(MDC::remove);
        optionalFields.forEach(MDC::remove);
        return this;
    }

    private String checkMandatoryFieldsExistInMDC() {
        // this method returns a String of uninitialised fields
        StringBuilder missingFields = new StringBuilder();
        mandatoryFields.forEach(field -> {
            if (isMDCParamEmpty(field)) {
                missingFields.append(field).append(" ");
            }
        });
        return missingFields.toString();
    }

    @Override
    public void setMandatoryField(String parameterName) {
        mandatoryFields.add(parameterName);
    }

    @Override
    public void setOptionalField(String parameterName) {
        optionalFields.add(parameterName);
    }

    @Override
    public boolean isMDCParamEmpty(String mdcKeyName) {
        String val = MDC.get(mdcKeyName);
        return val == null || val.trim().length() == 0;
    }

    @Override
    public String getFqdn() {
        return fqdn;
    }

    @Override
    public String getHostAddress() {
        return hostAddress;
    }
}
