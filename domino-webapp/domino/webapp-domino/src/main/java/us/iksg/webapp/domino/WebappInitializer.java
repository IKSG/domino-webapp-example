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
package us.iksg.webapp.domino;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.ibm.designer.runtime.domino.adapter.ComponentModule;
import com.ibm.designer.runtime.domino.adapter.HttpService;
import com.ibm.designer.runtime.domino.adapter.LCDEnvironment;
import com.ibm.designer.runtime.domino.bootstrap.adapter.HttpServletRequestAdapter;
import com.ibm.designer.runtime.domino.bootstrap.adapter.HttpServletResponseAdapter;
import com.ibm.designer.runtime.domino.bootstrap.adapter.HttpSessionAdapter;

public class WebappInitializer extends HttpService {

	public WebappInitializer(LCDEnvironment env) {
		super(env);
		
		System.out.println("Example webapp init!");
	}
	
	@Override
	public void refreshSettings() {
		super.refreshSettings();
	}
	
	@Override
	public void destroyService() {
		super.destroyService();
	}
	
	// *******************************************************************************
	// * Stub service methods
	// *******************************************************************************

	@Override
	public boolean doService(String arg0, String arg1, HttpSessionAdapter session, HttpServletRequestAdapter req,
			HttpServletResponseAdapter resp) throws ServletException, IOException {
		// NOP
		return false;
	}

	@Override
	public void getModules(List<ComponentModule> modules) {
		// NOP
	}

}
