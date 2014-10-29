package schedule.fs;

import java.util.Arrays;

import argsboot.ArgsBoot;
import argsboot.CompletedHandler;

public class FS {

	private FS() {
	}

	public static boolean read(String path) {
		return ArgsBoot.call(Arrays.asList("fs", "read", path), (CompletedHandler<Boolean>)(result) -> {
				return "true".equalsIgnoreCase(result);
		});
	}

}
