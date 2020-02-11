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
package us.iksg.webapp.domino.jaxrs;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wink.server.internal.servlet.RestServlet;

public class RootServlet extends RestServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(final ServletConfig servletConfig) throws ServletException {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
			Thread.currentThread().setContextClassLoader(RootServlet.class.getClassLoader());
			return null;
		});
		try {
			super.init(servletConfig);
		} finally {
			AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
				Thread.currentThread().setContextClassLoader(cl);
				return null;
			});
		}
	}

	@Override
	public void service(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
			Thread.currentThread().setContextClassLoader(RootServlet.class.getClassLoader());
			return null;
		});

		try {
			super.service(request, response);
		} finally {
			AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
				Thread.currentThread().setContextClassLoader(cl);
				return null;
			});
		}
	}
}
