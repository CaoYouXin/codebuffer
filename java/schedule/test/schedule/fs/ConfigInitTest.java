package schedule.fs;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigInitTest {

	public static void main(String[] args) throws URISyntaxException {
		URL resource = ConfigInitTest.class.getResource("cmds/aaa");
		Path path = Paths.get(resource.toURI());
		System.out.println(path.getName(path.getNameCount() - 1));
	}

}
