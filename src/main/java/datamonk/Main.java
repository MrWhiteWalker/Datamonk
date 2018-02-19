package datamonk;

public class Main {
	public static void main(String[] args) {
		String urlList[] = { "https://food.ndtv.com/recipe-crispy-herb-chicken-951526",
				"https://food.ndtv.com/recipe-khus-khus-ka-halwa-951601",
				"https://food.ndtv.com/recipe-pink-lemonade-951292" };

		for (int i = 0; i < urlList.length; i++) {
			Crawler crawler = new Crawler();
			if (crawler.createConnection(urlList[i])) {
				// System.out.println("Found");
			} else {
				System.out.println("Error");
			}
			crawler.fetchData();
			System.out.println(crawler.getFinalRecipe());
		}

	}
}
