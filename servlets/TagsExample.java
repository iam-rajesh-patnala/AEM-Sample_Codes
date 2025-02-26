package com.rpwebcrafts.aem.core.servlets; // Defines the package location of this servlet

// Import required Java and AEM-specific classes
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

// Register the servlet as an OSGi component with the Sling framework
@Component(service = Servlet.class, immediate = true)
// Define the servlet path to be accessed via the browser or API
@SlingServletPaths(value = { "/bin/demo/tags" })
public class TagsExample extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L; // Recommended for serialization to maintain compatibility

	// Override the doGet method to handle GET requests
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		// Get the ResourceResolver from the request to interact with AEM resources
		ResourceResolver resolver = request.getResourceResolver();
		// Get the TagManager to work with AEM tags
		TagManager tagManager = resolver.adaptTo(TagManager.class);

		// Check if TagManager is null (if it couldn't be retrieved)
		if (tagManager == null) {
			response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 error
			response.getWriter().write("{\"error\": \"TagManager could not be retrieved.\"}"); // Send error message as
																								// JSON
			return; // Stop further execution
		}

		// Resolve the specific tag path in AEM
		Tag result = tagManager.resolve("/content/cq:tags/rp-webcrafts/web-pages");

		// Check if the tag was found
		if (result == null) {
			response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND); // Set HTTP 404 error
			response.getWriter().write("{\"error\": \"Tag not found.\"}"); // Send error message as JSON
			return; // Stop further execution
		}

		// Get an iterator for all child tags under the resolved tag
		Iterator<Tag> listChildren = result.listChildren();
		JSONArray pagesArray = new JSONArray(); // Create a JSON array to store tag data

		// Loop through each child tag
		while (listChildren.hasNext()) {
			Tag nextTag = listChildren.next(); // Get the next tag
			String tagTitle = nextTag.getName(); // Get the tag name
			String tagPath = nextTag.getPath(); // Get the tag path

			// Create a new JSON object to store the tag details
			JSONObject pagesObject = new JSONObject();
			try {
				pagesObject.put("title", tagTitle); // Add tag title to JSON object
				pagesObject.put("path", tagPath); // Add tag path to JSON object
				pagesArray.put(pagesObject); // Add the JSON object to the JSON array
			} catch (JSONException e) {
				response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 error
				response.getWriter().write("{\"error\": \"JSON processing error.\"}"); // Send error message
				return; // Stop further execution
			}
		}

		// Set the response type to JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// Write the JSON array as the response
		response.getWriter().write(pagesArray.toString());
	}
}