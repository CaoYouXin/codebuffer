package schedule.fs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import utils.StringUtils;
import utils.dynamicinit.InitManager;

public class Config {

	private String name;
	private List<String> params;

	Config() {
	}

	Config setName(String name) {
		this.name = new String(name);
		return this;
	}

	Config addParam(String param) {
		if (null == this.params) {
			this.params = new LinkedList<>();
		}
		this.params.add(new String(param));
		return this;
	}

	public void cfg(Command command, CommandHandler handler) {
		Map<String, ConfigHandler> map = handlers.get(command.name);
		if (null == map) {
			System.err.println(String.format("lack of handler, %s",
					this.toString()));
		}
		ConfigHandler configHandler = map.get(this.name);
		if (null == configHandler) {
			System.err.println(String.format("lack of handler, %s",
					this.toString()));
		}
		configHandler.fn(new CfgParam(this.params, handler));
	}

	@Override
	public String toString() {
		return "Config [name=" + name + ", params=[" + StringUtils.wrap(params)
				+ "]]";
	}

	// static data map
	private static Map<String, Map<String, ConfigHandler>> handlers = init();

	private synchronized static Map<String, Map<String, ConfigHandler>> init() {
		if (null != handlers) {
			return handlers;
		}
		HashMap<String, Map<String, ConfigHandler>> hashMap = new HashMap<>();
		URL resource = Config.class.getResource("cmds");
		try {
			Path path = Paths.get(resource.toURI());
			Files.find(path, 2, new BiPredicate<Path, BasicFileAttributes>() {
				@Override
				public boolean test(Path t, BasicFileAttributes u) {
					if (u.isDirectory()) {
						return true;
					}
					return false;
				}
			}).forEach((p) -> {
				String cmdName = p.getName(p.getNameCount() - 1).toString();
				try {
					hashMap.put(cmdName, InitManager.init("cmds/" + cmdName, String.class, ConfigHandler.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return hashMap;
	}

}
