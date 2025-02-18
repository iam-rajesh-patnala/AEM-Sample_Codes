package com.rpwebcrafts.aem.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = { Servlet.class }, immediate = true, property = { "sling.servlet.paths=/bin/demo/querybuilder" })
public class QueryBuilderServlet extends SlingAllMethodsServlet {

	@Reference
	private QueryBuilder queryBuilder;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		ResourceResolver resourceResolver = req.getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);

		if (session == null) {
			resp.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("Session could not be adapted from resource resolver.");
			return;
		}

		// Define the query predicate
		Map<String, String> predicate = new HashMap<>();
		predicate.put("type", "cq:Page");
		predicate.put("path", "/content/we-retail");
		predicate.put("property", "jcr:content/jcr:title");
		predicate.put("p.hits", "selective");
		predicate.put("p.properties", "jcr:content/jcr:title jcr:content/cq:templates");
		predicate.put("p.offset", "0");
		predicate.put("p.limit", "25");

		// Create the query using QueryBuilder and execute it
		Query createQuery = queryBuilder.createQuery(PredicateGroup.create(predicate), session);
		SearchResult result = createQuery.getResult();

		List<Hit> hits = result.getHits();

		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		// Iterating over the hits to process each result
		for (Hit hit : hits) {

			try {

				Resource resource = hit.getResource();
				if (resource == null) {
					continue; // skip if resource is null
				}

				Resource content = resourceResolver.getResource(resource.getPath() + "/jcr:content");
				if (content == null) {
					continue; // skip if content is null
				}

				String title = content.getValueMap().get("jcr:title", String.class);
				String resourceType = content.getValueMap().get("sling:resourceType", String.class);

				JsonObjectBuilder jsonObject = Json.createObjectBuilder();

				jsonObject.add("title", title != null ? title : "Untitled");
				jsonObject.add("resourceType", resourceType != null ? resourceType : "Unknown");
				jsonObject.add("path", resource.getPath());

				jsonArray.add(jsonObject.build());

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		resp.setContentType("application/json");
		resp.getWriter().write(jsonArray.build().toString());

	}
}