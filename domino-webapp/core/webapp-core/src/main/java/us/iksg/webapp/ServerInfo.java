package us.iksg.webapp;

import javax.json.bind.annotation.JsonbProperty;

import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;

public class ServerInfo {
	@JsonbProperty("server")
	private String serverName;
	private String os;
	private String jvm;
	private String jvmVersion;
	
	public ServerInfo() {
		try {
			Thread t = new NotesThread(() -> {
				try {
					Session session = NotesFactory.createSession();
					try {
						serverName = session.getUserName();
					} finally {
						session.recycle();
					}
				} catch(NotesException e) {
					e.printStackTrace();
				}
			});
			t.start();
			t.join();
			
			this.os = System.getProperty("os.name"); //$NON-NLS-1$
			this.jvm = System.getProperty("java.vm.name"); //$NON-NLS-1$
			this.jvmVersion = System.getProperty("java.runtime.version"); //$NON-NLS-1$
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getServerName() {
		return serverName;
	}
	
	public String getOs() {
		return os;
	}
	public String getJvm() {
		return jvm;
	}
	public String getJvmVersion() {
		return jvmVersion;
	}
}
