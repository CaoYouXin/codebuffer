package schedule.fs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.StringUtils;


public class Command {

	String name;
	private List<Config> cfgs;
	private List<String> params;
	
	Command() {
	}
	
	Command setName(String name) {
		this.name = new String(name);
		return this;
	}

	Command addCfg(Config cfg) {
		if (null == this.cfgs) {
			this.cfgs = new LinkedList<>();
		} 
		this.cfgs.add(cfg);
		return this;
	}
	
	Command addParam(String param) {
		if (null == this.params) {
			this.params = new LinkedList<>();
		} 
		this.params.add(new String(param));
		return this;
	}
	
	public String cmd() {
		CommandHandler handler = handlers.get(this.name);
		
		for (Config cfg : this.cfgs) {
			cfg.cfg(this, handler);
		}
		
		return handler.fn(this.params);
	}
	
	@Override
	public String toString() {
		return "Command [name=" + name + ", cfgs=[" + StringUtils.join(cfgs, ", ") + "], params=["
				+ StringUtils.wrap(params) + "]]";
	}

	// static data map
	private static Map<String, CommandHandler> handlers = init();

	private synchronized static Map<String, CommandHandler> init() {
		if (null != handlers) {
			return handlers;
		}
		return new HashMap<>();
	}
	
}
