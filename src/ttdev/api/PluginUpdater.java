package ttdev.api;

import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PluginUpdater {
	
	private static String website = "http://projecten.uphero.com/updater/";

	public static void startTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
            	System.out.println("Checking for the latest version of: API");
            	update();
            }
            
        }.runTaskLater(API.getInstance(), 72000);
	}
	
	private static void update() {
		downloadFile(website, "API.jar", "API.jar");
	}
	
	private static void downloadFile(String URL, String webFile, String pluginFile) {
		try {
		    URL url = new URL(URL + webFile);
		    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		    urlConnection.setRequestMethod("GET");
		    urlConnection.setDoOutput(true);
		    urlConnection.connect();

		    File file = new File("./plugins/" + pluginFile);

		    FileOutputStream fileOutput = new FileOutputStream(file);
		    InputStream inputStream = urlConnection.getInputStream();

		    byte[] buffer = new byte[1024];
		    int bufferLength = 0;

		    while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
		        fileOutput.write(buffer, 0, bufferLength);
		    }
		    fileOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
