package schedule.fs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.StringUtils;

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
		handlers.get(this.name).get(command.name).fn(this.params);
	}
	
	@Override
	public String toString() {
		return "Config [name=" + name + ", params=[" + StringUtils.wrap(params) + "]]";
	}

	// static data map
	private static Map<String , Map<String, ConfigHandler>> handlers = init();

	private synchronized static Map<String, Map<String, ConfigHandler>> init() {
		if (null != handlers) {
			return handlers;
		}
		return new HashMap<>();
	}
	
}
