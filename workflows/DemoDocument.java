package com.rpwebcrafts.aem.core.workflows;

// Import required OSGi and AEM Workflow classes
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import java.util.Objects;

/**
 * This is an OSGi Component that registers as an AEM Workflow Process. The
 * `process.label` defines the name of this workflow step when used in AEM
 * workflows.
 */
@Component(service = WorkflowProcess.class, immediate = true, enabled = true, property = {
		"process.label=Custom_Demo_Document" }) // Label for workflow step in AEM
public class DemoDocument implements WorkflowProcess {

	// Logger for debugging and logging workflow execution
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDocument.class);

	/**
	 * This method is executed when the workflow step is triggered.
	 *
	 * @param workItemObj        Represents the current workflow item.
	 * @param workflowSessionObj Provides access to workflow-related operations.
	 * @param metaDataMapObj     Contains additional metadata.
	 */
	
	@Override
	public void execute(WorkItem workItemObj, WorkflowSession workflowSessionObj, MetaDataMap metaDataMapObj)
			throws WorkflowException {

		// Fetch the type of payload associated with the workflow
		String payloadType = Objects.toString(workItemObj.getWorkflowData().getPayloadType(), "UNKNOWN");

		// Check if the payload type is JCR_PATH (which means it's an AEM repository
		// path)
		if ("JCR_PATH".equals(payloadType)) {
			LOGGER.info("Payload Type: {}", payloadType);

			// Fetch the actual path of the payload (i.e., the JCR node path)
			String payloadPath = Objects.toString(workItemObj.getWorkflowData().getPayload(), "N/A");
			LOGGER.info("Payload Path: {}", payloadPath);
		} else {
			// Log a warning if the payload type is not supported
			LOGGER.warn("Unsupported Payload Type: {}", payloadType);
		}
	}
}
