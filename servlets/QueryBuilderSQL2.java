package com.rpwebcrafts.aem.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import javax.jcr.query.Query;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

@Component(service = Servlet.class, immediate = true, property = { "sling.servlet.paths=/bin/querybuilder/sql2" })
public class QueryBuilderSQL2 extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		// SQL2 query to select all cq:Page nodes under /content/we-retail with specific
		// templates or resource types
		String query = "SELECT * FROM [cq:Page] AS page WHERE ISDESCENDANTNODE([/content/we-retail]) "
				+ "AND (page.[jcr:content/cq:template] = '/conf/we-retail/settings/wcm/templates/hero-page' "
				+ "OR page.[jcr:content/sling:resourceType] = 'weretail/components/structure/page') "
				+ "order by page.[jcr:content/cq:created] desc";

		JsonArrayBuilder jsonArray = Json.createArrayBuilder(); // To build a JSON array response

		try {
			ResourceResolver resourceResolver = request.getResourceResolver(); // Get ResourceResolver from request

			// Execute the SQL2 query and get an iterator of Resource objects
			Iterator<Resource> results = resourceResolver.findResources(query, Query.JCR_SQL2);

			while (results.hasNext()) { // Iterate over the results
				Resource resource = results.next();
				Page page = resource.adaptTo(Page.class); // Adapt the resource to a Page object

				if (page != null) { // If adaptation is successful
					JsonObjectBuilder jsonObj = Json.createObjectBuilder(); // Create JSON object for each page
					jsonObj.add("title", page.getTitle()); // Add title property
					jsonObj.add("path", page.getPath()); // Add path property

					jsonArray.add(jsonObj); // Add JSON object to array
				}
			}

		} catch (Exception e) {
			e.printStackTrace(); // Print any exceptions
		}

		// Write the JSON array to the HTTP response
		response.getWriter().write(jsonArray.build().toString());
	}
}
