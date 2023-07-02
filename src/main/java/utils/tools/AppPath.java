package utils.tools;

import java.nio.file.Paths;

public class AppPath {
	String privatePath = "/WEB-INF";
	String viewsDir = "views";
	String mediaDir = "media";
	String staticDir = "static"; // NOTE: This one should be public not private
	

	public String convertToPrivate(String path) {
//		return Paths.get(privatePath, path).toString();
		return privatePath + "/" + path;
	}

	public String convertToView(String path) {
//		return Paths.get(convertToPrivate(viewsDir), path).toString();
		return convertToPrivate(viewsDir) + "/" +  path;
	}

}
