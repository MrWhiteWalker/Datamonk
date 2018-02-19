package datamonk;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import datamonk.Recipe.Ingredient;

public class Crawler {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private Connection connection;
	private Document htmlDocument;
	private Recipe recipe;

	public boolean createConnection(String url) {
		try {

			connection = Jsoup.connect(url).userAgent(USER_AGENT);
			htmlDocument = connection.get();
		//	System.out.println(connection.response().statusCode());
			if (connection.response().statusCode() == 200)
	//			System.out.println("Connected");
			if (!connection.response().contentType().contains("text/html")) {
	//			System.out.println("**Failure** Retrieved something other than HTML");
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		} catch (HttpStatusException e) {
			return false;
		} catch (IOException e) {
			return false;

		}
		return true;

	}

	public void fetchData() {
		recipe = new Recipe();
		System.out.println("Getting Recipe name");
		getRecipeName();
		System.out.println("Getting Serving size");
		getServingSize();
		System.out.println("Getting Ingredient list");
		getIngredients();
	}

	private void getRecipeName() {
		Element element = htmlDocument.select("h1").first();
		recipe.setTitle(element.text());
	}

	private boolean getServingSize() {

		// System.out.println("Found tag with class= recp-det-cont");
		Elements servingSpans = htmlDocument.select("div.recp-det-cont").first().select("i.serv_icon").first().parent()
				.select("span");
		for (Element span : servingSpans) {
			if (!span.text().trim().equals("Recipe Servings:")) {
	//			System.out.println("Recipe Servings: " + span.text());
				recipe.setServings(Integer.parseInt(span.text().trim()));
				return true;
			}
		}
		return false;
	}

	public boolean getIngredients() {
		Elements ingredientTags = htmlDocument.select("div.ingredients").first().select("li");
	//	System.out.println("Ingredients " + ingredientTags.size());

		for (Element ingredient : ingredientTags) {
			if (ingredient.select("b").first() == null) {
	//			System.out.println(ingredient.text());
				String[] tokens = ingredient.text().trim().split(" ");

				if (tokens[0].charAt(0) <= '9' && tokens[0].charAt(0) >= '0') {
					recipe.addIngredient(
							recipe.new Ingredient(tokens[0], ingredient.text().trim().substring(tokens[0].length())));
				} else {
					recipe.addIngredient(recipe.new Ingredient("", ingredient.text().trim()));

				}
			}
		}

		return true;

	}

	public Recipe getFinalRecipe() {
		return recipe;
	}

}
