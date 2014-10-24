package schedule.fs;

import utils.Debugger;


public class FS {

	private FS() {
	}

	public static <Return> Return command(String command,
			CompletedHandler<Return> handler) {
		Command cmd = parseCmd(command);
		Debugger.debug(() -> {
			System.out.println(cmd.toString());
		});
		String result = cmd.cmd();
		if (null != handler) {
			return handler.fn(result);
		} else {
			return null;
		}
	}

	private static Command parseCmd(String command) {
		// IntStream chars = command.chars();
		// char[] value = new char[command.length()];
		// IntWrapper offset = new IntWrapper();
		// chars.forEach((i) -> {
		// Debugger.debug(() -> {
		// char[] pchars = Character.toChars(i);
		// System.arraycopy(pchars, 0, value, offset.val(), pchars.length);
		// offset.val(offset.val() + pchars.length);
		// });
		// });
		// Debugger.debug(() -> {
		// System.out.println(new String(value));
		// });
		Command cmd = new Command();
		boolean isCmdName = true, isCfgName = false, isCfgParam = false, 
				hasCfg = false, isParam = false, isString = false;
		String buffer = "";
		Config cfgBuffer = new Config();
		for (int index = 0; index < command.length(); index++) {
			char c = command.charAt(index);
			switch (c) {
			case ' ':
				if (isString) {
					buffer += ' ';
					break;
				}
				if (isCfgParam) {
					cfgBuffer.addParam(buffer);
					buffer = "";
					break;
				}
				if (isParam) {
					cmd.addParam(buffer);
					buffer = "";
					break;
				}
				if (isCmdName) {
					cmd.setName(buffer);
					isCmdName = false;
					buffer = "";
					break;
				}
				if (isCfgName) {
					if ("p".equalsIgnoreCase(buffer)) {
						isParam = true;
					} else {
						cfgBuffer.setName(buffer);
						isCfgParam = true;
					}
					isCfgName = false;
					buffer = "";
					break;
				}
			case '-':
				if (hasCfg) {
					cmd.addCfg(cfgBuffer);
					cfgBuffer = new Config();
				}
				isCfgName = true;
				isCfgParam = false;
				hasCfg = true;
				break;
			case '\'':
				isString = !isString;
				break;
			default:
				buffer += c;
				break;
			}
		}
		if (isCfgName) {
			cmd.addCfg(cfgBuffer.setName(buffer));
		}
		if (isCfgParam) {
			cmd.addCfg(cfgBuffer.addParam(buffer));
		}
		if (isParam) {
			cmd.addParam(buffer);
		}
		return cmd;
	}

}
