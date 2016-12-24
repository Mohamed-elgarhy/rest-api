package com.vfe.restful;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;
import org.json.JSONObject;

/*import org.codehaus.jettison.json.JSONException;
 import org.codehaus.jettison.json.JSONObject;

 import com.sun.jersey.json.impl.provider.entity.JSONObjectProvider;*/

@Path("")
public class Service {
	/**
	 * Default constructor.
	 */
	public Service() {
		// TODO Auto-generated constructor stub
	}

	public static double FibonacciRecursive(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		else {
			double result = FibonacciRecursive(n - 1)
					+ FibonacciRecursive(n - 2);
			return result;
		}

	}

	/**
	 * Retrieves representation of an instance of Service
	 * 
	 * @return an instance of String
	 */
	@GET
	@Path("Token")
	@Produces("application/json")
	public Response getToken() {
		String result = "d305d55d-dee8-44ff-837f-b080a3f6a4b0";

		return Response.status(200).entity(result).build();
	}

	@GET
	@Produces("application/json")
	@Path("ReverseWords")
	public String reverseSentense(@Context UriInfo info) {

		/*
		 * String sentence = info.getQueryParameters().getFirst("sentence");
		 * StringBuilder sentenceBuilder = new StringBuilder(); StringBuilder
		 * wordBuilder = new StringBuilder(); char [] charSentence =
		 * sentence.toCharArray(); boolean newWordFlag = true; for (int i = 0; i
		 * < charSentence.length; i++) {
		 * 
		 * if (charSentence[i] == ' ' ) { if (newWordFlag) { newWordFlag =
		 * false; wordBuilder.reverse(); sentenceBuilder.append(wordBuilder); }
		 * 
		 * 
		 * System.out.println("space"); sentenceBuilder.append(charSentence[i]);
		 * continue; } else { if (!newWordFlag) { newWordFlag = true;
		 * wordBuilder = new StringBuilder(); }
		 * 
		 * 
		 * System.out.println(charSentence[i]);
		 * wordBuilder.append(charSentence[i]);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 */

		String sentence = info.getQueryParameters().getFirst("sentence");
		StringBuilder sentenceBuilder = new StringBuilder();
		StringBuilder wordBuilder = new StringBuilder();
		char[] charSentence = sentence.toCharArray();
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		boolean newWordFlag = true;
		for (int i = 0; i < charSentence.length; i++) {

			if (charSentence[i] == ' ') {
				if (newWordFlag) {
					newWordFlag = false;
					result.add(wordBuilder);
					wordBuilder = new StringBuilder();
				}

				// continue;
			} else {
				if (!newWordFlag) {
					newWordFlag = true;
					result.add(wordBuilder);
					wordBuilder = new StringBuilder();
				}
			}

			wordBuilder.append(charSentence[i]);

		}
		result.add(wordBuilder);

		for (Iterator<StringBuilder> iterator = result.iterator(); iterator
				.hasNext();) {
			StringBuilder stringBuilder = iterator.next();
			// System.out.println(stringBuilder);
			sentenceBuilder.append(stringBuilder.reverse());

		}

		/*
		 * String[] words = sentence.split("\\s+"); StringBuilder builder ;
		 * StringBuilder result = new StringBuilder(); for (int i = 0; i <
		 * words.length; i++) { builder = new StringBuilder(words[i]);
		 * builder.reverse(); System.out.println(builder);
		 * result.append(builder); result.append(" "); }
		 */

		return sentenceBuilder.toString();
	}

	// static int fib[] =

	public long fibonacciDynamicProgramming(int x) {
		long a = 0l;
		long b = 1l;
		for (int i = 0; i < x; i++) {
			long c = a + b;// System.out.println(c);
			a = b;
			b = c;
		}
		return a;
	}

	@GET
	@Path("Fibonacci")
	@Produces("application/json")
	public Response fibonacciSeries(@QueryParam("n") String n) {
		// TODO Auto-generated method stub
		/*
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("f value",
		 * n); String result = jsonObject.toString(); return
		 * Response.status(200).entity(result).build();
		 */
		String result = "no content";
		// return "OK";

		int num = 0;
		try {
			num = Integer.parseInt(n);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "The request is invalid.");
			String res = jsonObject.toString();
			return Response.status(400).entity(res).build();
		}

		if (num <= 92) {
			result = fibonacciDynamicProgramming(num) + "";
			return Response.status(200).entity(result).build();
			// return result+"";
		} else {
			return Response.status(400).entity(result).build();
		}

	}

	@GET
	@Path("TriangleType")
	@Produces("application/json")
	public Response calculateTriangle(@QueryParam("a") String a,
			@QueryParam("b") String b, @QueryParam("c") String c) {
		// TODO Auto-generated method stub
		/*
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("f value",
		 * n); String result = jsonObject.toString(); return
		 * Response.status(200).entity(result).build();
		 */
		String result = "Error";
		// return "OK";

		int a_side = 0;
		int b_side = 0;
		int c_side = 0;
		try {
			a_side = Integer.parseInt(a);
			b_side = Integer.parseInt(b);
			c_side = Integer.parseInt(c);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "The request is invalid.");
			String res = jsonObject.toString();
			return Response.status(400).entity(res).build();
		}

		try {
			result = calculateTriangle(a_side, b_side, c_side);
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(400).entity(result).build();
		}

	}

	private String calculateTriangle(int a, int b, int c) throws Exception {

		if ((a + b) <= c || (a + c) <= b || (b + c) <= a) {
			throw new Exception("Error"); // FIXME
		}
		if (a == b && b == c) {
			return "Equilateral";
		} else if ((a == b && b != c) || (a == c && c != b)
				|| (b == c && c != a)) {
			return "Isosceles";
		} else {
			return "Scalene";
		}

		// return "";
	}

	@POST
	@Path("{f}")
	@Produces("application/json")
	public Response jsonExample1(@PathParam("f") int f) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("f value", f + 1);
		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();

		// return "OK";
	}

}