package ttdev.api.user.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import ttdev.api.inventory.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ARecipe {
	
	private ItemStack result;
	private ShapedRecipe recipe;
	
	public ARecipe(Item result) {
		this.result = result.getItemStack();
		this.recipe = new ShapedRecipe(this.result);
		
		this.recipe.shape("ABC", "DEF", "GHI");
	}
	
	/**
	 * 
	 * A B C
	 * D E F
	 * G H I
	 * 
	 * @param slot
	 * @param material
	 */
	public void setIngredient(int slot, Material material) {
		switch (slot) {
			case 1:
				this.recipe.setIngredient('A', material);
				return;
			case 2:
				this.recipe.setIngredient('B', material);
				return;
			case 3:
				this.recipe.setIngredient('C', material);
				return;
			case 4:
				this.recipe.setIngredient('D', material);
				return;
			case 5:
				this.recipe.setIngredient('E', material);
				return;
			case 6:
				this.recipe.setIngredient('F', material);
				return;
			case 7:
				this.recipe.setIngredient('G', material);
				return;
			case 8:
				this.recipe.setIngredient('H', material);
				return;
			case 9:
				this.recipe.setIngredient('I', material);
				return;
		}
	}
	
	/**
	 * Store the Recipe.
	 */
	public void save() {
		Bukkit.getServer().addRecipe(this.recipe);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Material> getIngredientMap() {
		ArrayList<Material> ingredients = new ArrayList<>();
		
	    Iterator<?> it = this.recipe.getIngredientMap().entrySet().iterator();
	    while (it.hasNext()) {
	        @SuppressWarnings("unchecked")
			Map.Entry<Character, Material> pair = (Entry<Character, Material>) it.next();
	        ingredients.add(pair.getValue());
	    }
		
		return ingredients;
	}
	
	/**
	 * 
	 * @return
	 */
	public ItemStack getResult() {
		return this.result;
	}
	
}
