package datamonk;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	String title;
	int servings;
	List<Ingredient> ingredientList = new ArrayList<Recipe.Ingredient>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public void addIngredient(Ingredient ingredient) {
		ingredientList.add(ingredient);
 	}

	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public Recipe() {
		super();
	}

	public Recipe(String title, int servings, List<Ingredient> ingredientList) {
		super();
		this.title = title;
		this.servings = servings;
		this.ingredientList = ingredientList;
	}

	class Ingredient {
		String amount;
		String ingredientName;

		public Ingredient() {
			super();
		}

		public Ingredient(String amount, String ingredientName) {
			super();
			this.amount = amount;
			this.ingredientName = ingredientName;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getIngredientName() {
			return ingredientName;
		}

		public void setIngredientName(String ingredientName) {
			this.ingredientName = ingredientName;
		}

	}

	@Override
	public String toString() {
		//System.out.println(ingredientList.size());
		StringBuffer retBuffer = new StringBuffer(title+"|"+servings+"|[");
	//	System.out.println(retBuffer.toString());
		for(Ingredient ingredient:ingredientList) {
			retBuffer.append("['"+ingredient.amount+"','"+ingredient.ingredientName+"'],");
		//	System.out.println(retBuffer.toString());

		}
		retBuffer.deleteCharAt(retBuffer.length()-1);
		retBuffer.append("]\n");
		return retBuffer.toString();
	}
	
}
