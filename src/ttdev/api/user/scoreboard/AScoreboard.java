package ttdev.api.user.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class AScoreboard {

	private Scoreboard scoreboard;
	private ScoreboardManager manager;
	private Objective objective;
	
	private ArrayList<Score> scores = new ArrayList<>();
	
	/**
	 * Create a scoreboard.
	 * @param title
	 */
	public AScoreboard(String title) {
		this.manager = Bukkit.getScoreboardManager();
		this.scoreboard = this.manager.getNewScoreboard();
		this.objective = this.scoreboard.registerNewObjective(ChatColor.translateAlternateColorCodes('&', title), "dummy");
	}
	
	/**
	 * Set the displayslot.
	 * @param slot
	 */
	public void setDisplaySlot(DisplaySlot slot) {
		objective.setDisplaySlot(slot);
	}
	
	/**
	 * Set the displayname.
	 * @param title
	 */
	public void setDisplayName(String title) {
		objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
	}
	
	/**
	 * Overwrite/create a score.
	 * @param slot
	 * @param line
	 */
	public void setScore(int slot, String line) {
		for (Score score : this.scores) {
			if (score.getScore() == slot) {
				this.scoreboard.resetScores(score.getEntry());
				this.scores.remove(score);
				
				Score s = this.objective.getScore(ChatColor.translateAlternateColorCodes('&', line));
				s.setScore(slot);
				this.scores.add(s);
				return;
			}
		}
		Score s = this.objective.getScore(ChatColor.translateAlternateColorCodes('&', line));
		s.setScore(slot);
		this.scores.add(s);
	}
	
	/**
	 * Remove a score.
	 * @param slot
	 */
	public void removeScore(int slot) {
		for (Score score : this.scores) {
			if (score.getScore() == slot) {
				this.scoreboard.resetScores(score.getEntry());
				this.scores.remove(score);
			}
		}
	}
	
	/**
	 * Remove a score.
	 * @param line
	 */
	public void removeScore(String line) {
		for (Score score : this.scores) {
			if (score.getEntry().equals(line)) {
				this.scoreboard.resetScores(score.getEntry());
				this.scores.remove(score);
			}
		}
	}
	
	/**
	 * Set a scoreboard for a player.
	 * @param player
	 */
	public void setScoreboard(Player player) {
		player.setScoreboard(this.scoreboard);
	}
	
	/**
	 * Returns Scoreboard.
	 * @return
	 */
	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}
	
	/**
	 * Returns all the Scores.
	 * @return
	 */
	public ArrayList<Score> getScores() {
		return this.scores;
	}
	
	/**
	 * Returns Objective.
	 * @return
	 */
	public Objective getObjective() {
		return this.objective;
	}
	
	/**
	 * Overwrites the scores.
	 * @param scores
	 */
	public void setScores(ArrayList<Score> scores) {
		this.scores = scores;
		
		for (Score score : this.scores) {
			Score tmp = this.objective.getScore(score.getEntry());
			tmp.setScore(score.getScore());
		}
	}
	
}
