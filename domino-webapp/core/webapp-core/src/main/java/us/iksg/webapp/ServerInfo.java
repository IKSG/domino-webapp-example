/**
 * Copyright © 2020 Jesse Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
