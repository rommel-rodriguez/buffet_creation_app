package utils.tools;

import java.nio.file.Paths;

public class AppPath {
	String privatePath = "WEB-INF";
	String viewsDir = "views";
	String mediaDir = "media";
	String staticDir = "static"; // NOTE: This one should be public not private
	

	public String convertToPrivate(String path) {
		return Paths.get(privatePath, path).toString();
	}

	public String convertToView(String path) {
		return Paths.get(convertToPrivate(viewsDir), path).toString();
	}

}
